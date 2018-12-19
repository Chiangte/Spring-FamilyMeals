drop database if exists bookdb;
create database bookdb character set utf8;
use bookdb;

#出版社表
create table t_publishing
(
	id bigint primary key auto_increment comment '出版社ID，主键、标识列' ,
	name varchar(50) not null comment '出版社名称，唯一'
);
alter table t_publishing
	add constraint UQ_publishing_name unique(name);

#图书表
create table t_book
(
	id bigint primary key auto_increment comment '图书ID，主键、标识列' ,
	name varchar(100) not null comment '图书名称，唯一' ,
	author varchar(50) not null comment '图书作者' ,
	price decimal(10, 2) not null comment '图书价格' ,
	pid bigint not null comment '出版社外键，引用t_publishing表id列' ,
	createDate date not null comment '出版日期' ,
	cover varchar(50) not null comment '封面图片' ,
	summary varchar(1000) comment '图书摘要'
);
alter table t_book
	add constraint UQ_book_name unique(name),
	add constraint FK_book_pid foreign key(pid) references t_publishing(id);

#管理员表
create table t_user
(
	id bigint primary key auto_increment comment '管理员ID，主键、标识列' ,
	username varchar(64) not null comment '用户名，唯一' ,
	password varchar(64) not null comment '密码' ,
	phone varchar(20) not null comment '联系电话'
);
alter table t_user
	add constraint UQ_user_username unique(username);

#会员表
create table t_member
(
	id bigint primary key auto_increment comment '会员ID，主键、标识列' ,
	name varchar(20) not null comment '会员姓名' ,
	identityCard char(18) not null comment '会员身份证号，唯一，18位' ,
	phone varchar(20) not null comment '会员电话，唯一' ,
	createTime datetime not null comment '会员创建时间'
);
alter table t_member
	add constraint UQ_member_identityCard unique(identityCard),
	add constraint CK_member_identityCard check(len(identityCard) = 18),
	add constraint UQ_member_phone unique(phone);

#图书借阅表
create table t_borrow
(
	id bigint primary key auto_increment comment '图书借阅ID，主键、标识列' ,
	mid bigint not null comment '会员外键，引用t_member表id列' ,
	bid bigint not null comment '图书外键，引用t_book表id列' ,
	borrowDate datetime not null comment '借阅日期' ,
	returnDate datetime comment '归还日期，如果不为空则大于借阅日期'
);
alter table t_borrow
	add constraint FK_borrow_mid foreign key(mid) references t_member(id),
	add constraint FK_borrow_bid foreign key(bid) references t_book(id),
	add constraint CK_borrow_returnDate check(returnDate is null or returnDate > borrowDate);

insert into t_user values(null, 'admin', '123', '13677778888');

insert into t_publishing values
	(null, '人民教育出版社'),
	(null, '清华大学出版社'),
	(null, '机械工业出版社');

insert into t_book values
	(null, 'Java与模式', '闫宏', 98.5, 1, '1998-11-25', 'a.jpg', '这是一本台湾作者写的关于Java设计模式的书籍...'),
	(null, 'ExtJS', '黄灯桥', 68.5, 2, '2014-09-18', 'b.jpg', '这是一本国人书写的关于富客户端框架ExtJS的书籍...'),
	(null, 'Java编程思想', 'Bruce Eckel', 88.4, 3, '2007-06-01', 'c.jpg', '本书共22章，包括操作符、控制执行流程、访问权限控制、复用类、多态、接口、通过异常处理错误、字符串、泛型、数组、容器深入研究...');

insert into t_member values
	(null, '小张', '123456789987654321', '13593494538', '2017-07-10'),
	(null, '小陈', '111222333444555666', '18212328321', '2017-06-20'),
	(null, '小李', '123456654321123456', '13823423483', '2016-03-22');

insert into t_borrow values
	(null, 1, 1, now(), null),
	(null, 1, 2, now(), null),
	(null, 2, 3, '2018-04-24', now());
	
select * from t_user;
select * from t_publishing;
select * from t_book;
select * from t_member;
select * from t_borrow;
