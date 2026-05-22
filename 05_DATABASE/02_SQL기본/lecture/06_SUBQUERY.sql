-- SUB QUERY
-- 다른 쿼리에서 실행되는 쿼리 (보조역할)
-- 메인쿼리 실행중에 서브 쿼리를 실행해서 결과 값을 메인쿼리에 전달하는 방식

# 규칙
-- 서브쿼리는 소괄호로 묶여야한다.
-- 서브쿼리는 연산자의 오른쪽에 있어야한다.
-- 서브쿼리는 order by 지원안됨

USE employees;

-- employees db에서 각 부서별 관리자의 직원 정보를 출력
-- 조건, 현재 재직자만 ( 관리자의 코드는 dept_no = d005, 재직중인건 to_date = 9999-01-01)
SELECT emp_no
FROM dept_manager
WHERE to_date = '9999-01-01'
  AND dept_no = 'd005';

SELECT *
FROM employees
WHERE emp_no = (SELECT emp_no
                FROM dept_manager
                WHERE to_date = '9999-01-01'
                  AND dept_no = 'd005');

/*
Common Table Expressions (CTE)
- 서브쿼리와 비슷한 개념으로 코드의 가독성과 재사용성을 위한
- 임시 결과 테이블을 선언
*/

WITH current_manager AS (SELECT emp_no
                         FROM dept_manager
                         WHERE to_date = '9999-01-01'
                           AND dept_no = 'd005')
SELECT *
FROM employees
WHERE emp_no = (SELECT emp_no FROM current_manager);


-- 재직자 전체 평균 급여 보다 급여를 더 많이 받는 재직자의 정보를 출력

# 재직자의 평균급여
SELECT AVG(salary)
FROM salaries
WHERE to_date = '9999-01-01';

# 평균급여보다 많이받는 재직자의 ID
SELECT emp_no
FROM salaries
WHERE to_date = '9999-01-01'
  AND salary > (SELECT AVG(salary)
                FROM salaries
                WHERE to_date = '9999-01-01');

# 평균급여보다 많이받는 재직자의 직원정보
SELECT *
FROM employees
WHERE emp_no IN (SELECT emp_no
                 FROM salaries
                 WHERE to_date = '9999-01-01'
                   AND salary > (SELECT AVG(salary)
                                 FROM salaries
                                 WHERE to_date = '9999-01-01'));

# create table .. select
-- 셀렉트문의 결과를 그대로 새 테이블로 저장하는 문법

# 사용이유
-- 복잡한쿼리, 결과를 반복 조회하거나, 무거운 쿼리를 실행하는대신
-- 테이블로 저장해놓으면 이후 조회가 빠름
-- 원본데이터 손상을 방지

-- 컬럼명-데이터타입은 select 한 결과로 복사됨.
-- INDEX, PK, FK등의 제약조건은 복사 안됨 (추가 가능)

-- 연도별 입사자 수를 구하고, 그 중 입사자가 20000명 이상인 연도만 출력
SELECT YEAR(hire_date) AS hire_year, COUNT(*) AS hire_count
FROM employees
GROUP BY hire_year;

CREATE TABLE yearly_hire
SELECT YEAR(hire_date) AS hire_year, COUNT(*) AS hire_count
FROM employees
GROUP BY hire_year;

SELECT hire_year
FROM yearly_hire
WHERE hire_count >= 20000;