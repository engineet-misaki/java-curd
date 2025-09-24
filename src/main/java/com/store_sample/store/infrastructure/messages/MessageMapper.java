package com.store_sample.store.infrastructure.messages;

import com.store_sample.store.domain.messages.model.Message;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MessageMapper {

  void insert(Message message);

  List<Message> find(@Param("channelId") int channelId,
      @Param("searchWord") Optional<String> searchWord);
}
