-- phpMyAdmin SQL Dump
-- version 2.8.0.1
-- http://www.phpmyadmin.net
-- 
-- Host: custsql-ipg04.eigbox.net
-- Generation Time: Dec 09, 2010 at 01:34 AM
-- Server version: 5.0.83
-- PHP Version: 4.4.9
-- 
-- Database: `techmuffin`
-- 

-- --------------------------------------------------------

-- 
-- Table structure for table `admins`
-- 

DROP TABLE IF EXISTS `admins`;
CREATE TABLE IF NOT EXISTS `admins` (
  `adminid` int(11) NOT NULL auto_increment,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created` date NOT NULL,
  `lastLogin` date NOT NULL,
  PRIMARY KEY  (`adminid`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

-- 
-- Dumping data for table `admins`
-- 

INSERT INTO `admins` VALUES (5, 'andy', '4c74d5ae571aba5b07845b553157fff598f8fd4fca17aca454e446232713b1a671fa1e7b039b448922b6f6ba7fad613d', '2010-10-25', '0000-00-00');

-- --------------------------------------------------------

-- 
-- Table structure for table `comics`
-- 

DROP TABLE IF EXISTS `comics`;
CREATE TABLE IF NOT EXISTS `comics` (
  `comicid` int(11) NOT NULL auto_increment,
  `comicname` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `created` date NOT NULL,
  `title` varchar(1000) default NULL,
  PRIMARY KEY  (`comicid`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

-- 
-- Dumping data for table `comics`
-- 

INSERT INTO `comics` VALUES (2, 'UI comic', 'comic_two12082010.jpg', '2010-12-08', 'Top 10 things on the mind of a UX designer');
INSERT INTO `comics` VALUES (1, 'Comic1', 'comic_one12072010.jpg', '2010-12-07', 'What if Jedis were Johovas witnesses?');

-- --------------------------------------------------------

-- 
-- Table structure for table `comments`
-- 

DROP TABLE IF EXISTS `comments`;
CREATE TABLE IF NOT EXISTS `comments` (
  `commentid` int(11) NOT NULL auto_increment,
  `ownerid` int(11) NOT NULL,
  `type` varchar(25) NOT NULL,
  `comment` varchar(500) NOT NULL,
  `name` varchar(50) NOT NULL,
  `ipaddress` varchar(200) NOT NULL,
  `created` date NOT NULL,
  PRIMARY KEY  (`commentid`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=latin1 AUTO_INCREMENT=30 ;

-- 
-- Dumping data for table `comments`
-- 

INSERT INTO `comments` VALUES (23, 0, 'comics', 'Enter your comment here.', 'asdf', '::1', '2010-11-29');
INSERT INTO `comments` VALUES (22, 0, 'comics', 'my comment', 'andy', '::1', '2010-11-28');
INSERT INTO `comments` VALUES (21, 0, 'comics', 'Enter your comment here.', 'adsfas', '::1', '2010-11-28');
INSERT INTO `comments` VALUES (20, 0, 'comics', 'Enter your comment here.', 'dfaf', '::1', '2010-11-28');
INSERT INTO `comments` VALUES (25, 1, 'comics', 'You cant draw!', 'Andy', '184.189.231.192', '2010-12-07');
INSERT INTO `comments` VALUES (26, 1, 'comics', 'Hey I Love this! Way cool! I love Yoda :))\r\nbtw- u can draw', 'Dennis', '184.153.186.164', '2010-12-07');
INSERT INTO `comments` VALUES (27, 1, 'comics', 'Awesome!  Your guy on the right reminds me of the old Hanna Barbara toons :)', 'Tony', '64.223.231.61', '2010-12-07');
INSERT INTO `comments` VALUES (28, 1, 'comics', 'I agree with Dennis, Yoda is great.', 'Eric', '216.219.125.1', '2010-12-07');
INSERT INTO `comments` VALUES (29, 1, 'comics', 'Awesome Andy!', 'ramya', '216.219.125.1', '2010-12-08');

-- --------------------------------------------------------

-- 
-- Table structure for table `quoteimages`
-- 

DROP TABLE IF EXISTS `quoteimages`;
CREATE TABLE IF NOT EXISTS `quoteimages` (
  `quoteid` int(11) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `description` varchar(500) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `created` date NOT NULL,
  PRIMARY KEY  (`quoteid`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

-- 
-- Dumping data for table `quoteimages`
-- 

INSERT INTO `quoteimages` VALUES (6, 'oldActor', 'old actor 1940s', 'oldActor.jpg', 1, '2010-12-09');

-- --------------------------------------------------------

-- 
-- Table structure for table `quotes`
-- 

DROP TABLE IF EXISTS `quotes`;
CREATE TABLE IF NOT EXISTS `quotes` (
  `id` int(11) NOT NULL auto_increment,
  `quoteid` int(11) NOT NULL,
  `quote` varchar(500) NOT NULL,
  `ipaddress` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `created` date NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

-- 
-- Dumping data for table `quotes`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `settings`
-- 

DROP TABLE IF EXISTS `settings`;
CREATE TABLE IF NOT EXISTS `settings` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(120) NOT NULL,
  `value` varchar(120) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- 
-- Dumping data for table `settings`
-- 

INSERT INTO `settings` VALUES (3, 'status', 'Running');

-- --------------------------------------------------------

-- 
-- Table structure for table `subtitles`
-- 

DROP TABLE IF EXISTS `subtitles`;
CREATE TABLE IF NOT EXISTS `subtitles` (
  `subtitleid` int(11) NOT NULL auto_increment,
  `phrase` varchar(500) NOT NULL,
  `category` varchar(255) NOT NULL,
  `created` date NOT NULL,
  PRIMARY KEY  (`subtitleid`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

-- 
-- Dumping data for table `subtitles`
-- 

INSERT INTO `subtitles` VALUES (7, 'The ultimate muffin of power!', 'andy', '2010-12-05');
INSERT INTO `subtitles` VALUES (8, 'Muffins are tasty', 'andy', '2010-12-05');
INSERT INTO `subtitles` VALUES (9, 'Yes, I know, my CSS skills stink. ', 'andy', '2010-12-05');
INSERT INTO `subtitles` VALUES (10, 'Love the muffin!', 'andy', '2010-12-05');
INSERT INTO `subtitles` VALUES (11, 'Updated Every Weekend', 'Info', '2010-12-07');
