-- 초기 DB 설정

DROP DATABASE IF exists MacfitDB;

CREATE DATABASE MacfitDB CHARACTER SET=utf8;

USE MacfitDB;

DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS video;
DROP TABLE IF EXISTS follow;
DROP TABLE IF EXISTS likeArticle;

CREATE TABLE user (
    userNo int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    userId varchar(40) NOT NULL,
    password varchar(40) NOT NULL,
    userName varchar(40) NOT NULL,
    email varchar(100) NOT NULL,
    gender varchar(5) NOT NULL,
    age int NOT NULL
)ENGINE=InnoDB;

CREATE TABLE article (
    articleNo int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    userNo int NOT NULL,
    content varchar(200) NOT NULL,
    img varchar(100) NOT NULL,
    created_at datetime default now(),
    updated_at datetime default now(),
    likecnt int default 0,
    viewcnt int default 0
)ENGINE=InnoDB;

CREATE TABLE comment (
    commentNo int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    articleNo int NOT NULL,
    userNo int NOT NULL,
    content varchar(200) NOT NULL,
    created_at DATETIME DEFAULT now(),
    updated_at DATETIME DEFAULT now()
)ENGINE=InnoDB;

CREATE TABLE video (
    no int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    viewCount int NOT NULL default 0,
    title varchar(80) NOT NULL,
    part varchar(40) NOT NULL,
    youtubeUrl varchar(100) NOT NULL,
    channelName varchar(40) NOT NULL
)ENGINE=InnoDB;

CREATE TABLE follow (
    followFrom int NOT NUll,
    followTo int NOT NUll
)ENGINE=InnoDB;

CREATE TABLE likeArticle (
    ArticleNo int NOT NUll,
    UserNo int NOT NUll
)ENGINE=InnoDB;


INSERT INTO user (userId, password, userName, email, gender, age)
VALUES
('ssafylee', 1234, '이싸피', 'ssafylee@ssafy.com', 'M', 22),
('kimssafy', 0123, '김싸피', 'kimssafy@naver.com', 'W', 24),
('parkssafy', 1122, '박싸피', 'parkssafy@gmail.com', 'M', 25),
('choissafy', 2211, '최싸피', 'choissafy@naver.com', 'W', 26),
('hong', 3333, '홍싸피', 'hongssafy@naver.com', 'M', 27),
('yang', 5555, '양싸피', 'yangssafy@naver.com', 'W', 28),
('dhdhdh', 7777, '김동현', 'kimdonghyun@naver.com', 'W', 29),
('jeongssafy', 1357, '정싸피', 'jdd@ssafy.com', 'M', 30);



INSERT INTO article (userNo, content, img, created_at, updated_at, likecnt, viewcnt)
VALUES
(1, '첫번째 글입니다','/dog1.jpg', now(), now(), 0, 0),
(1, '두번째 글입니다','/dog1.jpg', now(), now(), 0, 0),
(1, '세번째 글입니다','/dog1.jpg', now(), now(), 0, 0),
(2, '네번째 글입니다','/dog1.jpg', now(), now(), 0, 0),
(2, '다섯번째 글입니다','/dog1.jpg', now(), now(), 0, 0),
(3, '6번째 굴입니다','/dog1.jpg', now(), now(), 0, 0),
(4, '7번째 내용입니다','/dog1.jpg', now(), now(), 0, 0),
(5, '8번째 내용입니다','/dog1.jpg', now(), now(), 0, 0),
(5, '9번째 굴입니다','/dog1.jpg', now(), now(), 0, 0),
(6, '10번째 글입니다','/dog1.jpg', now(), now(), 0, 0),
(6, '11번째 글입니다','/dog1.jpg', now(), now(), 0, 0),
(7, '열두번째 글입니다','/dog2.jpg', now(), now(), 0, 0);


INSERT INTO comment (articleNo, userNo, content, created_at, updated_at)
VALUES

(1, 2, '첫번째 댓글입니다',now(),now()),
(1, 3, '첫번째 댓글입니다',now(),now()),
(1, 5, '첫번째 댓글입니다',now(),now()),
(3, 7, '첫번째 댓글입니다',now(),now()),
(3, 6, '첫번째 댓글입니다',now(),now()),
(5, 7, '첫번째 댓글입니다',now(),now()),
(6, 2, '첫번째 댓글입니다',now(),now()),
(6, 4, '첫번째 댓글입니다',now(),now()),
(11, 4, '두번째 제목', now(),now());

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


INSERT INTO follow (followFrom, followTo)
VALUES
('1', '2'),
('1', '3'),
('1', '4'),
('1', '5'),
('2', '3'),
('2', '4'),
('3', '5'),
('5', '3'),
('7', '2');

INSERT INTO likeArticle (ArticleNo, UserNo)
VALUES
('1', '1'),
('1', '2'),
('1', '3'),
('2', '1'),
('2', '4'),
('2', '6'),
('2', '7'),
('8', '1'),
('8', '3'),
('9', '5');

commit;

-- 좋아요 수 데려오는 서브쿼리문 게시글조회
-- SELECT *,(SELECT count(*) FROM likeArticle WHERE articleNo = atc.articleNo) as likecnt
-- FROM article as atc
-- WHERE userNo = 1;