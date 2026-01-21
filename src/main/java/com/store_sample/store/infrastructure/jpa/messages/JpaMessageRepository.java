package com.store_sample.store.infrastructure.jpa.messages;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMessageRepository extends JpaRepository<TblMessages, Integer> {

}
