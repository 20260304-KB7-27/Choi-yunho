-- CROSS JOIN(교차 조인)
-- ON 조건이 없고, 두 테이블의 모든 행을 서로 조합
-- 결과 행의 수 = 테이블A 행의 수 * 테이블B 행의 수

use employees;

-- 부서 (9) * 부서 (9)
SELECT
    d1.dept_name as 부서A,
    d2.dept_name as 부서B
FROM departments d1
CROSS JOIN departments d2;

-- 옛날 문법
-- CROSS JOIN 후 WHERE로 필요한 내용 가지고 옴
SELECT e.emp_no, e.first_name, de.dept_no
from employees e, dept_emp de
WHERE e.emp_no = de.emp_no;