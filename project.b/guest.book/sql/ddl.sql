CREATE TABLE article(
    id LONG PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    content VARCHAR(255) NOT NULL,
    time long not null,
    likes LONG not null default 0,
    dislikes LONG not null default 0
);