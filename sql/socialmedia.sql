drop database if exists mysocialmedia;

create database mysocialmedia;

use mysocialmedia;

drop table post;

create table post (
    post_id int not null auto_increment,
    photo mediumblob,
    comment mediumtext,
    poster varchar(64),
    mediatype varchar(256),
    encodedString varchar(5000),
    primary key(post_id)
    
);