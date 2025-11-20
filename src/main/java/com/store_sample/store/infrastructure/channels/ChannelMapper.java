package com.store_sample.store.infrastructure.channels;

import com.store_sample.store.domain.channels.model.FindAllChannelModel;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChannelMapper {

  void insert(TblChannels channel);

  List<FindAllChannelModel> findAll();

  Optional<Integer> getMaxId();

  void update(TblChannels channel);

  void delete(int id);
}
