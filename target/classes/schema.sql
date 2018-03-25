create table account
(
   id varchar(20) not null,
   password varchar(255) not null,
   primary key(id)
);

create table url
(
   short_code varchar(20) not null,
   original_url varchar(255) not null,
   redirect_type int not null,
   count bigint not null,
   account_id varchar(20) not null,
   primary key(short_code)
);


commit;