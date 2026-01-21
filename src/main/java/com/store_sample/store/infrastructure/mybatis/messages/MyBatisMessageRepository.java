package com.store_sample.store.infrastructure.mybatis.messages;

import com.store_sample.store.domain.messages.model.Message;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyBatisMessageRepository implements MessageRepository {

  private final MessageMapper messageMapper;

  @Override
  public void insert(Message message) {
    messageMapper.insert(message);
  }

  @Override
  public List<Message> find(int channelId, Optional<String> searchWord) {
    return messageMapper.find(channelId, searchWord);
  }

}
