package com.alvis.exam.repository;

import com.alvis.exam.domain.Content;
import com.alvis.exam.viewmodel.admin.content.ContentPageRequestVM;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentMapper extends BaseMapper<Content>  {
    int deleteByPrimaryKey(Long id);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKeyWithBLOBs(Content record);

    int updateByPrimaryKey(Content record);

    List<Content> page(ContentPageRequestVM requestVM);

}