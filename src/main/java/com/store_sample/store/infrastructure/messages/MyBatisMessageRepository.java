package com.store_sample.store.infrastructure.messages;

import com.store_sample.store.domain.channels.model.Channel;
import com.store_sample.store.domain.channels.service.ChannelRepository;
import com.store_sample.store.domain.messages.model.Message;
import com.store_sample.store.domain.messages.service.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyBatisMessageRepository implements MessageRepository {

    private final MessageMapper messageMapper;

    @Override
    public void insert(Message message) {
        messageMapper.insert(message);
    }

}
