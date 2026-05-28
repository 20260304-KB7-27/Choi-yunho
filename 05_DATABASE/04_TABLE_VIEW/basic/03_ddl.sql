USE tabledb;

CREATE TABLE userTbl
(
    userID    char(8)     NOT NULL,
    name      varchar(10) NOT NULL,
    birthYear int         NOT NULL,

    -- constraint : 제약조건을 명시적으로 이름을 붙여 정의
    CONSTRAINT PRIMARY KEY PK_userTbl (userID)
);

CREATE TABLE prodTbl
(
    prodCode char(8)     NOT NULL,
    prodID   varchar(10) NOT NULL,
    prodDate int         NOT NULL,
    prodCur  int         NOT NULL,

    -- 복합키 : 여러 컬럼을 묶어서 하나의 키로 사용하는 것
    CONSTRAINT PRIMARY KEY PK_prodCode_prodID(prodCode, prodID)
);