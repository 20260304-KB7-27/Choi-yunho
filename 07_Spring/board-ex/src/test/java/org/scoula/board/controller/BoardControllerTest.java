package org.scoula.board.controller;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.service.BoardService;
import org.scoula.config.RootConfig;
import org.scoula.config.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class})
@WebAppConfiguration // 웹 환경 테스트임을 선언, MockMVC 활성화
@Log4j2
class BoardControllerTest {
    @Autowired
    BoardService service;

    @Autowired
    WebApplicationContext ctx; // 웹 어플리케이션 컨텍스트 -> mockMVC 만들 때 기반으로 사용

    // 실제서버를 사용하지 않고 컨트롤러를 호출/검증할 수 있게 해준느 가짜 MVC 객체
    private MockMvc mockMvc;

    @BeforeEach // 각 테스트 실행 전에
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build(); // MockMvc 객체 생성
    }

    @Test
    public void list() throws Exception {
        // mockMvc.perform : 테스트코드에서 브라우저 대신 컨트롤러에 HTTP 요청을 보내는 메서드
        log.info(String.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")) // ResultActions 리턴
                .andReturn() // 요청 처리 결과 -> MvcResult
                .getModelAndView() // 컨트롤러가 변환한 뷰 + 모델 -> ModelAndView
                .getModelMap()) // 뷰에 전달되는 모델 데이터 -> Model(ModelMap)
        );
    }
}