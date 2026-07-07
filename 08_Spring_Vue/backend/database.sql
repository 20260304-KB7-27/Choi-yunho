-- 사용자 권한 테이블
drop table if exists tbl_member_auth;
-- 사용자 정보 테이블
drop table if exists tbl_member;


create table tbl_member
(

    username    varchar(50) primary key, -- 사용자 id
    password    varchar(128) not null,   -- 암호화된 비밀번호
    email       varchar(50)  not null,
    reg_date    datetime default now(),
    update_date datetime default now()

);

create table tbl_member_auth
(

    username varchar(50) not null, -- 사용자 id
    auth     varchar(50) not null, -- 권한 문자열 ROLE_ADMIN, ROLE_MANAGER, ROLE_MEMBER 등
    primary key (username, auth),  -- 복합키
    constraint fk_authorities_users foreign key (username) references tbl_member (username)

);

-- 테스트 사용자 추가
insert into tbl_member(username, password, email)
values ('admin', '$2a$10$NEhSTVn.l1Pe9qUD6hdC2O3stR035Aj9df9Dv2YjVMbjgtYHBD5XO', 'admin@galapgos.org'),
       ('user0', '$2a$10$NEhSTVn.l1Pe9qUD6hdC2O3stR035Aj9df9Dv2YjVMbjgtYHBD5XO', 'user0@galapgos.org'),
       ('user1', '$2a$10$NEhSTVn.l1Pe9qUD6hdC2O3stR035Aj9df9Dv2YjVMbjgtYHBD5XO', 'user1@galapgos.org'),
       ('user2', '$2a$10$NEhSTVn.l1Pe9qUD6hdC2O3stR035Aj9df9Dv2YjVMbjgtYHBD5XO', 'user2@galapgos.org'),
       ('user3', '$2a$10$NEhSTVn.l1Pe9qUD6hdC2O3stR035Aj9df9Dv2YjVMbjgtYHBD5XO', 'user3@galapgos.org'),
       ('user4', '$2a$10$NEhSTVn.l1Pe9qUD6hdC2O3stR035Aj9df9Dv2YjVMbjgtYHBD5XO', 'user4@galapgos.org');

select *
from tbl_member;

-- 테스트 권한 등록
insert into tbl_member_auth(username, auth)
values ('admin', 'ROLE_ADMIN'),
       ('admin', 'ROLE_MANAGER'),
       ('admin', 'ROLE_MEMBER'),
       ('user0', 'ROLE_MANAGER'),
       ('user0', 'ROLE_MEMBER'),
       ('user1', 'ROLE_MEMBER'),
       ('user2', 'ROLE_MEMBER'),
       ('user3', 'ROLE_MEMBER'),
       ('user4', 'ROLE_MEMBER');

-- 기존 테이블이 있다면 삭제
DROP TABLE IF EXISTS tbl_board_attachment_ex;

-- 기존 테이블이 있다면 삭제
DROP TABLE IF EXISTS tbl_board_ex;

-- 게시판 테이블 생성
CREATE TABLE tbl_board_ex
(
    no          INTEGER AUTO_INCREMENT PRIMARY KEY, -- 게시글 번호 (자동증가)
    title       VARCHAR(200) NOT NULL,              -- 게시글 제목
    content     TEXT,                               -- 게시글 내용
    writer      VARCHAR(50)  NOT NULL,              -- 작성자
    reg_date    DATETIME DEFAULT CURRENT_TIMESTAMP, -- 등록일시
    update_date DATETIME DEFAULT CURRENT_TIMESTAMP  -- 수정일시
);

-- 테스트 데이터 삽입
INSERT INTO tbl_board_ex(title, content, writer)
VALUES ('테스트 제목1', '테스트 내용1', 'user00'),
       ('테스트 제목2', '테스트 내용2', 'user00'),
       ('테스트 제목3', '테스트 내용3', 'user00'),
       ('테스트 제목4', '테스트 내용4', 'user00'),
       ('테스트 제목5', '테스트 내용5', 'user00');

-- 데이터 확인
SELECT *
FROM tbl_board_ex;




-- 첨부파일 테이블 생성
CREATE TABLE tbl_board_attachment_ex
(
    no           INTEGER AUTO_INCREMENT PRIMARY KEY, -- 첨부파일 고유 번호
    filename     VARCHAR(256) NOT NULL,              -- 원본 파일명 (다운로드용)
    path         VARCHAR(256) NOT NULL,              -- 서버 저장 경로
    content_type VARCHAR(56),                        -- MIME 타입 (파일 형식 식별)
    size         INTEGER,                            -- 파일 크기 (바이트)
    bno          INTEGER      NOT NULL,              -- 게시글 번호 (외래키)
    reg_date     DATETIME DEFAULT now(),             -- 등록 시간

    -- 외래키 제약조건 설정
    CONSTRAINT FOREIGN KEY (bno) REFERENCES tbl_board_ex (no)
);



/* 게시글 조회 + 첨부파일 조회 테스트 */

-- 테스트용 게시글 삽입
INSERT INTO tbl_board_ex (title, content, writer)
VALUES ('첨부 파일 테스트', '첨부파일 테스트 내용', '테스터');

-- 테스트 게시글에 샘플 첨부파일 데이터 삽입
INSERT INTO tbl_board_attachment_ex(filename, path, content_type, size, bno)
VALUES
    ('cat1.jpg', 'c:\upload\board\cat1-1749870568385.jpg', 'image/jpeg',283580, (SELECT MAX(no) FROM tbl_board_ex)),
    ('cat2.jpg', 'c:\upload\board\cat2-1749870568395.jpg', 'image/jpeg',166577, (SELECT MAX(no) FROM tbl_board_ex)),
    ('cat3.jpg', 'c:\upload\board\cat3-1749870568401.jpg', 'image/jpeg',119049, (SELECT MAX(no) FROM tbl_board_ex)),
    ('cat4.jpg', 'c:\upload\board\cat4-1749870568407.jpg', 'image/jpeg',193510, (SELECT MAX(no) FROM tbl_board_ex)),
    ('cat5.jpg', 'c:\upload\board\cat5-1749870568412.jpg', 'image/jpeg',186751, (SELECT MAX(no) FROM tbl_board_ex));


