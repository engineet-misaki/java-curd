package com.store_sample.store.infrastructure.channels;

import com.store_sample.store.domain.channels.model.Channel;
import com.store_sample.store.domain.channels.service.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyBatisChannelRepository implements ChannelRepository {

    private final ChannelMapper channelMapper;

    @Override
    public void insert(Channel channel) {
        channelMapper.insert(channel);
    }

    @Override
    public List<Channel> findAll() {
        return channelMapper.findAll();
    }

    @Override
    public Optional<Integer> getMaxId() {
        return channelMapper.getMaxId();
    }

    @Override
    public void delete(int id) {
        channelMapper.delete(id);
    }

    @Override
    public int update(Channel channel) {
        return channelMapper.update(channel);
    }
}
