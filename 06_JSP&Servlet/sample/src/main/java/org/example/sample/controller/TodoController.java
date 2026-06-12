package org.example.sample.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class TodoController {
    @GetMapping("/list")
    public String getList(HttpServletRequest request, HttpServletResponse response) {
        List<String> list = Arrays.asList("Todo 1", "Todo 2", "Todo 3");
        request.setAttribute("todoList", list);

        System.out.println("GET /todo/Lists");

        return "/todo/list";
    }

    // Todo 상세보기 처리 (GET /todo/view)
    public String getView(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("GET /todo/view");
        return "todo/view";
    }

    // Todo 생성 폼 표시 (GET /todo/create)
    public String getCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("GET /todo/create");
        return "todo/create";
    }

    // Todo 생성 처리 (POST /todo/create)
    public String postCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("POST /todo/create");
        return "redirect:/todo/list";
    }

    // Todo 수정 폼 표시 (GET /todo/update)
    public String getUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("GET /todo/update");
        return "todo/update";
    }

    // Todo 수정 처리 (POST /todo/update)
    public String postUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("POST /todo/update");
        return "redirect:/todo/list";
    }

    // Todo 삭제 처리 (POST /todo/delete)
    public String postDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("POST /todo/delete");
        return "redirect:/todo/list";
    }
}