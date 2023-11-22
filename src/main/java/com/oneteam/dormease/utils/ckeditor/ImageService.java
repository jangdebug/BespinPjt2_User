package com.oneteam.dormease.utils.ckeditor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;
@Log4j2
@Service
public class ImageService {

    private String uploadDir = "c:\\localImage\\";
    public String imageUpload(MultipartFile uploadFile) throws Exception {

        boolean result = false;

        String fileOriName = uploadFile.getOriginalFilename();
        String fileExtension = fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length());

        UUID uuid = UUID.randomUUID();
        String uniqueName = uuid.toString().replace("-", "");

        File saveFile = new File(uploadDir + "\\" + uniqueName + fileExtension);
            saveFile.mkdirs();
            uploadFile.transferTo(saveFile);
//        if (!saveFile.exists())
//        try {
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (result) {
//            log.info("FILE UPLOAD SUCCESS!!");
//            uploadFile.transferTo(saveFile);
//        }

        return uniqueName + fileExtension;
    }
}
