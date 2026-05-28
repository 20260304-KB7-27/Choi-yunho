USE testdb;

-- 기존에 테이블이 존재하면 삭제함
DROP TABLE IF EXISTS userTBL;
DROP TABLE IF EXISTS buyTBL;

-- 다음 컬럼을 가지는 userTBL과 buyTBL을 정의하세요.
CREATE TABLE userTBL
(
    userID    char(8)     NOT NULL PRIMARY KEY,
    name      varchar(10) NOT NULL,
    birthyear int         NOT NULL
);

CREATE TABLE buyTBL
(
    num      int     NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userID   char(8) NOT NULL,
    prodName char(6) NOT NULL,

    CONSTRAINT fk_userTBL_buyTBL
        FOREIGN KEY (userID)
            REFERENCES userTBL (userID)
);

-- 기존에 테이블이 존재하면 삭제함
DROP TABLE IF EXISTS userTBL;
DROP TABLE IF EXISTS buyTBL;

CREATE TABLE userTBL
(
    userID    char(8)     NOT NULL PRIMARY KEY,
    name      varchar(10) NOT NULL,
    birthyear int         NOT NULL,
    email     char(30),

    UNIQUE (email)
);


DROP TABLE IF EXISTS userTBL;

CREATE TABLE userTBL
(
    userID    char(8) NOT NULL PRIMARY KEY,
    name      varchar(10),
    birthyear int CHECK ( birthyear >= 1900 AND birthyear <= 2023),
    mobile    char(3) NOT NULL
);

DROP TABLE IF EXISTS userTBL;

CREATE TABLE userTBL
(
    userID    char(8)     NOT NULL PRIMARY KEY,
    name      varchar(10) NOT NULL,
    birthyear int         NOT NULL DEFAULT -1,
    addr      char(2)     NOT NULL DEFAULT '서울',
    mobile1   char(3),
    mobile2   char(8),
    height    smallint             DEFAULT 170,
    mDate     date
);

INSERT INTO userTBL
VALUES ('CYH', '최윤호', 2001, '서울', '010', '11111111', 182, '2026-03-04');

-- mobile1 컬럼 삭제
ALTER TABLE userTBL
DROP COLUMN mobile1;

-- name 컬럼 uName으로 변경
ALTER TABLE userTBL
RENAME COLUMN name to uName;

-- 기본키 삭제
ALTER TABLE userTBL
DROP PRIMARY KEY;

DESC userTBL;