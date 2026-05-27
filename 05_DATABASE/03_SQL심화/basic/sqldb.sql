USE sqldb;

-- sqldb 데이터베이스에서 다음 조건을 처리하세요
# 사용자별 구매 이력
# 모든 칼럼 출력
# 구매 이력 없는 정보는 출력하지 않음
SELECT *
FROM usertbl u
         JOIN buytbl b ON b.userID = u.userID;

-- 앞의 결과에서 userID가 'JYP'인 데이터만 출력하세요
SELECT *
FROM usertbl u
         JOIN buytbl b ON b.userID = u.userID
WHERE b.userID = 'JYP';

-- sqldb 데이터베이스에서 다음 조건을 처리하세요
# 사용자별 구매이력 출력
# 연결 컬럼 userID
# userID 기준 오름차순
# 구매이력 없는 사용자도 출력
# userID, name, prodName, addr, 연락처 출력

SELECT u.userID, u.name, b.prodName, u.addr, CONCAT(u.mobile1, u.mobile2) AS 연락처
FROM usertbl u
         LEFT JOIN buytbl b ON u.userID = b.userID
ORDER BY u.userID;

-- sqldb의 사용자를 모두 조회하되 전화가 없는 사람은 제외하고 출력하세요

SELECT *
FROM usertbl
WHERE mobile1 IS NOT NULL;

-- sqldb의 사용자를 모두 조회하되 전화가 없는 사람만 출력하세요

SELECT *
FROM usertbl
WHERE mobile1 IS NULL;