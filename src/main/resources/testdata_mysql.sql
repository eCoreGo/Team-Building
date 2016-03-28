insert into member(id, name, wechat_no, phone) values(1, 'user1', '1', '12345678');
insert into member(id, name, wechat_no, phone) values(2, 'user2', '2', '87654321');

insert into team(id, temp, name, description, total_foundation, total_user_balance, creation_time) values(1, false, 'eCore', 'A wonderful team!', 100, 200, '2016/03/20 11:11:11');
insert into team(id, temp, name, description, total_foundation, total_user_balance, creation_time) values(2, false, 'CCAR', 'ABC!', 100, 200, '2016/03/20 11:11:11');

insert into team_member(team_id, member_id, balance, attend_time) values(1, 1, 100, '2016/03/20 11:11:11');
insert into team_member(team_id, member_id, balance, attend_time) values(2, 2, 150, '2016/03/20 11:11:11');