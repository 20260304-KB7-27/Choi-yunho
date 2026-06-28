package org.scoula.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class UploadFiles {

    public static String upload(String baseDir, MultipartFile part) throws IOException {

        File base = new File(baseDir);
        if (!base.exists()) {
            base.mkdir(); // 존재하지 않는 디렉토리 생성
        }

        // 원본 파일명 가져오기[
        String filename = part.getOriginalFilename();

        // 저장할 파일 객체 생성
        File dest = new File(baseDir, UploadFileName.getUniqueName(filename));

        part.transferTo(dest); // 업로드된 파일을 실제 서버 디렉토리에 저장 (물리파일생성)

        return dest.getPath(); // 저장된 파일 경로
    }

    // size long -> 문자열로 포맷팅해서 보여주는 메서드
    public static String getFormatSize(Long size) {
        if (size <= 0)
            return "0";
        final String[] units = new String[] { "Bytes", "KB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public static void download(HttpServletResponse response, File file, String orgName) throws IOException {

        response.setContentType("application/download"); // 응답으로 보내는 데이터는 다운로드용 파일이다.
//        response.setContentType("image/jpg"); // 응답으로 보내는 데이터는 다운로드용 파일이다.

        response.setContentLength((int) file.length());

        String filename = URLEncoder.encode(orgName, "UTF-8"); // 인코딩

        // Content-Disposition : 파일처리방식을 지정하는 HTTP Header (inline/attachment)
        response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");

        try (OutputStream os = response.getOutputStream();
             BufferedOutputStream bos = new BufferedOutputStream(os)) {

            Files.copy(Paths.get(file.getPath()), bos);
        }
    }
}
