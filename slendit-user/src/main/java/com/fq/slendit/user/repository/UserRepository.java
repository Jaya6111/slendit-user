package com.fq.slendit.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fq.slendit.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
