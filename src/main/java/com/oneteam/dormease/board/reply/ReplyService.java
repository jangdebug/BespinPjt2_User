package com.oneteam.dormease.board.reply;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class ReplyService {
    private final IReplyMapper replyMapper;
    public ReplyService (IReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

    public Object registReply(ReplyDto replyDto) {
        log.info("registReply()");

        Map<String, Object> resultMap = new HashMap<>();
        System.out.println("=====>" + replyDto);
        int result = replyMapper.insertReply(replyDto);
        List<ReplyDto> replyDtos = null;


        if (result > 0) {
            log.info("REGIST COMMENT SUCCESS!!");
            replyDtos = replyMapper.selectReplies(replyDto.getBoard_no());
        } else {
            log.info("REGIST COMMENT FAIL!!");
        }

        resultMap.put("result", result);
        resultMap.put("replyDtos", replyDtos);

        return resultMap;
    }
}
