package com.epsoft.msg.support.dao;

import com.epsoft.msg.support.domain.MessageTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageTemplateDAO extends JpaRepository<MessageTemplate,Long> {

    /**
     * 查询 列表 （分页）
     * @param deleted 0：未删除，1：删除
     * @param pageable 分页对象
     * @return
     */
    List<MessageTemplate> findAllByIsDeletedEquals(Integer deleted, Pageable pageable);


    /**
     * 统计未删除的条数
     * @param deleted
     * @return
     */
    Long countByIsDeletedEquals(Integer deleted);

}
