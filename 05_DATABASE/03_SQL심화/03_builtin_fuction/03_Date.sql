-- 날짜 내장함수

use employees;

SELECT
    emp_no
FROM employees
LIMIT 10;

SELECT
    emp_no,
    hire_date,
    YEAR(hire_date),    # 연도 추출
    MONTH(hire_date),   # 월 추출
    DAY(hire_date)      # 일 추출
FROM employees
LIMIT 10;

/*
DATEDIFF(date1, date2)
- date1에서 date2를 뺀 일 수 차이를 구분
*/

/*
DATE_FORMAT(date, format)
- %Y : 4자리 년도, %m : 2자리 월, %d: 2자리 일
- %y : 2자리 년도, %M : 영문 월 이름, %W: 영문 요일 이름
*/
SELECT
    emp_no,
    hire_date,
    DATE_FORMAT(hire_date, '%Y년 %m월 %d일')
FROM employees
LIMIT 10;