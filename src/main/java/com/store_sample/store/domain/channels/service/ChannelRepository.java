package com.store_sample.store.domain.channels.service;

import com.store_sample.store.domain.channels.model.Channel;
import com.store_sample.store.domain.channels.model.FindAllChannelModel;
import com.store_sample.store.infrastructure.channels.TblChannels;
import java.util.List;
import java.util.Optional;

public interface ChannelRepository {

  void insert(TblChannels channel);

  List<FindAllChannelModel> findAll();

  Optional<Integer> getMaxId();

  int update(Channel channel);

  void delete(int id);
}
