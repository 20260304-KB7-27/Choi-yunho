package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Controller
@Log4j2
@RequestMapping("/example/multi")
public class MultipartController {
    @GetMapping
    public String exUpload() {
        return "page/upload_page";
    }

    /*
    * MultipartFile
    * - 파일 업로드를 처리할 때 사용하는 인터페이스
    * - multipart/form-data 형태로 전달된 파일을 객체로 다룰 수 있게 변환해줌
    */

    @PostMapping
    public void exUploadPost(ArrayList<MultipartFile> files) {
        for (MultipartFile file : files) {
            log.info("`````````````````````````");
            log.info("name : {}", file.getOriginalFilename());
            log.info("size : {}", file.getSize());
        }
    }
}
