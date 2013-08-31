-- phpMyAdmin SQL Dump
-- version 2.8.0.1
-- http://www.phpmyadmin.net
-- 
-- Host: custsql-ipg04.eigbox.net
-- Generation Time: Dec 12, 2010 at 02:00 PM
-- Server version: 5.0.83
-- PHP Version: 4.4.9
-- 
-- Database: `techmuffin`
-- 
USE `techmuffin`;

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

INSERT INTO `admins` (`adminid`, `username`, `password`, `created`, `lastLogin`) VALUES (5, 'andy', '4c74d5ae571aba5b07845b553157fff598f8fd4fca17aca454e446232713b1a671fa1e7b039b448922b6f6ba7fad613d', '2010-10-25', '0000-00-00');

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

INSERT INTO `comics` (`comicid`, `comicname`, `location`, `created`, `title`) VALUES (2, 'UI comic', 'comic_two12082010.jpg', '2010-12-08', 'Top 10 things on the mind of a UX designer');
INSERT INTO `comics` (`comicid`, `comicname`, `location`, `created`, `title`) VALUES (1, 'Comic1', 'comic_one12072010.jpg', '2010-12-07', 'What if Jedis were Johovas witnesses?');
INSERT INTO `comics` (`comicid`, `comicname`, `location`, `created`, `title`) VALUES (3, 'threeways', 'comic_three12112010.jpg', '2010-12-12', '3 Ways to F#ck up a perfectly good Scrum Meeting');

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
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=latin1 AUTO_INCREMENT=31 ;

-- 
-- Dumping data for table `comments`
-- 

INSERT INTO `comments` (`commentid`, `ownerid`, `type`, `comment`, `name`, `ipaddress`, `created`) VALUES (23, 0, 'comics', 'Enter your comment here.', 'asdf', '::1', '2010-11-29');
INSERT INTO `comments` (`commentid`, `ownerid`, `type`, `comment`, `name`, `ipaddress`, `created`) VALUES (22, 0, 'comics', 'my comment', 'andy', '::1', '2010-11-28');
INSERT INTO `comments` (`commentid`, `ownerid`, `type`, `comment`, `name`, `ipaddress`, `created`) VALUES (21, 0, 'comics', 'Enter your comment here.', 'adsfas', '::1', '2010-11-28');
INSERT INTO `comments` (`commentid`, `ownerid`, `type`, `comment`, `name`, `ipaddress`, `created`) VALUES (20, 0, 'comics', 'Enter your comment here.', 'dfaf', '::1', '2010-11-28');
INSERT INTO `comments` (`commentid`, `ownerid`, `type`, `comment`, `name`, `ipaddress`, `created`) VALUES (25, 1, 'comics', 'You cant draw!', 'Andy', '184.189.231.192', '2010-12-07');
INSERT INTO `comments` (`commentid`, `ownerid`, `type`, `comment`, `name`, `ipaddress`, `created`) VALUES (26, 1, 'comics', 'Hey I Love this! Way cool! I love Yoda :))\r\nbtw- u can draw', 'Dennis', '184.153.186.164', '2010-12-07');
INSERT INTO `comments` (`commentid`, `ownerid`, `type`, `comment`, `name`, `ipaddress`, `created`) VALUES (27, 1, 'comics', 'Awesome!  Your guy on the right reminds me of the old Hanna Barbara toons :)', 'Tony', '64.223.231.61', '2010-12-07');
INSERT INTO `comments` (`commentid`, `ownerid`, `type`, `comment`, `name`, `ipaddress`, `created`) VALUES (28, 1, 'comics', 'I agree with Dennis, Yoda is great.', 'Eric', '216.219.125.1', '2010-12-07');
INSERT INTO `comments` (`commentid`, `ownerid`, `type`, `comment`, `name`, `ipaddress`, `created`) VALUES (29, 1, 'comics', 'Awesome!', 'ramya', '216.219.125.1', '2010-12-08');
INSERT INTO `comments` (`commentid`, `ownerid`, `type`, `comment`, `name`, `ipaddress`, `created`) VALUES (30, 1, 'comics', 'Great..!!!', 'Ja!min', '69.230.57.99', '2010-12-10');

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
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

-- 
-- Dumping data for table `quoteimages`
-- 

INSERT INTO `quoteimages` (`quoteid`, `name`, `description`, `filename`, `active`, `created`) VALUES (6, 'oldActor', 'old actor 1940s', 'oldActor.jpg', 1, '2010-12-09');
INSERT INTO `quoteimages` (`quoteid`, `name`, `description`, `filename`, `active`, `created`) VALUES (7, 'quote2', 'andy', 'getSmart.jpg', 1, '2010-12-09');

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
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

