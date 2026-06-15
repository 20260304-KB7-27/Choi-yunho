package lecture.after;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component // ComponentScan을 위한
@Primary
public class EmailSender {

    public void send (String message) {
        System.out.println("이메일 발송 : " + message);
    }
}
