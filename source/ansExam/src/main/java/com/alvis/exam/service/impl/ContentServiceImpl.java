package com.alvis.exam.service.impl;

import com.alvis.exam.domain.Content;
import com.alvis.exam.repository.ContentMapper;
import com.alvis.exam.service.ContentService;
import com.alvis.exam.utility.ModelMapperSingle;
import com.alvis.exam.viewmodel.admin.content.ContentEditRequestVM;
import com.alvis.exam.viewmodel.admin.content.ContentPageRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl extends BaseServiceImpl<Content> implements ContentService {

    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();
    private final ContentMapper contentMapper;

    @Autowired
    public ContentServiceImpl(ContentMapper contentMapper) {
        super(contentMapper);
        this.contentMapper = contentMapper;
    }

    @Override
    public PageInfo<Content> page(ContentPageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(() ->
                contentMapper.page(requestVM)
        );
    }

    @Override
    public Content insertFullContent(ContentEditRequestVM model, Integer userId) {
        Date now = new Date();
        Content content = new Content();
        content.setContent(model.getContent());
        content.setUserLevel(model.getUserLevel());
        content.setCreateTime(now);
        content.setDoTime(model.getDoTime());
        content.setStormNumber(model.getStormNumber());
        content.setTitle(model.getTitle());
        content.setDeleted(false);
        contentMapper.insertSelective(content);
        return content;
    }

    @Override
    public Content updateFullContent(ContentEditRequestVM model) {
        return null;
    }

    @Override
    public ContentEditRequestVM getContentEditRequestVM(Integer contentId) {
        return null;
    }

    @Override
    public ContentEditRequestVM getContentRequestVM(Content content) {
        return null;
    }

    @Override
    public Integer selectAllCount() {
        return null;
    }

    @Override
    public List<Integer> selectMothCount() {
        return null;
    }
}
