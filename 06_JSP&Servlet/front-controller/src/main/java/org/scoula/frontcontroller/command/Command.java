package org.scoula.frontcontroller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    // 메서드 참조용
    // 각 요청별 처리 메서드들은 request, response 객체를 받아서 사용
    String execute(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
