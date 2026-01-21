package com.store_sample.store.infrastructure.jpa.channel_members;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaChannelMemberRepository extends JpaRepository<TblChannelMembers, Integer> {

  List<TblChannelMembers> findByChannelId(int id);

}
