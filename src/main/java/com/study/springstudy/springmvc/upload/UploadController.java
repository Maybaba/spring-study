package com.study.springstudy.springmvc.upload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
public class UploadController {

    @GetMapping("/upload/form")
    public String uploadForm() {
        return "upload/upload-form";
    }

    @PostMapping("/upload/file")
    public String uploadFile(@RequestParam("thumbnail") MultipartFile file) {

        log.info(file.getOriginalFilename());
        log.info(String.valueOf(file.getSize() / 1024.0 / 1024.0)); //메가바이트로 변환
        log.info(file.getContentType());


        return "redirect:/upload-form";
    }
}
