SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS alram;
DROP TABLE IF EXISTS ATTACH;
DROP TABLE IF EXISTS COMMENTS;
DROP TABLE IF EXISTS COMPLAINT_BOARD;
DROP TABLE IF EXISTS LOVE;
DROP TABLE IF EXISTS BOARD;
DROP TABLE IF EXISTS FOLLOW;
DROP TABLE IF EXISTS USER;




/* Create Tables */

CREATE TABLE alram
(
	alno int NOT NULL AUTO_INCREMENT,
	uno int NOT NULL,
	rdate timestamp DEFAULT now() NOT NULL,
	-- ��ȸ���� - N
	-- ��ȸ��    - Y
	state varchar(1) DEFAULT 'N' NOT NULL COMMENT '��ȸ���� - N
��ȸ��    - Y',
	-- F - �˶� -> �ȷο�
	-- L - �˶� -> ���ƿ�
	-- C - �˶� -> �Ű�
	-- R - �˶� -> ���
	-- 
	type varchar(1) DEFAULT 'M' NOT NULL COMMENT 'F - �˶� -> �ȷο�
L - �˶� -> ���ƿ�
C - �˶� -> �Ű�
R - �˶� -> ���
',
	no int NOT NULL,
	PRIMARY KEY (alno)
);


CREATE TABLE ATTACH
(
	ano int NOT NULL AUTO_INCREMENT,
	pname varchar(50) NOT NULL,
	fname varchar(200),
	bno int NOT NULL,
	PRIMARY KEY (ano)
);


CREATE TABLE BOARD
(
	bno int NOT NULL AUTO_INCREMENT,
	title varchar(100) NOT NULL,
	hit int DEFAULT 0 NOT NULL,
	-- E-Ȱ��ȭ
	-- D-��Ȱ��ȭ
	state char DEFAULT 'E' NOT NULL COMMENT 'E-Ȱ��ȭ
D-��Ȱ��ȭ',
	rdate timestamp DEFAULT now() NOT NULL,
	content text NOT NULL,
	uno int NOT NULL,
	PRIMARY KEY (bno)
);


CREATE TABLE COMMENTS
(
	cno int NOT NULL AUTO_INCREMENT,
	content text NOT NULL,
	rdate timestamp DEFAULT now() NOT NULL,
	-- E-Ȱ��ȭ
	-- D-��Ȱ��ȭ
	state char DEFAULT 'E' NOT NULL COMMENT 'E-Ȱ��ȭ
D-��Ȱ��ȭ',
	bno int NOT NULL,
	uno int NOT NULL,
	PRIMARY KEY (cno)
);


CREATE TABLE COMPLAINT_BOARD
(
	cpno int NOT NULL AUTO_INCREMENT,
	bno int NOT NULL,
	uno int NOT NULL,
	PRIMARY KEY (cpno)
);


CREATE TABLE FOLLOW
(
	fno int NOT NULL AUTO_INCREMENT,
	uno int NOT NULL,
	tuno int NOT NULL,
	PRIMARY KEY (fno)
);


CREATE TABLE LOVE
(
	lno int NOT NULL AUTO_INCREMENT,
	bno int NOT NULL,
	uno int NOT NULL,
	PRIMARY KEY (lno)
);


CREATE TABLE USER
(
	uno int NOT NULL AUTO_INCREMENT,
	uid varchar(12) NOT NULL,
	upw varchar(200) NOT NULL,
	unick varchar(20) NOT NULL,
	uemail varchar(100) NOT NULL,
	urdate timestamp DEFAULT now() NOT NULL,
	-- U-����
	-- A-������
	uauthor char DEFAULT 'U' NOT NULL COMMENT 'U-����
A-������',
	pname varchar(50),
	fname varchar(200),
	-- Ȱ��ȭ-E
	-- ��Ȱ��ȭ-D
	ustate char DEFAULT 'E' NOT NULL COMMENT 'Ȱ��ȭ-E
��Ȱ��ȭ-D',
	PRIMARY KEY (uno),
	UNIQUE (uid),
	UNIQUE (unick)
);



/* Create Foreign Keys */

ALTER TABLE ATTACH
	ADD FOREIGN KEY (bno)
	REFERENCES BOARD (bno)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE COMMENTS
	ADD FOREIGN KEY (bno)
	REFERENCES BOARD (bno)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE COMPLAINT_BOARD
	ADD FOREIGN KEY (bno)
	REFERENCES BOARD (bno)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE LOVE
	ADD FOREIGN KEY (bno)
	REFERENCES BOARD (bno)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE alram
	ADD FOREIGN KEY (uno)
	REFERENCES USER (uno)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE BOARD
	ADD FOREIGN KEY (uno)
	REFERENCES USER (uno)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE COMMENTS
	ADD FOREIGN KEY (uno)
	REFERENCES USER (uno)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE COMPLAINT_BOARD
	ADD FOREIGN KEY (uno)
	REFERENCES USER (uno)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE FOLLOW
	ADD FOREIGN KEY (uno)
	REFERENCES USER (uno)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE FOLLOW
	ADD FOREIGN KEY (tuno)
	REFERENCES USER (uno)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE LOVE
	ADD FOREIGN KEY (uno)
	REFERENCES USER (uno)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



