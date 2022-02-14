package com.alvis.exam.service;

import com.alvis.exam.domain.Content;
import com.alvis.exam.viewmodel.admin.content.ContentEditRequestVM;
import com.alvis.exam.viewmodel.admin.content.ContentPageRequestVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ContentService extends BaseService<Content> {

    PageInfo<Content> page(ContentPageRequestVM requestVM);

    Content insertFullContent(ContentEditRequestVM model, Integer userId);

    Content updateFullContent(ContentEditRequestVM model);

    ContentEditRequestVM getContentEditRequestVM(Integer contentId);

    ContentEditRequestVM getContentRequestVM(Content content);

    Integer selectAllCount();

    List<Integer> selectMothCount();
}
