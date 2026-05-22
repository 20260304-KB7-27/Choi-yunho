-- DML
-- 데이터의 값을 삽입, 수정, 삭제하는 SQL 문

-- INSERT
insert into employees values (2, 20260522, '태근', '박', 'M', 20260304)

select * from employees limit 2;

insert into employees values (3, 20260522, '태근', '박', 'M', 20260304),
                             (4, 20260522, '유환', '김', 'M', 20260304)

-- 컬럼을 명시하면 Insert 시 데이터 순설르 바꾸는 것도 가능

insert into employees(emp_no, birth_date, last_name, first_name, gender, hire_date) values
                             (5, 20260522, '유환', '김', 'M', 20260304)

-- UPDATE
-- 테이블에 기록된 컬럼의 값을 수정하는 구문

update
    employees
set
    last_name = '김',
    first_name = '유'
where
    last_name = '유환';

-- DELETE
-- 테이블 행을 삭제

DELETE FROM
           employees
WHERE first_name='bear';