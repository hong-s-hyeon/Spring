DROP TABLE IF EXISTS t5_test_write;

CREATE TABLE t5_test_write(
    id int primary key auto_increment,
    user varchar(20) not null,
    subject varchar(200) not null,
    content longtext,
    viewcnt int default 0,
    regdate datetime default now()
);