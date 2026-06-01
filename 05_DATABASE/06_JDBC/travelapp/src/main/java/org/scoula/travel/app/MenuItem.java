package org.scoula.travel.app;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuItem {
    String title;
    Runnable command;   // 함수형 인터페이스
}