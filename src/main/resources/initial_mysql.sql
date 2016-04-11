/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/3/20 18:02:35                           */
/*==============================================================*/
drop database if exists tba;

create database tba;

use tba;

drop table if exists activity;

drop table if exists activity_attender;

drop table if exists team;

drop table if exists member;

drop table if exists team_member;

/*==============================================================*/
/* Table: activity                                              */
/*==============================================================*/
create table activity
(
   id                   int auto_increment,
   name                 varchar(50) not null,
   total_cost           decimal not null,
   total_foundation_cost decimal not null,
   description          varchar(500),
   status               smallint not null,
   start_time           timestamp default current_timestamp,
   end_time             timestamp default current_timestamp,
   team_id              int,
   openTaixSchedule		boolean,
   openExchangeModule   boolean,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: activity_attender                                     */
/*==============================================================*/
create table activity_attender
(
   id                   int auto_increment,
   acitivity_id         int not null,
   user_id              int not null,
   attended             boolean,
   seats                int,
   seatnumber           int not null,
   user_name            varchar(50),
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: team                                                  */
/*==============================================================*/
create table team
(
   id                   int auto_increment,
   temp                 bool not null,
   name                 varchar(50) not null,
   description          varchar(500),
   total_foundation     decimal not null,
   total_user_balance   decimal not null,
   creation_time        timestamp not null,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table member
(
   id                   varchar(100) not null,
   name                 varchar(50) not null,
   wechat_no            varchar(50),
   phone                varchar(20),
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: user_team                                             */
/*==============================================================*/
create table team_member
(
   team_id              int not null,
   member_id            varchar(100) not null,
   balance              decimal not null,
   attend_time          timestamp not null,
   primary key (team_id, member_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table exchange_detail
(
	id					int auto_increment,
	member_id			varchar(50),
	activity_id			int,
	team_id				int,
	exchange			decimal not null,
	exchange_status		smallint not null,
	date				timestamp default current_timestamp,
	team_total			int,
	expired				boolean,
	primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;