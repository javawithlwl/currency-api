create table currency_info(id UUID primary key,
code varchar(256) unique,
symbol varchar(256),
name varchar(256),
decimal_digits int,
rounding int)