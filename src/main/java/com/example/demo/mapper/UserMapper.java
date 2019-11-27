package com.example.demo.mapper;

import org.springframework.stereotype.Repository;

import com.example.demo.bean.User;

@Repository
public interface UserMapper {
	User SelById(String id);

	Integer Edit(User user);
}
