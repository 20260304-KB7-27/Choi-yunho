USE employees;

SELECT *
FROM employees
WHERE emp_no IN (1, 2, 3, 4, 5, 6);

-- 현재 d005 부서에 재직 중인 직원들의 상세정보
-- IN 안에 서브쿼리 작성
SELECT e.emp_no,
       e.first_name,
       e.last_name,
       e.gender
FROM employees e
WHERE emp_no IN (SELECT emp_no
                 FROM dept_emp
                 WHERE dept_no = 'd005'
                   AND to_date = '9999-01-01')
LIMIT 5;

-- NOT IN - 서브쿼리로 없는 것 찾기

-- 관리자였던 적이 없는 직원의 수

SELECT COUNT(*)
FROM employees e
WHERE e.emp_no NOT IN (SELECT emp_no
                       FROM dept_manager # 관리자를 했던 직원들의 emp_no
)

-- NOT IN의 NULL 함정
-- != AND 비교로 동작하는데 NULL이랑 비교하게 되면 T/F가 아닌
-- UNKNOWN이 나옴. 모든 결과가 전부 제외되는 문제 발생

USE SQLDB;

-- 기댓값 : 010, 016, 011이 아닌 나머지 user 데이텈
SELECT *
FROM usertbl
WHERE mobile1 NOT IN (SELECT mobile1
                      FROM usertbl
                      WHERE addr = '서울'
                        AND mobile1 IS NOT NULL)
