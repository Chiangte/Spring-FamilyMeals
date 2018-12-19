drop database if exists StuManagement;
create database StuManagement character set utf8;
use StuManagement;

create table classes
(
	id integer primary key auto_increment comment '编号、主键、自动增长' ,
	cname varchar(100) not null comment '班级名称'
);

create table student
(
	id integer primary key auto_increment comment '学员编号,主键,自动增长' ,
	sname varchar(50) not null comment '姓名',
	birthday date not null comment '生日' ,
	gender varchar(20) not null comment '性别',
	telephone varchar(11) not null comment '电话',
	email varchar(20) not null comment '邮箱',
	classId integer  not null comment '班级编号，对应班级表的主键',
	foreign key(classId) references classes(id)
);

insert into classes values
	(null,  'S1') ,
	(null,  'S2') ,
	(null,  'Y2') ;

insert into student values
	(null, '胡丹丹',now(), '女', '15928017171','1@qq.com',1 ) ,
	(null, '方辉', now(),'男', '15928017172','2@qq.com',1) ,
	(null, '林家栋', now(),'男', '15928017173','3@qq.com',2) ,
	(null, '杨仁',now(), '女', '15928017174','4@qq.com',2) ,
	(null, '狼人', now(),'男', '15928017175','5@qq.com',3) ;

select * from student;
select * from classes;