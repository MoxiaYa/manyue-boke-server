package com.example.demo.mapper;

import com.example.demo.bean.Discuss;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论
 */
@Repository
public interface DiscussMapper {

    /**
     * 查找博客的评论
     * @param id
     * @return
     */
    List<Discuss> selByBoke(Integer id);

    /**
     * 发表评论
     * @return
     */
    Integer addDiscuss(Discuss dis);

    /**
     * 删除评论
     * @param id
     * @return
     */
    Integer delDiscuss(Integer id);
}
