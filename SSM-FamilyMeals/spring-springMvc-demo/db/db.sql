drop database if exists db;
create database db character set utf8;
use db;

create table t_user
(
	id bigint primary key auto_increment ,
	username varchar(64) not null ,
	password varchar(64) not null ,
	name varchar(20) not null ,
	phone varchar(20) not null
);

create table t_blog
(
	id bigint primary key auto_increment ,
	title varchar(50) not null ,
	content text not null ,
	uid bigint not null ,
	createTime datetime not null ,
	foreign key(uid) references t_user(id)
);

