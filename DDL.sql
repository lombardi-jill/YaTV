
-- Database: MariaDB

START TRANSACTION;

DROP DATABASE IF EXISTS YaTV;

CREATE DATABASE YaTV;

USE YaTV;

CREATE OR REPLACE TABLE User (
	Email VARCHAR(64) PRIMARY KEY,
	FirstName VARCHAR(32),
	LastName VARCHAR(32),
	Password VARCHAR(64),
	PassSalt VARCHAR(10),
	Country VARCHAR(32)
	);

CREATE OR REPLACE TABLE App
(
	Name VARCHAR(32) PRIMARY KEY,
	Description VARCHAR(1024)
);

CREATE OR REPLACE TABLE Subscription
(	
	UserEmail VARCHAR(64),
	AppName VARCHAR(32),
	ExpirationDate DATE,
	Cost FLOAT,
	PRIMARY KEY (UserEmail, AppName), 
	CONSTRAINT `fk_subscription_useremail`
	FOREIGN KEY (UserEmail) REFERENCES User (Email)
	ON UPDATE CASCADE 
	ON DELETE CASCADE,
	CONSTRAINT `fk_subscription_appname`
	FOREIGN KEY (AppName) REFERENCES App (Name)
	ON UPDATE CASCADE 
	ON DELETE CASCADE
	
);

CREATE OR REPLACE TABLE Platform
(	Name VARCHAR(32) PRIMARY KEY,
	IsMobile BOOLEAN
);


CREATE OR REPLACE TABLE AvailableOn
(	AppName VARCHAR(32),
	Platform VARCHAR(32),
	Version VARCHAR(8),
	Rating FLOAT,
	
	PRIMARY KEY(AppName, Platform),

	CONSTRAINT `fk_available_appname`
	FOREIGN KEY (AppName) REFERENCES App (Name)
	ON UPDATE CASCADE 
	ON DELETE CASCADE, 
	CONSTRAINT `fk_available_platformname`
	FOREIGN KEY (Platform) REFERENCES Platform (Name)
	ON UPDATE CASCADE 
	ON DELETE CASCADE
);

CREATE TABLE Videos
(
	Id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Title VARCHAR(64),
	Description VARCHAR(1024),
	AppHostedOn VARCHAR(32),
	ReleaseDate DATE,
	Seconds INT,
	IsFree BOOLEAN,

	CONSTRAINT `fk_video_apphostedon`
	FOREIGN KEY (AppHostedOn) REFERENCES App (Name)
	ON UPDATE CASCADE 
	ON DELETE CASCADE

);

CREATE TABLE WatchList
(
	UserEmail VARCHAR(64),
	VideoId INT,
	IsLiked BOOLEAN NOT NULL DEFAULT 0,

	PRIMARY KEY(UserEmail, VideoId),
	
	CONSTRAINT `fk_watchlist_useremail`
	FOREIGN KEY (UserEmail) REFERENCES User (Email)
	ON UPDATE CASCADE 
	ON DELETE CASCADE,
	CONSTRAINT `fk_watchlist_video`
	FOREIGN KEY (VideoId) REFERENCES Videos (Id)
	ON UPDATE CASCADE 
	ON DELETE CASCADE
);

CREATE TABLE Tags
(
	VideoId INT,
	Tag VARCHAR(32),
	PRIMARY KEY (VideoId, Tag),
	CONSTRAINT `fk_tags_videotitle`
	FOREIGN KEY (VideoId) REFERENCES Videos (Id)
	ON UPDATE CASCADE 
	ON DELETE CASCADE
);

CREATE TABLE VideoList
(
	UserEmail VARCHAR(64),
	VideoId INT,
	
	PRIMARY KEY (UserEmail, VideoId),

	CONSTRAINT `fk_videolist_useremail`
	FOREIGN KEY (UserEmail) REFERENCES User (Email)
	ON UPDATE CASCADE 
	ON DELETE CASCADE,
	CONSTRAINT `fk_videolist_video`
	FOREIGN KEY (VideoId) REFERENCES Videos (Id)
	ON UPDATE CASCADE 
	ON DELETE CASCADE
);

CREATE TABLE Shows
(
	Id INT AUTO_INCREMENT PRIMARY KEY,
	Title VARCHAR(64), 
	Description VARCHAR(1024)
);

CREATE TABLE ShowList
(
	UserEmail VARCHAR(64),
	ShowId INT,
	
	PRIMARY KEY (UserEmail, ShowId),

	CONSTRAINT `fk_showlist_useremail`
	FOREIGN KEY (UserEmail) REFERENCES User (Email)
	ON UPDATE CASCADE 
	ON DELETE CASCADE,
	CONSTRAINT `fk_showlist_show`
	FOREIGN KEY (ShowId) REFERENCES Shows (Id)
	ON UPDATE CASCADE 
	ON DELETE CASCADE
);

CREATE TABLE Season
(
	ShowId INT,
	SeasonNumber INT,
	PRIMARY KEY (ShowId, SeasonNumber),
	CONSTRAINT `fk_season_show`
	FOREIGN KEY (ShowId) REFERENCES Shows (Id)
	ON UPDATE CASCADE 
	ON DELETE CASCADE
);

CREATE TABLE Episode
(
	ShowId INT,
	SeasonNumber INT,
	EpisodeNumber INT,
	VideoId INT,

	PRIMARY KEY(ShowId, SeasonNumber, EpisodeNumber, VideoId),
	
	CONSTRAINT `fk_episode_season`
	FOREIGN KEY (ShowId, SeasonNumber) REFERENCES Season (ShowId, SeasonNumber)
	ON UPDATE CASCADE 
	ON DELETE CASCADE,
	CONSTRAINT `fk_episode_video`
	FOREIGN KEY (VideoId) REFERENCES Videos (Id)
	ON UPDATE CASCADE 
	ON DELETE CASCADE
);

COMMIT;









