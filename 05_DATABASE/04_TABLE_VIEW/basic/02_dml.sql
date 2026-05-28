USE tabledb;

INSERT INTO usertbl
VALUES ('LSG', '이승기', 1987, '서울', '011', '11111111', 182, '2008-02-21');

-- default 제약 조건 확인
INSERT INTO usertbl
VALUES ('KKH', '김경호', 1987, '서울', '011', '11111111', 182, DEFAULT);

-- CHECK 제약 조건 확인 (height < 500)
INSERT INTO usertbl
VALUES ('NBJ', '나비족', 1987, '서울', '011', '11111111', 600, DEFAULT);

SELECT *
FROM usertbl;


-- buytbl
INSERT INTO buytbl
VALUES (NULL, 'LSG', '운동화', NULL, 30, 2);

-- 참조무결성 : 외래키가 참조하는 데이터는 반드시 실제로 존재해야함.
INSERT INTO buytbl
VALUES (NULL, 'NBJ', '운동화', NULL, 30, 2);

DELETE
FROM buytbl
WHERE prodName = '운동화';

DELETE
FROM usertbl
WHERE userID = 'LSG';

DROP TABLE IF EXISTS usertbl;
DROP TABLE IF EXISTS buytbl;