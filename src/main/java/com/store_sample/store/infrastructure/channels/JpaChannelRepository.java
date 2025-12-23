package com.store_sample.store.infrastructure.channels;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaChannelRepository extends JpaRepository<TblChannels, Integer> {

  List<TblChannels> findById(int id);

}
