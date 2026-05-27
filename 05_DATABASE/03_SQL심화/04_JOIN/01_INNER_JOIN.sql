/*
SELECT 컬럼, ...
FROM 테이블A
INNER JOIN 테이블B ON 테이블A.컬럼 = 테이블B.컬럼

INNER JOIN
- ON 조건에 매칭되는 행끼리 결합
- 가장 일반적으로 사용되는 JOIN (INNER JOIN = JOIN)
*/

USE employees;

-- 직원 정보
SELECT emp_no, first_name, last_name
FROM employees
LIMIT 5;

SELECT emp_no, dept_no, from_date, to_date
FROM dept_emp
LIMIT 5;

-- 직원 정보, 부서 ID
SELECT e.emp_no, first_name, last_name, dept_no, from_date, to_date
FROM employees AS e
         INNER JOIN dept_emp AS d ON e.emp_no = d.emp_no
LIMIT 10;

-- dept_emp와 departments JOIN
SELECT de.emp_no, de.dept_no, dept_name
FROM dept_emp de
         JOIN departments d ON de.dept_no = d.dept_no;

-- 재직중인 직원과 부서명까지 한번에 조회
SELECT e.emp_no, first_name, last_name, de.dept_no, d.dept_name, from_date, to_date
FROM employees AS e
         INNER JOIN dept_emp AS de ON e.emp_no = de.emp_no
         JOIN departments d ON de.dept_no = d.dept_no
WHERE de.to_date = '9999-01-01'
LIMIT 10;