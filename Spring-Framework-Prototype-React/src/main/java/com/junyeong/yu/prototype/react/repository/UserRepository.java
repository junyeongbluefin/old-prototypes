package com.junyeong.yu.prototype.react.repository;

import com.junyeong.yu.prototype.react.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{

}