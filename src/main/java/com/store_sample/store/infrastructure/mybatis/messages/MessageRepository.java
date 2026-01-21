package com.store_sample.store.infrastructure.mybatis.messages;

import com.store_sample.store.domain.messages.model.Message;
import java.util.List;
import java.util.Optional;

public interface MessageRepository {

  void insert(Message message);

  List<Message> find(int channelId, Optional<String> searchWord);
}
