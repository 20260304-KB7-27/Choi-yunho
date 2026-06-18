package org.scoula.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// MyBatis 진영에서의 VO -> DB 테이블 한 행에 매핑되는 객체
@Data // getter, setter, toString, EqualsAndHashCode, RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardVO {
    private Long no;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private Date updateDate;
}
