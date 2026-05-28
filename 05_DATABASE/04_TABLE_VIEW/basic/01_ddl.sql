-- DDL(Data Definition Language)
-- 데이터베이스 구조를 정의하는 언어

DROP DATABASE IF EXISTS tabledb; -- 삭제

CREATE DATABASE tabledb; -- 생성

USE tabledb;

DROP TABLE IF EXISTS usertbl;

CREATE TABLE usertbl
(
    userID    char(8)     NOT NULL
        PRIMARY KEY,
    name      varchar(10) NOT NULL,
    birthYear int         NOT NULL,
    addr      char(2)     NOT NULL,
    mobile1   char(3)     NULL,
    mobile2   char(8)     NULL,
    height    smallint    NULL CHECK ( height < 500 ),
    mDate     date        NULL
);

DROP TABLE IF EXISTS buytbl;

CREATE TABLE buytbl
(
    num       int AUTO_INCREMENT
        PRIMARY KEY,
    userID    char(8)  NOT NULL,
    prodName  char(6)  NOT NULL,
    groupName char(4)  NULL,
    price     int      NOT NULL,
    amount    smallint NOT NULL

-- FOREIGN KEY (현재 테이블컬럼명)
    -- REFERENCES 참조할 테이블명(참조할 컬럼명)
#     CONSTRAINT buytbl_ibfk_1
#         FOREIGN KEY (userID) REFERENCES usertbl (userID)
);

-- 테이블 구조 변경
ALTER TABLE buytbl
    ADD CONSTRAINT fk_usertbl_buytbl
        FOREIGN KEY (userID)            -- 외래키로 설정할 컬럼
        REFERENCES usertbl(userID);     -- 참조할 부모 테이블 (컬럼)