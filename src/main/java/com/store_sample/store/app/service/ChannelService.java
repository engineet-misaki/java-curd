package com.store_sample.store.app.service;

import com.store_sample.store.domain.channels.model.Channel;
import com.store_sample.store.domain.channels.service.ChannelDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChannelService {

    private final ChannelDomainService channelDomainService;

    public Channel create (Channel channel) {
        return channelDomainService.create(channel);
    }
    public Channel update (Channel channel) {
        return channelDomainService.update(channel);
    }

    public List<Channel> findAll () {
        return channelDomainService.findAll();
    }
}
