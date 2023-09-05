CREATE TABLE user (
    user_id     VARCHAR(36)     NOT NULL,
    user_name   VARCHAR(100)    BINARY NOT NULL unique,
    password    VARCHAR(36)     BINARY NOT NULL,
    primary key (user_id)
) engine = InnoDB charset = utf8;

INSERT INTO user(user_id, user_name, password) VALUES ("7895", "JHOU", "test");