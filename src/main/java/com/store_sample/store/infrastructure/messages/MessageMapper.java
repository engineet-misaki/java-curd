package com.store_sample.store.infrastructure.messages;

import com.store_sample.store.domain.channels.model.Channel;
import com.store_sample.store.domain.messages.model.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MessageMapper {
    void insert(Message message);
}
