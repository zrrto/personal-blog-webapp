DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS article_id_visitor_ip;
DROP TABLE IF EXISTS articles;

CREATE TABLE articles
(
  id INT NOT NULL AUTO_INCREMENT,
  username CHAR(15) NOT NULL CHECK(username !=''),
  resource_id VARCHAR(200) NOT NULL,
  subject VARCHAR (100) NOT NULL,
  html LONGTEXT NOT NULL,
  content LONGTEXT NOT NULL,
  icon VARCHAR (200) NOT NULL,
  create_date TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  modify_date TIMESTAMP NULL DEFAULT '0000-00-00 00:00:00',
  access_times INT DEFAULT 0,
  comment_times INT DEFAULT 0,
  good_times INT DEFAULT 0,
  touch_times INT DEFAULT 0,
  funny_times INT DEFAULT 0,
  happy_times INT DEFAULT 0,
  anger_times INT DEFAULT 0,
  bored_times INT DEFAULT 0,
  water_times INT DEFAULT 0,
  surprise_times INT DEFAULT 0,
  PRIMARY KEY(id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 AUTO_INCREMENT = 1;

alter table articles add unique(resource_id);

CREATE TABLE comments
(
  id INT NOT NULL AUTO_INCREMENT,
  content TEXT NOT NULL,
  visitor_ip char(20) NOT NULL,
  city char(20) NOT NULL,
  article_id INT NOT NULL ,
  create_date TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  modify_date TIMESTAMP NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY(id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 AUTO_INCREMENT = 1;

ALTER TABLE comments ADD CONSTRAINT `COMMENTS_FK_ARTICLE_ID` FOREIGN KEY (`article_id`) REFERENCES articles(`id`);

create table article_id_visitor_ip (  
    article_id INT NOT NULL, 
	visitor_ip char(20) NOT NULL, 
	primary key (article_id,visitor_ip)
);

ALTER TABLE article_id_visitor_ip ADD CONSTRAINT `ARTICLE_ID_VISITOR_IP_FK_ARTICLE_ID` FOREIGN KEY (`article_id`) REFERENCES articles(`id`);