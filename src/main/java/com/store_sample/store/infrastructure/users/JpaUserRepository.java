package com.store_sample.store.infrastructure.users;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<TblUsers, Integer> {

  Optional<TblUsers> findById(int id);

}
