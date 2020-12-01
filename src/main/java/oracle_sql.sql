???prompt PL/SQL Developer Export Tables for user ORACLE@ORCL
prompt Created by Administrator on 2020��12��1��
set feedback off
set define off

prompt Creating ARTICLE...
create table ARTICLE
(
  aid         NUMBER(10) not null,
  author      VARCHAR2(255),
  create_time DATE,
  title       VARCHAR2(255)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ARTICLE
  add primary key (AID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating STUDENT...
create table STUDENT
(
  id          NUMBER(10) not null,
  addr        VARCHAR2(255),
  age         NUMBER(10),
  birth       DATE,
  create_time DATE,
  name        VARCHAR2(50) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STUDENT
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Disabling triggers for ARTICLE...
alter table ARTICLE disable all triggers;
prompt Disabling triggers for STUDENT...
alter table STUDENT disable all triggers;
prompt Deleting STUDENT...
delete from STUDENT;
commit;
prompt Deleting ARTICLE...
delete from ARTICLE;
commit;
prompt Loading ARTICLE...
insert into ARTICLE (aid, author, create_time, title)
values (700, '天神兽', to_date('26-11-2020 16:01:51', 'dd-mm-yyyy hh24:mi:ss'), '数码宝贝3');
insert into ARTICLE (aid, author, create_time, title)
values (653, '暴龙兽', to_date('26-11-2020 11:26:23', 'dd-mm-yyyy hh24:mi:ss'), '数码宝贝');
insert into ARTICLE (aid, author, create_time, title)
values (652, '天神兽', to_date('26-11-2020 11:26:23', 'dd-mm-yyyy hh24:mi:ss'), '数码宝贝');
commit;
prompt 3 records loaded
prompt Loading STUDENT...
insert into STUDENT (id, addr, age, birth, create_time, name)
values (200, '上海', 110, to_date('27-11-2020', 'dd-mm-yyyy'), to_date('27-11-2020 15:26:33', 'dd-mm-yyyy hh24:mi:ss'), '大神WR');
insert into STUDENT (id, addr, age, birth, create_time, name)
values (450, '北京', 110, to_date('27-11-2020', 'dd-mm-yyyy'), to_date('27-11-2020 16:13:52', 'dd-mm-yyyy hh24:mi:ss'), '国服');
insert into STUDENT (id, addr, age, birth, create_time, name)
values (300, '上海', 110, to_date('27-11-2020', 'dd-mm-yyyy'), to_date('27-11-2020 15:39:40', 'dd-mm-yyyy hh24:mi:ss'), '私服');
insert into STUDENT (id, addr, age, birth, create_time, name)
values (400, '北京', 110, to_date('27-11-2020', 'dd-mm-yyyy'), to_date('27-11-2020 16:12:59', 'dd-mm-yyyy hh24:mi:ss'), '国服');
insert into STUDENT (id, addr, age, birth, create_time, name)
values (500, '迪拜', 129, to_date('27-11-2020', 'dd-mm-yyyy'), to_date('27-11-2020 16:39:17', 'dd-mm-yyyy hh24:mi:ss'), '私服55');
insert into STUDENT (id, addr, age, birth, create_time, name)
values (700, '上海', 110, to_date('27-11-2020', 'dd-mm-yyyy'), to_date('27-11-2020 16:59:28', 'dd-mm-yyyy hh24:mi:ss'), '私服');
insert into STUDENT (id, addr, age, birth, create_time, name)
values (650, '上海', 110, to_date('27-11-2020', 'dd-mm-yyyy'), to_date('27-11-2020 16:55:42', 'dd-mm-yyyy hh24:mi:ss'), '私服');
insert into STUDENT (id, addr, age, birth, create_time, name)
values (550, '上海', 110, to_date('27-11-2020', 'dd-mm-yyyy'), to_date('27-11-2020 16:28:17', 'dd-mm-yyyy hh24:mi:ss'), '私服');
insert into STUDENT (id, addr, age, birth, create_time, name)
values (551, '北京', 110, to_date('27-11-2020', 'dd-mm-yyyy'), to_date('27-11-2020 16:28:17', 'dd-mm-yyyy hh24:mi:ss'), '国服');
insert into STUDENT (id, addr, age, birth, create_time, name)
values (552, '上海', 110, to_date('27-11-2020', 'dd-mm-yyyy'), to_date('27-11-2020 16:28:17', 'dd-mm-yyyy hh24:mi:ss'), '私服');
insert into STUDENT (id, addr, age, birth, create_time, name)
values (600, '上海', 110, to_date('27-11-2020', 'dd-mm-yyyy'), to_date('27-11-2020 16:39:17', 'dd-mm-yyyy hh24:mi:ss'), '私服');
insert into STUDENT (id, addr, age, birth, create_time, name)
values (601, '北京', 110, to_date('27-11-2020', 'dd-mm-yyyy'), to_date('27-11-2020 16:39:17', 'dd-mm-yyyy hh24:mi:ss'), '国服');
insert into STUDENT (id, addr, age, birth, create_time, name)
values (602, '上海', 110, to_date('27-11-2020', 'dd-mm-yyyy'), to_date('27-11-2020 16:39:17', 'dd-mm-yyyy hh24:mi:ss'), '私服');
commit;
prompt 13 records loaded
prompt Enabling triggers for ARTICLE...
alter table ARTICLE enable all triggers;
prompt Enabling triggers for STUDENT...
alter table STUDENT enable all triggers;

set feedback on
set define on
prompt Done
