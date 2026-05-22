USE sqldb;

-- DISTINCT
-- 중복된 값을 제거하는 데에 사용
-- Null도 종류로 포함

SELECT DISTINCT groupName
FROM buytbl;

-- LIMIT
-- select 문의 결과 집합에서 반환할 행의 갯수를 제한할 때 사용
-- 페이징할 때도 사용 가능 (offset)
-- limit [offset], [rowcount] : offset 행부터 rowcount만큼
-- limit [rowcount] : rowcount 만큼

SELECT *
FROM buytbl
ORDER BY price DESC
LIMIT 5;

-- offset
SELECT *
FROM buytbl
ORDER BY price
LIMIT 0, 20;

