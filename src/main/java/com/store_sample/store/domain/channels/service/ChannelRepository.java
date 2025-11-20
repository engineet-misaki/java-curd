package com.store_sample.store.domain.channels.service;

import com.store_sample.store.domain.channels.model.FindAllChannelModel;
import com.store_sample.store.infrastructure.channels.TblChannels;
import java.util.List;
import java.util.Optional;

public interface ChannelRepository {

  void insert(TblChannels channel);

  List<FindAllChannelModel> findAll();

  Optional<Integer> getMaxId();

  void update(TblChannels channel);

  void delete(int id);
}
