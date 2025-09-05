package com.store_sample.store.domain.channels.service;

import com.store_sample.store.domain.channels.model.Channel;

import java.util.List;
import java.util.Optional;

public interface ChannelRepository {
    void insert(Channel channel);
    List<Channel> findAll();
    Optional<Integer> getMaxId();
}
