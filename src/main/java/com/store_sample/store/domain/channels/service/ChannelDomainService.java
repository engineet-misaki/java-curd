package com.store_sample.store.domain.channels.service;

import com.store_sample.store.domain.channels.model.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChannelDomainService {
    private final ChannelRepository channelRepository;


    public Channel create (Channel channel) {
        var currentMaxId = channelRepository.getMaxId();
        var newId = currentMaxId.orElse(0) + 1;
        channel.setId(newId);

        channelRepository.insert(channel);

        return channel;
    }

    public List<Channel> findAll(){
        return channelRepository.findAll();
    }

}
