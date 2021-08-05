CREATE TABLE article(
     id LONG PRIMARY KEY auto_increment,
     name VARCHAR(255) NOT NULL,
     context VARCHAR(255) NOT NULL,
     time datetime not null default current_timestamp ,
     likes int not null default 0,
     dislikes int not null default 0
);