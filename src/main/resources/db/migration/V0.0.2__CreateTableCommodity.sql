CREATE TABLE commodity (
                           commodity_id    VARCHAR(36)     not null,
                           commodity_name  VARCHAR(64)     not null,
                           description     TEXT(256)       not null,
                           price           INTEGER         not null,
                           img_url         VARCHAR(64),
                           PRIMARY KEY(commodity_id)
) engine = InnoDB charset = utf8mb4;

CREATE INDEX uni_commodity_name ON commodity(commodity_name);
-- 对commodity_name建立索引 查找时无需遍历全部内容 --

INSERT INTO commodity (commodity_id, commodity_name, description, price, img_url)
VALUES ('b859ee94-20f1-11ed-861d-0242ac120002', 'Iphone13', 'Apples iPhone is one of the most popular smartphones on the market, and for good reason. Its powerful, its sleek, and it has a huge selection of apps. If youre looking for a new mobile phone, the iPhone X is a great option. Browse the top-ranked list of iPhone 10 below along with associated reviews and opinions.', 999, 'Iphone13'),
       ('b859ee94-20f1-11ed-861d-0242ac120003', 'MacBook Pro', 'Power to change everything. Say hello to a Mac that is extreme in every way. With the greatest performance, expansion and configurability yet, it is a system created to let a wide range of professionals push the limits of what is possible.', 1699, 'MacBookPro');

