CREATE TABLE IF NOT EXISTS markers (
	id serial primary key,
	name varchar(100) not null,
	lan double precision not null,
	lon double precision not null,
	description varchar(1000),
	likes integer default 0 check(likes >= 0),
	dislikes integer default 0 check(dislikes >= 0),
	tags varchar(300),
	approved int not null default 0,
	comment_from_government varchar(1500)
);

CREATE TABLE IF NOT EXISTS comments (
	id serial primary key,
	author varchar(100) not null,
	message varchar(500) not null,
	marker int references markers(id)
);