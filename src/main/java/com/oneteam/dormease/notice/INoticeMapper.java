package com.oneteam.dormease.notice;

import com.oneteam.dormease.board.BoardDto;
import com.oneteam.dormease.utils.UploadFileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface INoticeMapper {
    List<NoticeDto> selectAllNoticeContent(Map<String, Object> map);

    int selectCountOfNotice(Map<String, Object> map);

    int updateContentHit(int no);

    NoticeDto selectDetailContent(int no);

    List<UploadFileDto> selectUploadedFiles(int no);
}
