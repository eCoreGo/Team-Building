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

drop table if exists user;

drop table if exists user_team;

/*==============================================================*/
/* Table: activity                                              */
/*==============================================================*/
create table activity
(
   id                   int not null,
   name                 varchar(50) not null,
   total_cost           decimal not null,
   total_foundation_cost decimal not null,
   time                 timestamp not null,
   description          varchar(500),
   status               smallint not null,
   primary key (id)
);

/*==============================================================*/
/* Table: activity_attender                                     */
/*==============================================================*/
create table activity_attender
(
   id                   int not null,
   acitivity_id         int not null,
   user_id              int not null,
   attended             boolean,
   seats                int,
   seatnumber           int not null,
   user_name            varchar(50),
   primary key (id)
);

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
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table member
(
   id                   int not null,
   name                 varchar(50) not null,
   wechat_no            varchar(50) not null,
   phone                varchar(20),
   primary key (id)
);

/*==============================================================*/
/* Table: user_team                                             */
/*==============================================================*/
create table team_member
(
   team_id              int not null,
   member_id              int not null,
   balance              decimal not null,
   attend_time          timestamp not null,
   primary key (team_id, member_id)
);