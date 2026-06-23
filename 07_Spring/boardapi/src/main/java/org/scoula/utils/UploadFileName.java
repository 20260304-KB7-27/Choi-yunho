package org.scoula.utils;

// 고유한 파일명을 만드는 메소드 생성용
public class UploadFileName {
    public static String getUniqueName(String filename) {

        // image1.png -> image-현재시간(밀리초).png
        // 확장자 위치(index) 찾기용
        int ix = filename.lastIndexOf(".");

        String name = filename.substring(0, ix); // 파일명 추출
        String ext = filename.substring(ix); // 확장자 명

        return String.format("%s-%d.%s", name, System.currentTimeMillis(), ext);
    }
}
