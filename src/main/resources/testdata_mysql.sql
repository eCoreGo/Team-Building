insert into member(id, name, wechat_no, phone) values(1, 'user1', '1', '12345678');
insert into member(id, name, wechat_no, phone) values(2, 'user2', '2', '87654321');

insert into team(id, temp, name, description, total_foundation, total_user_balance, creation_time) values(1, false, 'eCore', 'A wonderful team!', 100, 200, '2016/03/20 11:11:11');
insert into team(id, temp, name, description, total_foundation, total_user_balance, creation_time) values(2, false, 'CCAR', 'ABC!', 100, 200, '2016/03/20 11:11:11');

insert into team_member(team_id, member_id, balance, attend_time) values(1, 1, 100, '2016/03/20 11:11:11');
insert into team_member(team_id, member_id, balance, attend_time) values(2, 2, 150, '2016/03/20 11:11:11');

insert into activity(id, name, total_cost, total_foundation_cost, description, status, start_time, end_time, team_id) values(1, "ÉÕ¿¾1", 2000, 1000, "desc", 1, '2016/03/20 11:11:11', '2016/03/24 11:11:11', 1);
insert into activity(id, name, total_cost, total_foundation_cost, description, status, start_time, end_time, team_id) values(2, "ÉÕ¿¾2", 2200, 1200, "desc", 2, '2016/03/20 11:11:11', '2016/03/24 11:11:11', 1);
insert into activity(id, name, total_cost, total_foundation_cost, description, status, start_time, end_time, team_id) values(3, "ÉÕ¿¾3", 2400, 1400, "desc", 3, '2016/03/20 11:11:11', '2016/03/24 11:11:11', 2);