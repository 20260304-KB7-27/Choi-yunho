/*
safe mode
- 한 번의 쿼리 실수로 모든 데이터가 수정되거나 삭제되는 것을 방지하기 위해
- 조건문이 없을 때 update, delete가 동작하지 않게 막는 기능
*/

USE sqldb;

SET SQL_SAFE_UPDATES = 0; -- mysql safe mode 끄기

UPDATE buytbl
SET price = price * 1.5; -- 모든 가격 1.5배

SET SQL_SAFE_UPDATES = 1; -- mysql safe mode 켜기

USE testdb;

DROP TABLE IF EXISTS board;

CREATE TABLE board
(
    post_no INT          NOT NULL AUTO_INCREMENT,
    title   VARCHAR(100) NOT NULL,
    author  VARCHAR(50)  NOT NULL,
    PRIMARY KEY (post_no)
);

INSERT INTO board (title, author)
VALUES ('첫 번째 글', '홍길동');
INSERT INTO board (title, author)
VALUES ('두 번째 글', '김철수');
INSERT INTO board (title, author)
VALUES ('세 번째 글', '이영희');
INSERT INTO board (title, author)
VALUES ('네 번째 글', '박민준');
INSERT INTO board (title, author)
VALUES ('다섯 번째 글', '최지수');

/*
delete
- 테이블의 행(row)를 삭제하는 DML 명령어
- WHERE 조건으로 특정 행을 삭제 할 수 있다.
- 트랙잭션(ROLLBACK)으로 복구 가능
*/

DELETE
FROM board
WHERE post_no = 1;

SELECT * FROM board;

-- Auto Increment 값이 초기화되지 않고 이어서 증가함.
INSERT INTO board (title, author)
VALUES ('여섯 번째 글', '최지수');

/*
TRUNCATE
- 테이블의 전채 행을 한 번에 삭제하는 DDL 명령어
- WHERE 사용 불가 (전체 삭제 전용)
- 트랜젝션(ROLLBACK) 사용 불가
- DELETE 보다 처리 속도 빠름
- 테이블 구조 (컬럼, 제약조건) 유지, AUTO_INCREMENT는 초기화
*/

TRUNCATE TABLE board;

INSERT INTO board (title, author)
VALUES ('첫 번째 글', '홍길동');

/*
DROP
- 테이블 자체ㅐ를 데이터베이스에서 완전히 제거하는 DDL 명령어
- 트랜젝션(ROLLBACK) 복구가 불가능
*/

-- 테이블이 없으면 에러 발생
DROP TABLE board;

-- 일반적으로는 IF EXISTS를 붙여서 사용
DROP TABLE IF EXISTS board;