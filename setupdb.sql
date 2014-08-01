CREATE DATABASE testdb;
USE testdb;

create table customer
(
	id int primary key auto_increment,
	name varchar(100) not null
);

create table address
(
	customer_id int,
	type int,
	street varchar(100) not null,
	postal_code varchar(7) not null,
	city varchar(100) not null,
	primary key (customer_id, type),
	foreign key (customer_id) references customer(id)
);

create table email
(
	customer_id int,
	type int,
	address varchar(100) not null unique,
	primary key (customer_id, type),
	foreign key (customer_id) references customer(id)
);

commit;


