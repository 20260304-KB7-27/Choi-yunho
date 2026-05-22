USE employees;

-- WHERE
-- 특정 조건에 맞는 행만 선택하는데 사용된다.

SELECT *
FROM employees
WHERE gender = 'M';
-- 성별이 M인 데이터만

-- select에 조회 컬럼으로 선택하지 않은 컬럼으로 조건을 걸 수 있음

SELECT first_name,
       last_name
FROM employees
WHERE gender = 'M';


-- ColumnA != B : 컬럼의 내용 중 B와 같지 않은 데이터만 보여주기 -> 확장 문법
-- ColumnA <> B : 컬럼의 내용 중 B와 같지 않은 데이터만 보여주기 -> 표준 정의

SELECT first_name,
       last_name,
       gender
FROM employees
WHERE
#     gender != 'M'; -- 성별이 M이 아닌 데이터만
gender <> 'M';
-- 성별이 M이 아닌 데이터만


-- AND 연산자와 함께 where 절 사용

SELECT first_name,
       last_name,
       gender
FROM employees
WHERE gender = 'M'
  AND first_name = 'Aamod';

SELECT emp_no,
       first_name,
       last_name,
       gender
FROM employees
WHERE gender = 'F'
   OR emp_no < '10050';
-- 성별이 F이거나 emp_no가 10050 이하인 데이터만

/*
AND 와 OR의 우선순위
*/
SELECT TRUE OR FALSE AND FALSE;
SELECT (TRUE OR FALSE) AND FALSE;

SELECT *
FROM employees
WHERE emp_no >= 10050
  AND emp_no <= 10060;

/*
BETWEEN
- 경계값을 포함한, 사이의 값을 포함한다.
*/

SELECT *
FROM employees
WHERE emp_no BETWEEN 10050 AND 10060;

-- BETWEEN의 부정 표현 (경계값을 포함하지 않음)
SELECT *
FROM employees
WHERE emp_no NOT BETWEEN 10050 AND 499995;


/*
LIKE
- 특정 패턴과 일치하는 행을 검색
- 느림 -> 보통은 패턴 검색 용도로는 검색 엔진을 사용

패턴
% : 0개 이상의 문자를 나타낸다.
'%apple%' -> apple이 포함된 모든 문자열을 의미
_ : 1개의 문자를 나타냄
'a_k'는 'a'로 시작하고 'k'로 끝나는 세 글자 문자열을 의미
*/

SELECT first_name
FROM employees
WHERE first_name LIKE 'Aa%'; -- first_name이 Aa로 시작하는 모든 데이터

SELECT first_name
FROM employees
WHERE first_name LIKE 'Aa__d';
-- first_name이 Aa로 시작하고 d로 끝나는 5글자인 모든 데이터

/*
IN 연산자
- 특정 열의 값이 지정된 목록 중 하나와 일치하는 데이터만
*/

SELECT *
FROM employees
WHERE emp_no IN (2, 3, 4, 55333, 19525);

/*
is Null
- 값이 NULL 인지 아닌지 확인하여 조회
*/

USE sqldb;

SELECT *
FROM buytbl
WHERE groupName IS NULL;