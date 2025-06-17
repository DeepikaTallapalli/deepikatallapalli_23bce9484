show databases;
create database VIT;
show tables from VIT;
drop database VIT;
create table cse( 
sid int,
sname varchar(40),
smark int
);
use vit;
select  * from cse;
show tables from vit;
use vit;
create table cse( 
sid int,
sname varchar(40),
smark int
);
select * from cse;
insert into cse values(1,'deepika',98);
create table cse1( 
sid int,
sname varchar(40),
smark int,
sage int
);
insert into cse1 values(1,'deepika',98,20);
insert into cse1 values(2,'akshaya',88,19);
insert into cse1 values(3,'lucky',89,18);
insert into cse1 values(4,'sita',92,21);
insert into cse1 values(5,'srinu',99,22);
insert into cse1 values(6,'krishna',97,17);
insert into cse1 values(7,'rajini',78,18);
insert into cse1 values(8,'latha',81,19);
insert into cse1 values(9,'roja',68,20);
insert into cse1 values(10,'geetha',84,21);

select * from cse1;

create table cse2( 
sid int,
sname varchar(40),
smark int,
sbranch varchar(10)
);
insert into cse2 values(1,'geetha',84,'cse');
insert into cse2 values(2,'latha',92,'csbs');
insert into cse2 values(3,'radha',94,'ece');
insert into cse2 values(4,'uma',81,'mech');
insert into cse2 values(5,'raghu',74,'cse');
insert into cse2 values(6,'ramya',89,'mech');
insert into cse2 values(7,'krishna',94,'cse');
insert into cse2 values(8,'deepika',84,'ece');
insert into cse2 values(9,'lakshmi',79,'mech');
insert into cse2 values(10,'geethika',84,'cse');

select * from cse2;

desc cse2;
alter table cse2 add sadd varchar(100);
desc cse2;
alter table cse1 add smobile varchar(10);
desc cse1;
alter table cse2 drop sadd;
desc cse2;
alter table cse1 add(s_country varchar(100) default 'Inidia');
select * from cse1;
update cse2 set s_age=19 where sid=2;
select * from cse1;






