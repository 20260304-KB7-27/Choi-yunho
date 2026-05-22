/*
그룹화
-- 결과 집합을 특정 열의 값에 따라 그룹화 하는 데에 사용
-- HAVING은 GROUP BY 절과 함께 사용하여 그룹에 대한 조건 적용
*/

USE employees;

SELECT *
FROM salaries;

-- 직원별 급여 횟수 조회
SELECT emp_no,
       COUNT(*)
FROM salaries
GROUP BY emp_no;

-- 직원별 급여횟수, 평균급여, 총급여
SELECT emp_no,
       COUNT(*)    AS 급여횟수,
       AVG(salary) AS 평균급여,
       SUM(salary) AS 총급여
FROM salaries
GROUP BY emp_no;

-- 평균 급여가 50000달러에서 60000달러 사이인 직원 조회
SELECT emp_no,
       COUNT(*)    AS 급여횟수,
       AVG(salary) AS 평균급여,
       SUM(salary) AS 총급여
FROM salaries
GROUP BY emp_no
HAVING 평균급여 >= 50000
   AND 평균급여 <= 60000;