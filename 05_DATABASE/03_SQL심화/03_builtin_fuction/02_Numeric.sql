-- 숫자 내장 함수

use employees;

/*
ROUND(number, decimals)
- 숫자를 지정한 소숫점 자리수로 반올림
- decimals가 0이면 정수로 반올림
*/
-- 직원별 평균 급여 반올림해서 정수 출력

SELECT
    emp_no,
    AVG(salary) AS '원본 평균 급여',
    ROUND(AVG(salary)) AS '평균급여 반올림',  # 정수로 반올림
    ROUND(AVG(salary), 2) AS '소수 둘째 자리 반올림',
    FLOOR(AVG(salary)) AS 내림,
    CEIL(AVG(salary)) AS 올림
from salaries
GROUP BY emp_no
LIMIT 5;

/*
FORMAT(number, decimals)
- 숫자 천 단위 콤마 추가 및 소수점 자릿수 지정 출력
- 문자열로 반환
*/
SELECT
    emp_no,
    salary,
    FORMAT(salary, 0) as 급여_포맷
from salaries
WHERE to_date = '9999-01-01'
LIMIT 5;