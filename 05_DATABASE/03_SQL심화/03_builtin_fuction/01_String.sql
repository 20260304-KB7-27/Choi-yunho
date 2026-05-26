-- 문자열 내장함수

USE employees;

/*
CONCAT(str1, str2, ...)
- 여러 문자열을 합쳐주는 함수
*/

SELECT emp_no,
       CONCAT(first_name, ' ', last_name) AS full_name
FROM employees
LIMIT 5;

/*
UPPER / LOWER
*/

SELECT first_name,
       UPPER(first_name) AS 대문자이름,
       LOWER(first_name) AS 소문자이름
FROM employees
LIMIT 5, 10;

/*
- SUBSTRING(str, pos, len) : 문자열의 pos 위치부터 len 만큼의 문자 추출
- LEFT(str, len) : 왼쪽에서부터 str의 len개 만큼 문자 추출
- RIGHT(str, len) : 오른쪽에서부터 str의 len개 만큼 문자 추출
*/
SELECT first_name,
       LEFT(first_name, 3) AS '앞 3글자',
       RIGHT(first_name, 3) AS '뒤 3글자',
       SUBSTRING(first_name, 2, 4) AS '2번쨰부터 4글자'
FROM employees
LIMIT 5, 5;