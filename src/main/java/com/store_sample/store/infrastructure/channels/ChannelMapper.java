package com.store_sample.store.infrastructure.channels;

import com.store_sample.store.domain.channels.model.Channel;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChannelMapper {

  void insert(Channel channel);

  List<Channel> findAll();

  Optional<Integer> getMaxId();

  int update(Channel channel);

  void delete(int id);
}
