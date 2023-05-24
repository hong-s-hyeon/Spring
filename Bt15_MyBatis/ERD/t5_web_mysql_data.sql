-- 기존테이블 삭제
DELETE FROM t5_test_write;
ALTER TABLE t5_test_write AUTO_INCREMENT = 1;
-- 샘플 글
INSERT INTO t5_test_write (user, subject, content) VALUES
('회원1', '제목입니다1', '내용입니다1'),
('회원2', '제목입니다2', '내용입니다2'),
('회원3', '제목입니다3', '내용입니다3'),
('회원4', '제목입니다4', '내용입니다4')
;
