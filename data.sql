-- Create table
create table APP_USER
(
    USER_ID           BIGINT not null,
    USER_NAME         VARCHAR(36) not null,
    ENCRYTED_PASSWORD VARCHAR(128) not null,
    ENABLED           Int not null
) ;
--
alter table APP_USER
    add constraint APP_USER_PK primary key (USER_ID);

alter table APP_USER
    add constraint APP_USER_UK unique (USER_NAME);


-- Create table
create table APP_ROLE
(
    ROLE_ID   BIGINT not null,
    ROLE_NAME VARCHAR(30) not null
) ;
--
alter table APP_ROLE
    add constraint APP_ROLE_PK primary key (ROLE_ID);

alter table APP_ROLE
    add constraint APP_ROLE_UK unique (ROLE_NAME);


-- Create table
create table USER_ROLE
(
    ID      BIGINT not null,
    USER_ID BIGINT not null,
    ROLE_ID BIGINT not null
);
--
alter table USER_ROLE
    add constraint USER_ROLE_PK primary key (ID);

alter table USER_ROLE
    add constraint USER_ROLE_UK unique (USER_ID, ROLE_ID);

alter table USER_ROLE
    add constraint USER_ROLE_FK1 foreign key (USER_ID)
        references APP_USER (USER_ID);

alter table USER_ROLE
    add constraint USER_ROLE_FK2 foreign key (ROLE_ID)
        references APP_ROLE (ROLE_ID);

create table APP_EVENT
(
    EVENT_ID  SERIAL not null primary key,
    EVENT_NAME VARCHAR(30),
    EVENT_PLACE VARCHAR(100) not null,
    EVENT_DESCRIPTION VARCHAR(100),
    EVENT_DATE timestamp
) ;

create table visitors
(
    user_id int
        constraint user_events_users_id_fk
            references app_user,
    event_id int
        constraint user_events_events_id_fk
            references app_event
);

-- Used by Spring Remember Me API.
CREATE TABLE Persistent_Logins (

                                   username varchar(64) not null,
                                   series varchar(64) not null,
                                   token varchar(64) not null,
                                   last_used timestamp not null,
                                   PRIMARY KEY (series)

);

--------------------------------------

insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED)
values (2, 'dbuser1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED)
values (1, 'dbadmin1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED)
values (3, 'Admin', '$2a$10$TH1QNEvNRwGzwPeX21Bxler.ZCOEdJpdz7UETOYBFhvm31P/NXbk2', 1);

insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED)
values (4, 'Никита Гринько', '$2a$10$Gn7zPXA.tZI8vzdn3Dfw8ukgWgpuP7Ei4KznIsXoHzENFYq/JISXm', 1);

insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED)
values (5, 'Алексей Романов', '$2a$10$HLF9vzu27aNJ2jlexkv3OuwVIaSSCDjtgQzRXKtlBkgzxv4tVPsZ.', 1);

insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED)
values (6, 'Стас Волков', '$2a$10$NOvvYIK1DEUOK/Lfu9lKYOnOo8xlzsE5M6WXsk5MPI.PYI4LOnQh2', 1);

insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED)
values (7, 'Инна Юрьева', '$2a$10$LMuQh27BIoJ00wFz2WQAq.6.V4fPii6gcb1mUBLRqD/vtw9AhlIOa', 1);

insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED)
values (8, 'Влад Шевченко', '$2a$10$O9ohMinjhYIWNv5R.zMVC.3suUSiP8aWbT8vULjIahVDrl6y0JQle', 1);


---

insert into app_role (ROLE_ID, ROLE_NAME)
values (1, 'ROLE_ADMIN');

insert into app_role (ROLE_ID, ROLE_NAME)
values (2, 'ROLE_USER');

---

insert into user_role (ID, USER_ID, ROLE_ID)
values (1, 1, 1);

insert into user_role (ID, USER_ID, ROLE_ID)
values (2, 1, 2);

insert into user_role (ID, USER_ID, ROLE_ID)
values (3, 2, 2);

insert into user_role (ID, USER_ID, ROLE_ID)
values (4, 3, 2);
insert into user_role (ID, USER_ID, ROLE_ID)
values (5, 3, 1);
insert into user_role (ID, USER_ID, ROLE_ID)
values (6, 4, 2);
insert into user_role (ID, USER_ID, ROLE_ID)
values (7, 5, 2);
insert into user_role (ID, USER_ID, ROLE_ID)
values (8, 6, 2);
insert into user_role (ID, USER_ID, ROLE_ID)
values (9, 7, 2);
insert into user_role (ID, USER_ID, ROLE_ID)
values (10, 8, 2);
---
Commit;