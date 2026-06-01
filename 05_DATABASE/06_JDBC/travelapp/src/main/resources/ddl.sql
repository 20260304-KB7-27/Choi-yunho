create database  tbl_travel;

use mysql;
GRANT ALL ON tbl_travel.* TO 'scoula'@'%';

use tbl_travel;
DROP TABLE IF EXISTS tbl_travel;
CREATE TABLE tbl_travel
(
    no INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    district VARCHAR(50) NOT NULL,
    title VARCHAR(512) NOT NULL,
    description TEXT,
    address VARCHAR(512),
    phone VARCHAR(256)
);

DROP TABLE IF EXISTS tbl_travel_image;
CREATE TABLE tbl_travel_image
(
    no INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    filename VARCHAR(512) NOT NULL,
    travel_no INT,
    CONSTRAINT FOREIGN KEY (travel_no) REFERENCES tbl_travel (no)
        ON DELETE CASCADE
/*
- 부모 테이블에 데이터가 삭제되면 해당 데이터를 참조하는 자식테이블의 데이터도 자동 삭제
*/
);