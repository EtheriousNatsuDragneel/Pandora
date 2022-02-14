package com.alvis.exam.controller.admin;

import com.alvis.exam.base.BaseApiController;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.Content;
import com.alvis.exam.domain.Question;
import com.alvis.exam.service.ContentService;
import com.alvis.exam.service.QuestionService;
import com.alvis.exam.service.TextContentService;
import com.alvis.exam.utility.DateTimeUtil;
import com.alvis.exam.utility.PageInfoHelper;
import com.alvis.exam.viewmodel.admin.content.ContentEditRequestVM;
import com.alvis.exam.viewmodel.admin.content.ContentPageRequestVM;
import com.alvis.exam.viewmodel.admin.content.ContentResponseVM;
import com.alvis.exam.viewmodel.admin.question.QuestionEditRequestVM;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("AdminContentController")
@RequestMapping(value = "/api/admin/content")
@AllArgsConstructor
public class ContentController extends BaseApiController {

    private final QuestionService questionService;
    private final ContentService contentService;
    private final TextContentService textContentService;

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<ContentResponseVM>> pageList(@RequestBody ContentPageRequestVM model) {
        PageInfo<Content> pageInfo = contentService.page(model);
        PageInfo<ContentResponseVM> page = PageInfoHelper.copyMap(pageInfo, q -> {
            ContentResponseVM vm = modelMapper.map(q, ContentResponseVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(q.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RestResponse edit(@RequestBody @Valid ContentEditRequestVM model) {

        if (null == model.getId()) {
            contentService.insertFullContent(model, getCurrentUser().getId());
        } else {
            contentService.updateFullContent(model);
        }

        return RestResponse.ok();
    }

    @RequestMapping(value = "/select/{id}", method = RequestMethod.POST)
    public RestResponse<QuestionEditRequestVM> select(@PathVariable Integer id) {
        QuestionEditRequestVM newVM = questionService.getQuestionEditRequestVM(id);
        return RestResponse.ok(newVM);
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public RestResponse delete(@PathVariable Integer id) {
        Question question = questionService.selectById(id);
        question.setDeleted(true);
        questionService.updateByIdFilter(question);
        return RestResponse.ok();
    }

}
