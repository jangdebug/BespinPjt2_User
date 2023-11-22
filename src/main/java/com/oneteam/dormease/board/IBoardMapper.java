package com.oneteam.dormease.board;

import com.oneteam.dormease.utils.UploadFileDto;
import com.oneteam.dormease.utils.pagination.Criteria;
import com.oneteam.dormease.utils.pagination.PageMakerDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IBoardMapper {

    int insertNewContent(Map<String, Object> boardDtoMap);

    int insertNewFile(List<UploadFileDto> UploadFileDtos);

    int selectCurrentBoardNo(int no);

    List<BoardDto> selectAllFreeBoardContent(Map<String, Object> pageMakerDto);

    int updateContentHit(int no);

    BoardDto selectDetailContent(int no);

    List<UploadFileDto> selectUploadedFiles(int no);

    int deleteFilesForModify(int boardNoForModify);

    int updateContent(BoardDto boardDto);

    int updateContentForDelete(int no);

    int selectCountOfContent(Map<String, Object> map);
}
