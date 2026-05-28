USE employees;

SHOW INDEX FROM employees;

-- EXPLAIN 실행계획 확인
/*
type (접근 방식)
- ALL : 전체 테이블을 다 읽음
- index : 인덱스를 전부 읽기
- range : (WHERE age > 30) 인덱스 일부 탐색
- ref : 인덱스를 통해 특정 값을 탐색
- const : 기본키, 유일 값으로 한줄만 찾는 경우

key (사용된 인덱스)
- 쿼리에서 사용되는 인덱스의 이름 (NULL 인덱스가 없는 것)

rows (예상되는 읽을 행의 수)
- 적게 읽을수록 성능이 좋음.
*/
-- SELECT 쿼리를 실행하기 전에 MYSQL이 내부적으로 어떻게 데이터를 가져올지 확인가능
EXPLAIN
SELECT *
FROM employees
WHERE last_name = 'Peha';

EXPLAIN
SELECT *
FROM employees
WHERE emp_no = '10018';

-- 인덱스 생성
CREATE INDEX idx_lastname ON employees (last_name);

SHOW INDEX FROM employees;

-- 테이블의 인덱스 통계정보를 갱신
ANALYZE TABLE employees;

EXPLAIN
SELECT *
FROM employees
WHERE last_name = 'Peha';

-- 인덱스 삭제
DROP INDEX idx_lastname ON employees;

SHOW INDEX FROM employees;