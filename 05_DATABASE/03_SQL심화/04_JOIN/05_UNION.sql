-- UNION / UNION ALL
-- 두 개 이상의 SELECT 결과를 세로 방향으로 합치는 연산

/*
UNION
- 두 SELECT 결과를 합친 뒤 중복을 제거
- 내부적으로 자체 정렬을 수행

UNION ALL
- 두 SELECT 결과를 합침 (중복 제거 X)
- UNION 보다 빠름

규칙
- 각 SELECT의 컬럼 수가 같아야 함.
- 대응하는 컬럼의 데이터 타입이 호환이 되어야 함.
- 최종 컬럼명은 첫번째 SELECT 의 컬럼명을 따른다.
*/

USE employees;

-- d001 부서 현재 재직자 d001 부서 과거 포함 관리자 전원
SELECT emp_no, dept_no, from_date, to_date, 'dept_emp' AS 출처
FROM dept_emp
WHERE dept_no = 'd001'
  AND emp_no > 110000
  AND to_date = '9999-01-01'

UNION
#합치고 중복 제거

SELECT emp_no, dept_no, from_date, to_date, 'dept_manager' AS 출처
FROM dept_manager
WHERE dept_no = 'd001';