package com.oneteam.dormease.board.reply;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IReplyMapper {

    int insertReply(ReplyDto replyDto);

    List<ReplyDto> selectReplies(int no);
}
