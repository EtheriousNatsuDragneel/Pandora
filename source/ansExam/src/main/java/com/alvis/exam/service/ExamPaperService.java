package com.alvis.exam.service;

import com.alvis.exam.domain.ExamPaper;
import com.alvis.exam.domain.User;
import com.alvis.exam.viewmodel.admin.exam.ExamPaperEditAllRequestVM;
import com.alvis.exam.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.alvis.exam.viewmodel.admin.exam.ExamPaperPageRequestVM;
import com.alvis.exam.viewmodel.student.dashboard.PaperFilter;
import com.alvis.exam.viewmodel.student.dashboard.PaperInfo;
import com.alvis.exam.viewmodel.student.exam.ExamPaperPageVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ExamPaperService extends BaseService<ExamPaper> {

    PageInfo<ExamPaper> page(ExamPaperPageRequestVM requestVM);

    PageInfo<ExamPaper> taskExamPage(ExamPaperPageRequestVM requestVM);

    PageInfo<ExamPaper> studentPage(ExamPaperPageVM requestVM);

    ExamPaper savePaperFromVM(ExamPaperEditRequestVM examPaperEditRequestVM, User user);

    Integer editAllPaperFromVM(ExamPaperEditAllRequestVM examPaperEditAllRequestVM, User user);

    ExamPaperEditRequestVM examPaperToVM(Integer id);

    List<PaperInfo> indexPaper(PaperFilter paperFilter);

    List<Long> paperIDList();

    boolean getUserCount(Integer userId);

    Integer selectAllCount();

    List<Integer> selectMothCount();

    boolean getCount(Integer id);
}
