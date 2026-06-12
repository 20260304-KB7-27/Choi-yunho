package org.scoula.frontcontroller;

import org.scoula.frontcontroller.command.Command;
import org.scoula.frontcontroller.controller.HomeController;
import org.scoula.frontcontroller.controller.TodoController;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "frontControllerServlet", value = "/")
public class FrontControllerServlet extends HttpServlet {

    // URL과 command
    Map<String, Command> getMap; // get 요청들
    Map<String, Command> postMap; // post 요청들

    // jsp 경로를 나타낸 접두사/접미서 (반복 되는 코드)
    String prefix = "/WEB-INF/views/";
    String suffix = ".jsp";

    HomeController homeController = new HomeController();
    TodoController todoController = new TodoController();

    //    GET /hello -> 실질적으로 동작할 메서드 객체로
    public void init() {
        getMap = new HashMap<>();
        postMap = new HashMap<>();

        // GET 요청 등록
        getMap.put("/", homeController::getIndex);

        // GET 매핑
        getMap.put("/todo/list", todoController::getList);
        getMap.put("/todo/view", todoController::getView);
        getMap.put("/todo/create", todoController::getCreate);
        getMap.put("/todo/update", todoController::getUpdate);

        // POST 매핑
        postMap.put("/todo/create", todoController::postCreate);
        postMap.put("/todo/update", todoController::postUpdate);
        postMap.put("/todo/delete", todoController::postDelete);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Command command = getCommand(request);

        if (command != null) {
            execute(command, request, response);
        } else {
            String view = prefix + "404" + suffix;

            request.getRequestDispatcher(view).forward(request, response);
        }


    }

    // request객체에서 명령어에 해당하는 url 찾아오기
    private String getCommandName(HttpServletRequest request) {

        // /contextPath/hello
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        // contextPath 꺼내기
        String contextPath = request.getContextPath();
        System.out.println("contextPath = " + contextPath);

        // requestURI 에서 contextPath 제외
        String commandName = requestURI.substring(contextPath.length());
        System.out.println("commandName = " + commandName);

        return commandName;
    }

    // Map 안에서 command 꺼내오는 메서드
    private Command getCommand(HttpServletRequest request) {

        Command command;

        String commandName = getCommandName(request);

        if (request.getMethod().equalsIgnoreCase("GET")) {
            // get 요청일때
            command = getMap.get(commandName);
        } else {
            // get 요청이 아닐때
            command = postMap.get(commandName);
        }

        return command;
    }

    // 매개변수로 받은 command를 request, response를 담아 실행
    // command를 실행시켜 forward 해야할 jsp 파일 경로 전달.
    public void execute(Command command, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String viewName = command.execute(request, response);

        if (viewName.startsWith("redirect:")) {
            // redirect 로직

            response.sendRedirect(viewName.substring("redirect:".length()));
        } else {
            // forward 처리
//        System.out.println("viewName = " + viewName);
            String view = prefix + viewName + suffix;
            // "WEB-INF/views/index.jsp"
            request.getRequestDispatcher(view).forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp); // doPost메소드가 호출되면 doGet으로 전달
    }
}