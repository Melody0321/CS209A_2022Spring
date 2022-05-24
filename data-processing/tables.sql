create table project
(
    id     serial primary key,
    name   varchar not null,
    url    varchar not null,
    star   int     not null,
    issues int     not null,
    watch  int     not null,
    fork   int     not null
);

create table project_time(
    project_id int not null ,
    create_time timestamp not null ,
    update_time timestamp not null ,
    foreign key (project_id)references project(id)
);

create table project_topic(
    id     serial primary key,
    project_id int not null ,
    topic varchar not null ,
    foreign key (project_id)references project(id)
);
