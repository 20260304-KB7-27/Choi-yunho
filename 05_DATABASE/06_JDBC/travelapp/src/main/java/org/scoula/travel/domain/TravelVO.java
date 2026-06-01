package org.scoula.travel.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
* VO (Value Object) -> 데이터베이스에 종속되는 형태
* - 데이터 자체를 담는 객체
* - 주로 DB 테이블의 한 행을 객체로 표현
*
* DTO (Data Transfer Object)
* - 계층간 데이터 전달만을 위해 사용하는 객체
* - 비즈니스 로직에 전달에 필요한 필드만 담음
* */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelVO {

    private Long no;
    private String district;
    private String title;
    private String description;
    private String address;
    private String phone;

    private List<TravelImageVO> images;
}