-- 
-- Dumping data for table `quotes`
-- 

INSERT INTO `quotes` (`id`, `quoteid`, `quote`, `ipaddress`, `author`, `created`) VALUES (11, 6, 'How About a cigarrette?!', '::1', 'andy', '2010-12-08');
INSERT INTO `quotes` (`id`, `quoteid`, `quote`, `ipaddress`, `author`, `created`) VALUES (12, 6, 'This is stupid!', '::1', 'bob', '2010-12-08');
INSERT INTO `quotes` (`id`, `quoteid`, `quote`, `ipaddress`, `author`, `created`) VALUES (13, 6, 'Mad Men!', '::1', 'dd', '2010-12-08');
INSERT INTO `quotes` (`id`, `quoteid`, `quote`, `ipaddress`, `author`, `created`) VALUES (14, 6, 'Shut yo mouth, woman!', '::1', 'dfaff', '2010-12-08');
INSERT INTO `quotes` (`id`, `quoteid`, `quote`, `ipaddress`, `author`, `created`) VALUES (15, 6, 'SMOKE!!', '::1', 'bob', '2010-12-08');
INSERT INTO `quotes` (`id`, `quoteid`, `quote`, `ipaddress`, `author`, `created`) VALUES (16, 7, 'The call me MR. TIBBS!!', '::1', 'bvob', '2010-12-08');
INSERT INTO `quotes` (`id`, `quoteid`, `quote`, `ipaddress`, `author`, `created`) VALUES (17, 7, 'I throw my shoe at you Mr. President!', '::1', 'bubb', '2010-12-08');
INSERT INTO `quotes` (`id`, `quoteid`, `quote`, `ipaddress`, `author`, `created`) VALUES (18, 7, 'I\\&#039m the Forrest Gump of the 60s!', '184.189.231.192', 'andy', '2010-12-11');

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

INSERT INTO `settings` (`id`, `name`, `value`) VALUES (3, 'status', 'Running');

-- --------------------------------------------------------

-- 
-- Table structure for table `subscriptions`
-- 

DROP TABLE IF EXISTS `subscriptions`;
CREATE TABLE IF NOT EXISTS `subscriptions` (
  `subscriptionid` int(11) NOT NULL auto_increment,
  `email` varchar(50) NOT NULL,
  `created` date NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY  (`subscriptionid`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

-- 
-- Dumping data for table `subscriptions`
-- 

INSERT INTO `subscriptions` (`subscriptionid`, `email`, `created`, `active`) VALUES (3, 'andy@apaladino.com', '2010-12-09', 1);
INSERT INTO `subscriptions` (`subscriptionid`, `email`, `created`, `active`) VALUES (4, 'andy_paladino@yahoo.com', '2010-12-09', 1);
INSERT INTO `subscriptions` (`subscriptionid`, `email`, `created`, `active`) VALUES (12, 'apogeelam@yahoo.com', '2010-12-11', 1);
INSERT INTO `subscriptions` (`subscriptionid`, `email`, `created`, `active`) VALUES (7, 'dinotime09@gmail.com', '2010-12-10', 1);
INSERT INTO `subscriptions` (`subscriptionid`, `email`, `created`, `active`) VALUES (13, 'andy-test10@apaladino.com', '2010-12-11', 1);

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
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

-- 
-- Dumping data for table `subtitles`
-- 

INSERT INTO `subtitles` (`subtitleid`, `phrase`, `category`, `created`) VALUES (7, 'The ultimate muffin of power!', 'andy', '2010-12-05');
INSERT INTO `subtitles` (`subtitleid`, `phrase`, `category`, `created`) VALUES (8, 'Muffins are tasty', 'andy', '2010-12-05');
INSERT INTO `subtitles` (`subtitleid`, `phrase`, `category`, `created`) VALUES (9, 'Yes, I know, my CSS skills stink. ', 'andy', '2010-12-05');
INSERT INTO `subtitles` (`subtitleid`, `phrase`, `category`, `created`) VALUES (10, 'Love the muffin!', 'andy', '2010-12-05');
INSERT INTO `subtitles` (`subtitleid`, `phrase`, `category`, `created`) VALUES (11, 'Updated Every Weekend', 'Info', '2010-12-07');
INSERT INTO `subtitles` (`subtitleid`, `phrase`, `category`, `created`) VALUES (12, 'Love my Muffin! ... LOVE IT!!', 'muffins', '2010-12-12');
