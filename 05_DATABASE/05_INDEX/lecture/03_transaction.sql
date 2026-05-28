/*
트랜젝션(Transaction)
- 데이터베이스 작업을 하나의 논리적인 작업 단위로 묶은 것
*/

SELECT @@autocommit; -- 현재 세션의 자동 커밋 상태

SET AUTOCOMMIT = TRUE;
-- autocommit 비활성화

-- 트랜젝션 시작
START TRANSACTION;

-- 이후에 동작하는 query들은 하나의 작업 단위로 묶임
USE sqldb;

DELETE
FROM buytbl
WHERE num = 3;

DELETE
FROM buytbl
WHERE num = 4;

SELECT *
FROM buytbl;
-- 현재 트랜젝션 세션 안에서는 삭제된 것으로 보이나 실제 데이터베이스에는 반영 안됨.


ROLLBACK; -- transaction 시작 이전 가장 최근의 commit 시점으로 되돌림.

COMMIT; -- commit을 했을 때 데이터베이스에 반영이 된다.

SET AUTOCOMMIT = TRUE; -- autocommit 활성화

    SELECT @@autocommit; -- 현재 세션의 자동 커밋 상태
