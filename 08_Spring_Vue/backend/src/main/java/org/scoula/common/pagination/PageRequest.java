package org.scoula.common.pagination;
// 페이징 처리 요청

import lombok.Data;

@Data
public class PageRequest {

    private int page; // 요청 페이지 번호
    private int amount; // 한 페이지당 데이터 건수

    // 기본 생성자 첫페이지 5개씩 표시
    public PageRequest() {
        page = 1;
        amount = 5;
    }

    public PageRequest(int page, int amount) {
        this.page = page;
        this.amount = amount;
    }

    public static PageRequest of(int page, int amount) {
        return new PageRequest(page, amount);
    }

    // MyBatis Limit 절에 사용할 오프셋 getter 생성
    public int getOffset() {
        return (page - 1) * amount;
    }
}
