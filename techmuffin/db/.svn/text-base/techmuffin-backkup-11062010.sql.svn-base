-- phpMyAdmin SQL Dump
-- version 3.3.8
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 06, 2010 at 10:07 AM
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
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `comics`
--

INSERT INTO `comics` (`comicid`, `comicname`, `location`, `created`) VALUES
(1, 'testComic', 'Koala.jpg', '2010-10-31');
