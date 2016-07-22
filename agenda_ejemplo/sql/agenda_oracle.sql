-- Creacion DB agenda curso java

--define user=alum00
--define pass=alum00

define user=&1
define pass=&1

conn &user/&pass@curso

drop table agenda;

create table agenda (
       id          integer       primary key,
       userag      varchar2(50)  not null,
       nombre      varchar2(100) not null,
       telefono    number(10)    null,
       email       varchar2(100) null
);

create unique index nombre_idx on agenda (
       nombre asc
);

drop sequence seq_agenda;

create sequence seq_agenda
       start with 1;

insert into agenda values(1, 'root', 'Eva', 123433223, 'eva@god.net');

insert into agenda values(2, 'root', 'Adan', 66666666, 'adan@god.net');

insert into agenda values(4, 'gregor', 'Gregor', 98765432, 'franz@samsa.k');
insert into agenda values(5, 'gregor', 'Oberon Gonzalez', 666777888, 'william@oberon.ur');
insert into agenda values(6, 'gregor', 'Titania Pérez', 987654321, 'hada@titania.ur');

insert into agenda values(7, 'admin', 'Evil Fire', '666666666',  'fire@evil.com');

commit;

