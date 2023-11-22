package com.oneteam.dormease.board;

import com.oneteam.dormease.user.parents.ParentsDto;
import com.oneteam.dormease.user.student.StudentDto;
import com.oneteam.dormease.utils.UploadFileDto;
import com.oneteam.dormease.utils.UploadFileService;
import com.oneteam.dormease.utils.pagination.PageDefine;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/board")
@Tag(name = "Board", description = "자유게시판 API")
public class BoardController {
    private final BoardService boardService;
    private final UploadFileService uploadFileService;
    public BoardController(BoardService boardService, UploadFileService uploadFileService) {
        this.boardService = boardService;
        this.uploadFileService = uploadFileService;
    }

    /**
     * [VIEW] 자유 게시판 리스트
     *
     * @param 어떤 부분 검색, 키워드, 페이지 넘버, 양
     * @return 페이지
     */

    /*
     * 자유 게시판 게시글 리스트 페이지
     */
    @GetMapping("/freeBoardListForm")
    @Operation(summary = "자유 게시판 게시글 리스트 페이지", description = "자유 게시판 게시글 리스트 페이지", tags = {"View"})
    public String freeBoardListForm(HttpSession session, Model model,
                                    @RequestParam(value="keyWord", required = false) String keyWord,
                                    @RequestParam(value="search", required = false) String search,
                                    @RequestParam(value="pageNum", required = false, defaultValue = PageDefine.DEFAULT_PAGE_NUMBER) int pageNum,
                                    @RequestParam(value="amount", required = false, defaultValue = PageDefine.DEFAULT_AMOUNT) int amount) {
        log.info("freeBoardListForm()");
        StudentDto loginedStudentDto = (StudentDto) session.getAttribute("loginedStudentDto");
        ParentsDto loginedParentsDto = (ParentsDto) session.getAttribute("loginedParentsDto");
        String schoolNo = null;
        if (loginedStudentDto != null) {
            schoolNo = loginedStudentDto.getSchool_no();
        } else if (loginedParentsDto != null) {
            schoolNo = loginedParentsDto.getSchool_no();
        }
        Map<String, Object> resultMap = boardService.getAllFreeBoardContent(schoolNo,keyWord, search, pageNum, amount);
        String nextPage = "board/freeBoardListForm";
        model.addAttribute("boardDtos", resultMap.get("boardDtos"));
        model.addAttribute("pageMakerDto", resultMap.get("pageMakerDto"));
        model.addAttribute("search", resultMap.get("search"));
        model.addAttribute("keyWord", resultMap.get("keyWord"));

        return nextPage;
    }

    /*
     * 게시글 디테일 페이지
     */
    @GetMapping("/detailContentForm")
    public String detailContentForm(@RequestParam("no") int no, Model model, HttpSession session) {
        log.info("detailContentForm()");
        ParentsDto loginedParentsDto = (ParentsDto) session.getAttribute("loginedParentsDto");
        if (loginedParentsDto != null) {
            int ReadAllow = 1;
            Model checkForReadAllow = null;
            checkForReadAllow.addAttribute("ReadAllow" , ReadAllow);
            return "board/accountNotAllowed";
        }
        String nextPage = "board/detailContentForm";
        Map<String, Object> boardAndReplyMap = boardService.getDetailContent(no);
        model.addAttribute("boardAndReplyMap", boardAndReplyMap);

        return nextPage;
    }

    /*
     * 게시글 작성 페이지
     */
    @GetMapping("/writeContentForm")
    public String writeContentForm(HttpSession session, Model model) {
        log.info("writeContentForm()");
        ParentsDto loginedParentsDto = (ParentsDto) session.getAttribute("loginedParentsDto");
        if (loginedParentsDto != null) {
            int WriteAllow = 1;
            model.addAttribute("WriteAllow" , WriteAllow);
            return "board/accountNotAllowed";
        }
        String nextpage = "board/writeContentForm";

        return nextpage;
    }

