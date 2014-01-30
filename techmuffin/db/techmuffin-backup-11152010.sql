-- phpMyAdmin SQL Dump
-- version 3.3.8
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 15, 2010 at 06:45 PM
-- Server version: 5.1.41
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `techmuffin`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
CREATE TABLE IF NOT EXISTS `admins` (
  `adminid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created` date NOT NULL,
  `lastLogin` date NOT NULL,
  PRIMARY KEY (`adminid`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`adminid`, `username`, `password`, `created`, `lastLogin`) VALUES
(5, 'andy', '4c74d5ae571aba5b07845b553157fff598f8fd4fca17aca454e446232713b1a671fa1e7b039b448922b6f6ba7fad613d', '2010-10-25', '0000-00-00');

-- --------------------------------------------------------

--
-- Table structure for table `comics`
--

DROP TABLE IF EXISTS `comics`;
CREATE TABLE IF NOT EXISTS `comics` (
  `comicid` int(11) NOT NULL AUTO_INCREMENT,
  `comicname` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `created` date NOT NULL,
  PRIMARY KEY (`comicid`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `comics`
--

INSERT INTO `comics` (`comicid`, `comicname`, `location`, `created`) VALUES
(1, 'test comic', 'testImg.jpg', '2010-11-15');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
CREATE TABLE IF NOT EXISTS `comments` (
  `commentid` int(11) NOT NULL AUTO_INCREMENT,
  `ownerid` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `comment` varchar(500) NOT NULL,
  `name` varchar(50) NOT NULL,
  `ipaddress` varchar(200) NOT NULL,
  `created` date NOT NULL,
  PRIMARY KEY (`commentid`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`commentid`, `ownerid`, `type`, `comment`, `name`, `ipaddress`, `created`) VALUES
(2, 1, 0, 'Enter your comment here.adfadfasfadsffasafadsfafaddfaa', 'dafdfs', '::1', '2010-11-15');

-- --------------------------------------------------------

--
-- Table structure for table `quoteimages`
--

DROP TABLE IF EXISTS `quoteimages`;
CREATE TABLE IF NOT EXISTS `quoteimages` (
  `quoteid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(500) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `created` date NOT NULL,
  PRIMARY KEY (`quoteid`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `quoteimages`
--


-- --------------------------------------------------------

--
-- Table structure for table `quotes`
--

DROP TABLE IF EXISTS `quotes`;
CREATE TABLE IF NOT EXISTS `quotes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quoteid` int(11) NOT NULL,
  `quote` varchar(500) NOT NULL,
  `ipaddress` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `created` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `quotes`
--


-- --------------------------------------------------------

--
-- Table structure for table `subtitles`
--

DROP TABLE IF EXISTS `subtitles`;
CREATE TABLE IF NOT EXISTS `subtitles` (
  `subtitleid` int(11) NOT NULL AUTO_INCREMENT,
  `phrase` varchar(500) NOT NULL,
  `category` varchar(255) NOT NULL,
  `created` date NOT NULL,
  PRIMARY KEY (`subtitleid`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `subtitles`
--

INSERT INTO `subtitles` (`subtitleid`, `phrase`, `category`, `created`) VALUES
(5, 'phrase 3333', 'aaiiasga', '2010-11-14'),
(6, 'welcome to techmuffin 1', 'cat ', '2010-11-14');
