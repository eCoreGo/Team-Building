/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/3/20 18:02:35                           */
/*==============================================================*/

drop table if exists activity;

drop table if exists activity_attender;

drop table if exists taxi_schedule;

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
   id                   int not null,
   temp                 bool not null,
   name                 varchar(50) not null,
   total_foundation     decimal not null,
   total_user_balance   decimal not null,
   creation_time        timestamp not null,
   primary key (id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
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
create table user_team
(
   team_id              int not null,
   user_id              int not null,
   balance              decimal not null,
   attend_time          timestamp not null,
   primary key (team_id, user_id)
);

alter table activity add constraint FK_Relationship_4 foreign key (id)
      references team (id) on delete restrict on update restrict;

alter table activity_attender add constraint FK_Relationship_6 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table activity_attender add constraint FK_Relationship_7 foreign key (id)
      references activity (id) on delete restrict on update restrict;

alter table taxi_schedule add constraint FK_Relationship_8 foreign key (id)
      references activity (id) on delete restrict on update restrict;

alter table user_team add constraint FK_Relationship_2 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table user_team add constraint FK_Relationship_3 foreign key (team_id)
      references team (id) on delete restrict on update restrict;

