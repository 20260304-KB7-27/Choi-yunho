package lecture.after;

import lecture.after.EmailSender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {


        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // Controller로 가됨
        // 클래스
        NotiService service = context.getBean(NotiService.class);

        service.notify("회원가입 축하합니다!");
    }
}
