USE testdb;


/*
AUTO INCREMENT
- 행이 INSERT 될 때마다 자동으로 1씩 증가하는 고유번호 생성
- 적용하기 위해서는 PK UNIQUE INDEX 이어야함.
*/

CREATE TABLE board
(
    board_no   int          NOT NULL AUTO_INCREMENT,
    title      varchar(100) NOT NULL,
    content    text,
    author     varchar(50)  NOT NULL,
    created_at datetime DEFAULT NOW(),
    PRIMARY KEY (board_no)
);

INSERT INTO board(title, content, author)
VALUES ('첫 번째 글', '안녕하세요 첫번째 게시글 입니다.', '홍길동');


INSERT INTO board(title, content, author)
VALUES ('두 번째 글', '안녕하세요 두 번째 게시글 입니다.', '홍길동');


INSERT INTO board(title, content, author)
VALUES ('세 번째 글', '안녕하세요 세 번째 게시글 입니다.', '홍길동');

-- NULL 또는 0을 넣어도 Auto_increment가 동작한다.

INSERT INTO board
VALUES (NULL, '네 번째 글', '안녕하세요 네 번째 게시글 입니다.', '홍길동', NOW());

INSERT INTO board
VALUES (0, '다섯 번째 글', '안녕하세요 다섯 번째 게시글 입니다.', '홍길동', NOW());

INSERT INTO board
VALUES (25, '네 번째 글', '안녕하세요 네 번째 게시글 입니다.', '홍길동', NOW());

-- auto increment 시작값 변경

ALTER TABLE board
    AUTO_INCREMENT = 100;

INSERT INTO board (title, content, author)
VALUES ('100부터 시작', '시작값을 100으로 변경 후 삽입', 'bear');

SELECT * FROM board;

-- last_insert_id()
-- 현재 세션에서 마지막으로 insert 된 auto_increment 값을 반환

INSERT INTO board (title, content, author)
VALUES ('100부터 시작', '시작값을 100으로 변경 후 삽입', 'bear');

SELECT LAST_INSERT_ID()