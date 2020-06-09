package com.restServices.UdemyRestServices.io.repositries;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.restServices.UdemyRestServices.io.Entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
   UserEntity findUserByEmail(String email);
   UserEntity findByUserId(String userId);
}
