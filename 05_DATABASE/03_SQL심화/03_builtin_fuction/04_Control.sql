-- 조건/흐름 내장함수

USE employees;

/*
IF (condition, true_value, false_value)
- condition이 true이면 true_value 반환
- condition이 false이면 false_value 반환
*/

SELECT emp_no,
       first_name,
       gender,
       IF(gender = 'M', '남성', '여성') AS 성별_한글
FROM employees
LIMIT 10;

/*
    CASE WHEN condition THEN result ... ELSE result END
    - 여러 조건을 순서대로 검색해서 처음으로 참인 조건의 결과 반환
*/

-- 급여 구간에 따른 등급 구분
SELECT emp_no,
       salary,
       CASE
           WHEN salary >= 100000 THEN 'S등급'
           WHEN salary >= 80000 THEN 'A등급'
           WHEN salary >= 50000 THEN 'B등급'
           ELSE 'C등급'
           END AS 급여등급
FROM salaries
WHERE to_date = '9999-01-01'
LIMIT 10;

/*
IFNULL(column, default_value)
- column의 값이 null 이면 default_value를 반환하고, null이 아니면 원래 값을 반환
*/
USE sqldb;

SELECT name,
       mobile1,
       mobile2,
       IFNULL(
               CONCAT(mobile1, '-', mobile2),
               '번호없음'
       ) AS 전화번호
FROM usertbl