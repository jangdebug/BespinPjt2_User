package com.oneteam.dormease.utils.ckeditor;

import com.oneteam.dormease.utils.UploadFileDto;
import com.oneteam.dormease.utils.UploadFileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@Controller
@Tag(name = "Image", description = "이미지 API")
public class ImageController {
    private final ImageService imageService;
    private final UploadFileService uploadFileService;
    public ImageController(ImageService imageService, UploadFileService uploadFileService) {
        this.imageService = imageService;
        this.uploadFileService = uploadFileService;
    }
    @PostMapping(value = "/image/upload")
    public ModelAndView imageUpload(MultipartHttpServletRequest request) throws Exception {
        log.info("imageUpload");

        // ckeditor는 이미지 업로드 후 이미지 표시하기 위해 uploaded 와 url을 json 형식으로 받아야 함
        // modelandview를 사용하여 json 형식으로 보내기위해 모델앤뷰 생성자 매개변수로 jsonView 라고 써줌
        // jsonView 라고 쓴다고 무조건 json 형식으로 가는건 아니고 @Configuration 어노테이션을 단
        ModelAndView mv = new ModelAndView("jsonView");

        // ckeditor 에서 파일을 보낼 때 upload : [파일] 형식으로 해서 넘어오기 때문에 upload라는 키의 밸류를 받아서 uploadFile에 저장함
        MultipartFile uploadFile = request.getFile("upload");
        String newFileName = imageService.imageUpload(uploadFile);
//        UploadFileDto uploadFileDto = imageService.upload(uploadFile);
//        String newFileName = uploadFileDto.getBoard_attach_file();

        // uploaded, url 값을 modelandview를 통해 보냄
        mv.addObject("uploaded", true);
        mv.addObject("url", "/UploadImg/" + newFileName);

        return mv;
    }
}
