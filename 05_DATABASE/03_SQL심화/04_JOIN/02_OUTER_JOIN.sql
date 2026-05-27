-- OUTER JOIN
-- 한 쪽 테이블의 데이터는 JOIN 조건이 안 맞아도 결과에 포함
-- 조건이 맞지 않는 쪽 컬럼은 NULL로 채워짐

-- LEFT OUTER JOIN : 왼쪽(FROM) 테이블을 기준으로 모두 출력
-- RIGHT OUTER JOIN : 오른쪽(JOIN) 테이블을 기준으로 모두 출력
# OUTER 키워드 생략 가능

DROP DATABASE IF EXISTS join_demo;
CREATE DATABASE join_demo;
USE join_demo;

CREATE TABLE customers
(
    id   int PRIMARY KEY,
    name varchar(30)
);

CREATE TABLE orders
(
    id          int PRIMARY KEY,
    customer_id int,
    product     varchar(50),
    amount      int
);

INSERT INTO customers
VALUES (1, '김철수'),
       (2, '이영희'),
       (3, '박민준'),
       (4, '최수진'),
       (5, '정태현');

INSERT INTO orders
VALUES (1, 1, '노트북', 1200000),
       (2, 1, '마우스', 30000),
       (3, 3, '키보드', 80000);

-- 고객과 고객의 주문 데이터 (주문한 고객만 존재)
SELECT *
FROM customers c
         JOIN orders o ON c.id = customer_id;

-- 주문이 없는 고객도 포함
SELECT *
FROM customers c
         LEFT JOIN orders o ON c.id = customer_id;

-- 주문을 하나도 하지 않은 고객 조회
SELECT *
FROM customers c
         LEFT JOIN orders o ON c.id = customer_id
WHERE o.id IS NULL;

-- RIGHT JOIN - 테이블 순서만 반대
SELECT *
FROM orders o
         RIGHT JOIN customers c ON c.id = customer_id;

