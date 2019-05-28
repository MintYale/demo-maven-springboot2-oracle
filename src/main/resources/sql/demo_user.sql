CREATE TABLE demo_user (
  id varchar2(255) NOT NULL,
  name varchar2(255) ,
  age varchar2(3) ,
  PRIMARY KEY (id)
);

INSERT INTO demo_user VALUES ('001','张三','18');
INSERT INTO demo_user VALUES ('002','李四','19');
INSERT INTO demo_user VALUES ('003','王五','20');

select * from demo_user;