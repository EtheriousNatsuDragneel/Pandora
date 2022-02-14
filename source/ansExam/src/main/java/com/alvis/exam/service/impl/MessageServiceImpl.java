package com.alvis.exam.service.impl;

import com.alvis.exam.domain.ExamPaperAnswer;
import com.alvis.exam.domain.Message;
import com.alvis.exam.domain.MessageUser;
import com.alvis.exam.domain.User;
import com.alvis.exam.repository.MessageMapper;
import com.alvis.exam.repository.MessageUserMapper;
import com.alvis.exam.service.ExamPaperAnswerService;
import com.alvis.exam.service.MessageService;
import com.alvis.exam.service.UserService;
import com.alvis.exam.utility.ExamUtil;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageMapper messageMapper;
    private final MessageUserMapper messageUserMapper;
    private final ExamPaperAnswerService examPaperAnswerService;
    private final UserService userService;


    @Override
    public List<Message> selectMessageByIds(List<Integer> ids) {
        return messageMapper.selectByIds(ids);
    }

    @Override
    public PageInfo<MessageUser> studentPage(MessageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(() ->
                messageUserMapper.studentPage(requestVM)
        );
    }

    @Override
    public PageInfo<Message> page(MessagePageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(() ->
                messageMapper.page(requestVM)
        );
    }

    @Override
    public List<MessageUser> selectByMessageIds(List<Integer> ids) {
        return messageUserMapper.selectByMessageIds(ids);
    }

    @Override
    @Transactional
    public void sendMessage(Message message, List<MessageUser> messageUsers) {
        messageMapper.insertSelective(message);
        messageUsers.forEach(d -> d.setMessageId(message.getId()));
        messageUserMapper.inserts(messageUsers);
    }

    @Override
    @Transactional
    public void read(Integer id) {
        MessageUser messageUser = messageUserMapper.selectByPrimaryKey(id);
        if (messageUser.getReaded())
            return;
        messageUser.setReaded(true);
        messageUser.setReadTime(new Date());
        messageUserMapper.updateByPrimaryKeySelective(messageUser);
        messageMapper.readAdd(messageUser.getMessageId());
    }

    @Override
    public Integer unReadCount(Integer userId) {
        return messageUserMapper.unReadCount(userId);
    }

    @Override
    public Message messageDetail(Integer id) {
        MessageUser messageUser = messageUserMapper.selectByPrimaryKey(id);
        return messageMapper.selectByPrimaryKey(messageUser.getMessageId());
    }

    @Override
    public void sendMessageForUser(ExamPaperAnswer examPaperAnswer) {
        ExamPaperAnswer examPaper  = examPaperAnswerService.selectById(examPaperAnswer.getId());
        List<Integer> receiveUserIds = new ArrayList<>();
        receiveUserIds.add(examPaper.getCreateUser());
        List<User> receiveUser = userService.selectByIds(receiveUserIds);
        Date now = new Date();
        Message message = new Message();
        message.setTitle("潘星人考试成绩");
        message.setContent("本次考试分数为："+ ExamUtil.scoreToVM(examPaper.getUserScore()));
        message.setCreateTime(now);
        message.setReadCount(0);
        message.setReceiveUserCount(1);
        message.setSendUserId(2);
        message.setSendUserName("潘星人考官");

        List<MessageUser> messageUsers = receiveUser.stream().map(d -> {
            MessageUser messageUser = new MessageUser();
            messageUser.setCreateTime(now);
            messageUser.setReaded(false);
            messageUser.setReceiveRealName(d.getRealName());
            messageUser.setReceiveUserId(d.getId());
            messageUser.setReceiveUserName(d.getUserName());
            return messageUser;
        }).collect(Collectors.toList());


        sendMessage(message, messageUsers);

    }

}
