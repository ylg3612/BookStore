-- 创建users表
CREATE TABLE users(
id INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(100) UNIQUE NOT NULL,
PASSWORD VARCHAR(100) NOT NULL,
email VARCHAR(100)
)

-- 创建books表
CREATE TABLE books(
id INT PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(100) NOT NULL,
author VARCHAR(100) NOT NULL,
price DOUBLE(11,2) NOT NULL,
sales INT NOT NULL,
stock INT NOT NULL,
img_path VARCHAR(100)
)

-- 创建orders表
create table orders(
id varchar(100) primary key,
order_time datetime,
total_count int,
total_amount double(11,2),
state int,
user_id int,
foreign key(user_id) references users(id)
)

-- 创建orderItems表
create table order_items(
id int primary key auto_increment,
count int,
amount double(11,2),
title varchar(100),
author varchar(100),
price double(11,2),
img_path varchar(100),
order_id varchar(100),
foreign key(order_id) references orders(id)
)