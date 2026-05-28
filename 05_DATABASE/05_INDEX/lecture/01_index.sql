CREATE DATABASE IF NOT exists testdb;

USE testdb;

/*
클러스터형 인덱스
- 테이블의 기본키(PK)가 자동으로 클러스터형 인덱스가 됨
- 데이터가 기본키 순서대로 정렬(클러스터형 인덱스 대로 정렬되어 저장)
- 한 테이블에 한 개의 인덱스만 존재

보조 인덱스
- PRIMARY KEY가 아닌 모든 인덱스
- UNIQUE 제약조건을 넣으면 고유 인덱스가 생성됨
*/
DROP TABLE IF EXISTS usertbl;
CREATE TABLE usertbl(
                        userID CHAR(8) NOT NULL PRIMARY KEY, -- 클러스터형 인덱스
                        name VARCHAR(10) NOT NULL UNIQUE ,   -- 보조 인덱스
                        birthYear INT NOT NULL,
                        addr NCHAR(2) NOT NULL
);

SHOW INDEX FROM usertbl;

INSERT INTO usertbl VALUES('LSG', '이승기', 1987, '서울');
INSERT INTO usertbl VALUES('KBS', '김범수', 1979, '경남');
INSERT INTO usertbl VALUES('KKH', '김경호', 1971, '전남');
INSERT INTO usertbl VALUES('JYP', '조용필', 1950, '경기');
INSERT INTO usertbl VALUES('ANA', '성시경', 1979, '서울');

SELECT * FROM usertbl; -- userID 기준으로 정렬됨

ALTER TABLE usertbl DROP PRIMARY KEY; -- pk 삭제
ALTER TABLE usertbl         -- pk name 컬럼으로 등록
    ADD CONSTRAINT pk_name PRIMARY KEY(name);

SHOW INDEX FROM usertbl;

SELECT * FROM usertbl; -- name 기준으로 정렬