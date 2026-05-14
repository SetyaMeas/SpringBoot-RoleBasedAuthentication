create table tbl_role (
	id int primary key,
	name varchar(50) not null unique
);

create table tbl_permission (
	id int primary key,
	name varchar(50) not null unique,
	resource varchar(50) not null,
	action varchar(50) not null
);

create table tbl_role_permission (
	id serial primary key,
	role_id int not null,
	permission_id int not null,
	foreign key (role_id) references tbl_role (id),
	foreign key (permission_id) references tbl_permission (id)
);

create table tbl_user (
	id serial primary key,
	name varchar(255) not null unique,
	email varchar(255) not null unique,
	pwd varchar(255) not null,
	-- salt bytea not null,
	created_at timestamptz default NOW()
);

create table tbl_user_role (
	id serial primary key,
	user_id int not null,
	role_id int not null,
	foreign key (user_id) references tbl_user (id),
	foreign key (role_id) references tbl_role (id),
	created_at timestamptz default NOW()
);

create table tbl_task (
	id serial primary key,
	name varchar (255) not null,
	user_id int not null,
	created_at timestamptz default NOW(),
	foreign key (user_id) references tbl_user (id)
);