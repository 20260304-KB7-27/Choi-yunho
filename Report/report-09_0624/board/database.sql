use scoula_db;
DROP TABLE IF EXISTS tbl_board_attachment_ex;
DROP TABLE IF EXISTS tbl_board_ex;
CREATE TABLE tbl_board_ex (

                              no INTEGER AUTO_INCREMENT PRIMARY KEY,
                              title VARCHAR(200) NOT NULL,
                              content TEXT,
                              writer VARCHAR(50) NOT NULL,
                              reg_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                              update_date DATETIME DEFAULT CURRENT_TIMESTAMP

);
INSERT INTO tbl_board_ex(title, content, writer)
VALUES

    ('테스트 제목1', '테스트 내용1', 'user00'),
    ('테스트 제목2', '테스트 내용2', 'user00'),
    ('테스트 제목3', '테스트 내용3', 'user00'),
    ('테스트 제목4', '테스트 내용4', 'user00'),
    ('테스트 제목5', '테스트 내용5', 'user00');

SELECT * FROM tbl_board_ex
ORDER BY no DESC;



CREATE TABLE tbl_board_attachment_ex (
                                         no INTEGER AUTO_INCREMENT PRIMARY KEY,
                                         filename VARCHAR(256) NOT NULL,
                                         content_type VARCHAR(56),
                                         size BIGINT,
                                         file_data LONGBLOB NOT NULL,
                                         bno INTEGER NOT NULL,
                                         reg_date DATETIME DEFAULT now(),
                                         CONSTRAINT fk_board_attachment
                                             FOREIGN KEY(bno)
                                                 REFERENCES tbl_board_ex(no)
                                                 ON DELETE CASCADE
);
