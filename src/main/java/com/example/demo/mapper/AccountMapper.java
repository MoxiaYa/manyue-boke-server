package com.example.demo.mapper;

import org.springframework.stereotype.Repository;

import com.example.demo.bean.Account;

@Repository
public interface AccountMapper {
	Account SelById(String id);
	/**
	 * 注册
	 * @param account
	 * @return
	 */
	Integer AddAccount(Account account);

	/**
	 * 修改
	 * @param account
	 * @return
	 */
	Integer update(Account account);
}
