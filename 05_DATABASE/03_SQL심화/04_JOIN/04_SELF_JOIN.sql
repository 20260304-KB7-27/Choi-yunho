-- SELF JOIN
-- 하나의 테이블을 서로 다른 별칭을 붙여 두 번 참조하는 JOIN
-- 같은 테이블 안에서 행끼리 비교하거나 관계를 찾을 떄 사용

USE employees;

-- 같은 생년월일인 직원들 조회
SELECT CONCAT(e1.first_name, ' ', e1.last_name) AS 직원A이름,
       e1.birth_date                            AS 생년월일,
       CONCAT(e2.first_name, ' ', e2.last_name) AS 직원B이름
FROM employees e1
         JOIN employees e2 ON e1.birth_date = e2.birth_date
AND e1.emp_no < e2.emp_no -- 중복, 자기 쌍 제거
LIMIT 20;
