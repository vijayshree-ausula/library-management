create table book(id int not null auto_increment, isbn int not null, title varchar(50) not null, author varchar(50) not null, genre varchar(50) default 'UNKNOWN', status varchar(15) default 'Available', primary key (id), Unique key (isbn), unique key (title));

create table quantity(id int not null auto_increment, book_id int not null, total int default 1, available int, primary key(id));

alter table quantity add constraint fk_book_quantity foreign key (book_id) references book (id) on delete cascade on update cascade;

create table member(id int not null auto_increment, name varchar(50) not null, address
 varchar(50) not null, email varchar(50) not null, phone varchar(12) not null , status varchar(10) default ‘Active’, primary key(id), Unique key (email), unique key (phone));
 
 create table issue(id int not null auto_increment, member_id int not null, member_name varchar(50) not null, book_id int not null, book_name varchar(50) not null, issue_date date, return_date date, status varchar(10), primary key (id));
 