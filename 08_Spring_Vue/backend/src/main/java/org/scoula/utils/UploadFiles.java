package org.scoula.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

@Slf4j
public class UploadFiles {
    public static final String BASE_DIR = "/Users/youknow/Desktop/Projects/KB/fullstack/Choi-yunho/08_Spring_Vue/upload";

    public static String upload(String baseDir, MultipartFile part) throws IOException {


        // 기본 디렉토리 확인, 없으면 생성
        File base = new File(baseDir);
        if(!base.exists()) {
            base.mkdirs(); // 상위 디렉토리까지 포함해서 생성
        }

        // 원본 파일명 가져오기
        String filename = part.getOriginalFilename();

        // 저장할 파일 객체 생성
        File dest = new File(baseDir, UploadFileName.getUniqueName(filename));

        part.transferTo(dest); // 업로드된 파일을 실제 서버 디렉토리에 저장 (물리파일생성)

        return dest.getPath(); // 저장된 파일 경로
    }

    // size Long -> 문자열로 포맷팅해서 보여주는 메서드
    public static String getFormatSize(Long size) {
        if (size <= 0)
            return "0";
        final String[] units = new String[] { "Bytes", "KB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public static void download(HttpServletResponse response, File file, String orgName) throws IOException {

        response.setContentType("application/download"); // 응답으로 보내는 데이터는 다운로드용 파일이다.
//        response.setContentType("image/png"); // 응답으로 보내는 데이터는 다운로드용 파일이다.

        response.setContentLength((int)file.length());

        String filename = URLEncoder.encode(orgName, "UTF-8"); // 인코딩

        // Content-disposition : 파일처리방식을 지정하는 HTTP Header (inline/attachment)
        response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
//        response.setHeader("Content-Disposition", "inline;filename=\"" + filename + "\"");

        try(OutputStream os = response.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os)) {

            Files.copy(Paths.get(file.getPath()), bos);
        }

    }

    // 아바타 이미지 전송용
    public static void downloadImage(HttpServletResponse response, File file) {
        try {
            Path path = Path.of(file.getPath());
            String mimeType = Files.probeContentType(path); // 파일 확장자로 mimetype 추정
            log.info("🍟mimeType : {}", mimeType);
            //응답 Content_type을 파일타입으로 지정하면 브라우저가 이미지를 바로 렌더링
            response.setContentType(mimeType);
            response.setContentLength((int) file.length()); // 응답바디 크기 지정

            try (
                OutputStream os = response.getOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(os)){

                Files.copy(path, bos); // 서버 파일을 응답 스트림으로 복사 (전송)
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
