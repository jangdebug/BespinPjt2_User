package com.oneteam.dormease.notice;

import com.oneteam.dormease.utils.UploadFileDto;
import com.oneteam.dormease.utils.pagination.Criteria;
import com.oneteam.dormease.utils.pagination.PageMakerDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class NoticeService {
    private final INoticeMapper noticeMapper;
    public NoticeService (INoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    public Map<String, Object> getAllNoticeContent(String schoolNo, String keyWord, String search, int pageNum, int amount) {
        log.info("getAllNoticeContent()");
        Criteria criteria = new Criteria(pageNum, amount);
        Map<String, Object> map = new HashMap<>();
        map.put("keyWord", keyWord);
        map.put("search", search);
        map.put("schoolNo", schoolNo);
        int totalCnt = noticeMapper.selectCountOfNotice(map);
        PageMakerDto pageMakerDto = new PageMakerDto(schoolNo, criteria, totalCnt);
        map.put("pageMakerDto", pageMakerDto);
        List<NoticeDto> noticeDtos = noticeMapper.selectAllNoticeContent(map);
        map.remove("schoolNo");
        map.put("noticeDtos", noticeDtos);

        return map;
    }

    public Map<String, Object> getDetailNotice(int no) {
        log.info("getDetailNotice()");

        NoticeDto noticeDto = new NoticeDto();
        List<UploadFileDto> uploadedFiles = new ArrayList<>();
        Map<String, Object> noticeMap = new HashMap();
        int result = noticeMapper.updateContentHit(no);

        if (result > 0) {
            noticeDto = noticeMapper.selectDetailContent(no);
            uploadedFiles = noticeMapper.selectUploadedFiles(no);
        }
        noticeMap.put("noticeDto", noticeDto);
        noticeMap.put("uploadedFiles", uploadedFiles);

        return noticeMap;
    }
}
