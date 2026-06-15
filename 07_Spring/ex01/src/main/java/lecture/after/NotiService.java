package lecture.after;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 서비스 로직
@Service
public class NotiService {

    private final EmailSender emailSender;

    @Autowired
    public NotiService(EmailSender emailSender) {
        // 매개변수 -> Spring이 이 Bean을 주입해줌 -> DI , SMSSender는 EmailSender를 상속했으므로 SMSSender의 객체가 들어가도 에러 X
        this.emailSender = emailSender;
    }

    public void notify(String message) {
        emailSender.send(message);
    }
}
