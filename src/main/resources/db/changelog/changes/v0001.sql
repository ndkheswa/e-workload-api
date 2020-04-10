create table if not exists "user" (
        id varchar(255) not null,
        name varchar(50) not null,
        primary key (id)
);

create table if not exists client (
      id bigserial not null,
      firstName varchar(100) not null,
      lastName varchar(100) not null,
      email varchar(100) not null,
      phone varchar(20) not null,
      occupation varchar(100) not null,
      gender varchar(100) not null,
      birthDate timestamp without time zone not null,
      age int not null,
      primary key (id)
);

create table if not exists booking (
     id bigserial not null,
     comment pg_largeobject not null,
     client_id bigserial not null,
     createdAt timestamp without time zone,
     updatedAt timestamp without time zone,
     primary key (id),
     foreign key (client_id) references client(id)
);