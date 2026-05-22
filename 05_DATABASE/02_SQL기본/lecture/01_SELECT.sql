-- SELECT
-- 특정 테이블에서 원하는 데이터를 조회

SELECT -- 조회해줘
       userID,
       groupName -- userID 컬럼을
FROM buytbl;
-- buytbl 테이블에서

/*
select 단독 활용
- from 없이 단독 사용 가능
- 단순한 텍스트 출력
*/

SELECT (5 + 5);
SELECT NOW(); -- 데이터베이스(MYsql) 내장함수
SELECT CONCAT('bear', '안녕', 'mysql') AS name;
-- 문자열 합치기

-- 별칭에 공백을 쓰려면 따옴표 필요
SELECT CONCAT('bear', '안녕', 'mysql') AS 'Full Name';

SELECT name,
       CONCAT(mobile1, ' ', mobile2) AS 전화번호
FROM usertbl;