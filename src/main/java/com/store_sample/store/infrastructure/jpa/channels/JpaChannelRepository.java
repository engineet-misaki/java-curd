package com.store_sample.store.infrastructure.jpa.channels;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaChannelRepository extends JpaRepository<TblChannels, Integer> {

  Optional<TblChannels> findById(int id);

}
