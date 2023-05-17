-- 초기 DB 설정

DROP DATABASE IF exists MacfitDB;

CREATE DATABASE MacfitDB CHARACTER SET=utf8;

USE MacfitDB;

DROP TABLE IF EXISTS video;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS follow;

CREATE TABLE video (
	no int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    viewCount int NOT NULL default 0,
	title varchar(80) NOT NULL,
    part varchar(40) NOT NULL,
	youtubeUrl varchar(100) NOT NULL,
	channelName varchar(40) NOT NULL
)ENGINE=InnoDB;

CREATE TABLE comment (
	commentNo int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    articleNo int NOT NULL,
	userNo int NOT NULL,
    content varchar(200) NOT NULL,
	created_at date NOT NULL,
    updated_at date NOT NULL
)ENGINE=InnoDB;

CREATE TABLE article (
	articleNo int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	userNo int NOT NULL,
    content varchar(200) NOT NULL,
    img varchar(100) NOT NULL,
	created_at date NOT NULL,
    updated_at date NOT NULL,
    likecnt int NOT NULL,
    viewcnt int NOT NULL
)ENGINE=InnoDB;

CREATE TABLE user (
	userNo int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    userId varchar(40) NOT NULL,
	password varchar(40) NOT NULL,
	userName varchar(40) NOT NULL,
	email varchar(100) NOT NULL,
	gender varchar(5) NOT NULL,
    age int NOT NULL
)ENGINE=InnoDB;


CREATE TABLE follow (
    followFrom int NOT NUll,
    followTo int NOT NUll
)ENGINE=InnoDB;





INSERT INTO video (viewCount, title, part, youtubeUrl, channelName)
VALUES 
(12360152,  '전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]', '전신', 'gMaB-fG4u4g', 'ThankyouBUBU'),
(13915351, '하루 15분! 전신 칼로리 불태우는 다이어트 운동', '전신', 'swRNeYw1JkY', 'ThankyouBUBU'),
(9758542, '상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]', '상체', '54tTYO-vU2E', 'ThankyouBUBU'),
(2015835, '상체비만 다이어트 최고의 운동 [상체 핵매운맛]', '상체', 'QqqZH3j_vH0', 'ThankyouBUBU'),
(1328648, '하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]', '하체', 'tzN6ypk6Sps', '김강민'),
(1965631, '저는 하체 식주의자 입니다', '하체', 'u5OgcZdNbMo', 'GYM종국'),
(9876542, '11자복근 복부 최고의 운동 [복근 핵매운맛]', '복부', 'PjGcOP-TQPE', 'ThankyouBUBU'),
(10729846, '(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)', '복부', '7TLk7pscICk', 'SomiFit');


INSERT INTO user (userId, password, userName, email,gender, age)
VALUES
('ssafylee', 1234, '이싸피', 'ssafylee@ssafy.com', 'M', 22),
('kimssafy', 0123, '김싸피', 'kimssafy@naver.com', 'W', 24),
('jeongssafy', 1357, '정싸피', 'jdd@ssafy.com', 'M', 27);


INSERT INTO article (userNo, content, img, created_at, updated_at, likecnt, viewcnt)
VALUES
(1, '첫번째 내용입니다','/dog1.jpg', now(), now(), 0, 0),
(2, '두번째 내용입니다','/dog2.jpg', now(), now(), 0, 0);


INSERT INTO comment (articleNo, userNo, content, created_at, updated_at)
VALUES
(1, 3, '첫번째 댓글입니다',now(),now()),
(2, 2, '두번째 제목', now(),now());


INSERT INTO follow (followFrom, followTo)
VALUES
('1', '2'),
('2', '1');

commit;