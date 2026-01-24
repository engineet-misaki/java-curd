package com.store_sample.store.service.message;

import com.store_sample.store.app.exception.TooManyRequestsException;
import com.store_sample.store.domain.messages.model.Message;
import com.store_sample.store.domain.messages.service.MessageDomainService;
import com.store_sample.store.domain.users.service.UserDomainService;
import com.store_sample.store.infrastructure.jpa.users.TblUsers;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageService {

  private final MessageDomainService messageDomainService;
  private final UserDomainService userDomainService;
  private final Map<String, Bucket> bucketMap = new ConcurrentHashMap<>();


  @Retryable(value = SQLException.class, maxAttempts = 6, backoff = @Backoff(delay = 100, maxDelay = 1000, multiplier = 2))
  public Message post(Message message) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Jwt jwt = (Jwt) auth.getPrincipal();
    String username = jwt.getSubject();
    TblUsers user = userDomainService.findByName(username);

    var bucket = resolvBucket(username);
    if (!bucket.tryConsume(1)) {
      throw new TooManyRequestsException();
    }

    message.setUserId(user.getId());

    messageDomainService.post(message);
    return message;
  }

  public List<Message> find(int channelId, Optional<String> searchWord) {

    return messageDomainService.find(channelId, searchWord);
  }


  private Bucket resolvBucket(String username) {
    return bucketMap.computeIfAbsent(username, key -> {
      var refill = Refill.intervally(2, Duration.ofMinutes(1));
      var limit = Bandwidth.classic(2, refill);
      return Bucket.builder()
          .addLimit(limit)
          .build();
    });
  }
}
