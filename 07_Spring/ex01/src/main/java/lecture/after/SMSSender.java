package lecture.after;

import lecture.after.EmailSender;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary // 동일한 타입의 여러 빈 중에 우선 주입됨
public class SMSSender extends EmailSender { // 다형성을 위한 상속

    // 문자 보내는 기능
    @Override
    public void send(String message) {
        System.out.println("SMS 발송 : " + message);
    }
}
