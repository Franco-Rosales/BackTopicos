
create table topico(

    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(250) not null,
    fecha_creacion datetime not null,
    status tinyint not null,
    autor varchar(100) not null,
    curso varchar(100) not null,

    primary key(id)

);
