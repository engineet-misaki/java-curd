package com.store_sample.store.infrastructure.channel_members;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaChannelMemberRepository extends JpaRepository<TblChannelMembers, Integer> {

}
