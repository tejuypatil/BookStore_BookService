package com.devlopment.bookstore_bookservice.repository;

import com.devlopment.bookstore_bookservice.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserData,Integer> {
}