    /*
     * 게시글 작성 컨펌
     */
    @PostMapping("/writeContentConfirm")
    @Operation(summary = "게시글 작성 확인", description = "게시글 작성 확인")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "ok"),
//            @ApiResponse(code = 404, message = "page not found!")
//    })
    public String writeContentConfirm(@RequestParam(value = "files", required = false)  List<MultipartFile> files,
                                      HttpSession session,
                                      Model model,
                                      BoardDto boardDto) {
        log.info("writeContentConfirm()");
        StudentDto loginedStudentDto = (StudentDto) session.getAttribute("loginedStudentDto");
        int result = -1;

        try {
            List<UploadFileDto> uploadedFileDtos = new ArrayList<>();
            if (files != null && !files.isEmpty()) {
                for (MultipartFile file : files) {
                    String fileOriName = file.getOriginalFilename();
                    // SAVE FILES
                    if (fileOriName != "") {
                        UploadFileDto uploadedFileDto = uploadFileService.upload(loginedStudentDto.getId(), file);
                        uploadedFileDto.setBoard_attach_file(uploadedFileDto.getBoard_attach_file());
                        if (uploadedFileDto != null) {
                            // 업로드된 파일 정보를 리스트에 추가
                            uploadedFileDtos.add(uploadedFileDto);
                        }
                    }
                }
            }
            result = boardService.writeContentConfirm(loginedStudentDto, boardDto, uploadedFileDtos);
        } catch(Exception e) {
            e.printStackTrace();
        }
        // 업로드한 파일들을 처리
        String nextpage = "board/writeContentResult";
        model.addAttribute("result", result);

        return nextpage;
    }

    /*
     * 게시글 수정 페이지
     */
    @PostMapping("/modifyContentForm")
    public String modifyContentForm(@RequestParam(value = "board_no", required = false) int no, Model model) {
        log.info("modifyContentForm()");
        String nextPage = "board/modifyContentForm";
        Map<String, Object> boardAndReplyMap = boardService.getdetailContentForModify(no);
        model.addAttribute("boardAndReplyMap", boardAndReplyMap);

        return nextPage;
    }

    /*
     * 게시글 수정 컨펌
     */
    @PostMapping("/modifyContentConfirm")
    public String modifyContentConfirm(@RequestParam(value = "files", required = false)  List<MultipartFile> files,
                                       @RequestParam(value = "board_attach_file", required = false) List<String> board_attach_file,
                                       HttpSession session,
                                       BoardDto boardDto,
                                       Model model) {
        log.info("modifyContentConfirm()");
        StudentDto loginedStudentDto = (StudentDto) session.getAttribute("loginedStudentDto");
        String nextPage = "board/modifyContentResult";
        int result = -1;
        if (loginedStudentDto.getNo() == boardDto.getStudent_no()) {
            try {
                List<UploadFileDto> uploadedFileDtos = new ArrayList<>();
                if (files != null && !files.isEmpty()) {
                    for (MultipartFile file : files) {
                        String fileOriName = file.getOriginalFilename();
                        // SAVE FILES
                        if (fileOriName != "") {
                            UploadFileDto uploadedFileDto = uploadFileService.upload(loginedStudentDto.getId(), file);
                            uploadedFileDto.setBoard_attach_file(uploadedFileDto.getBoard_attach_file());
                            if (uploadedFileDto != null) {
                                // 업로드된 파일 정보를 리스트에 추가
                                uploadedFileDtos.add(uploadedFileDto);
                            }
                        }
                    }
                }
                result = boardService.modifyContentConfirm(boardDto, uploadedFileDtos, board_attach_file);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("result", result);

        return nextPage;
    }

    /*
     * 게시글 삭제 컨펌
     */
    @GetMapping("/deleteContentConfirm")
    public String deleteContentConfirm(@RequestParam(value = "board_no", required = false) int no, Model model) {
        log.info("deleteContentConfirm()");
        String nextPage = "board/deleteContentResult";
        int result = boardService.deleteContentConfirm(no);
        model.addAttribute("result", result);

        return nextPage;
    }
}
