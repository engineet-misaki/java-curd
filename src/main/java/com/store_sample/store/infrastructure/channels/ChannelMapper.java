package com.store_sample.store.infrastructure.channels;

import com.store_sample.store.domain.channels.model.Channel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ChannelMapper {
    void insert(Channel channel);
    List<Channel> findAll();
    Optional<Integer> getMaxId();
}
