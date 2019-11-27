package com.example.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.bean.Boke;

@Repository
public interface BokesMapper {
	/***
	 * 查找已启用的
	 * @return
	 */
	List<Boke> SelEnabled();

	/**
	 * 查找所有博客
	 * @return
	 */
	List<Boke> SelAll();

	Integer add(Boke boke);

    /**
     * 批量删除
     * @param ids
     * @return
     */
	Integer delMore(List<Integer> ids);

    /**
     * 单独删除
     * @param id
     * @return
     */
	Integer del(Integer id);

    /**
     * 修改是否启用
     * @param id
     * @param is_enabled
     * @return
     */
	Integer editEnabled(Integer id,Integer is_enabled);

    /**
     * 改
     * @param boke
     * @return
     */
	Integer update(Boke boke);

	/**
	 * 查
	 * 根据标题查找
	 * @param title
	 * @return
	 */
	List<Boke> selByTitle(String title);

	/**
	 * 查
	 * 根据ID查找
	 * @param id
	 * @return
	 */
	Boke selById(Integer id);
}
