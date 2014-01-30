-- phpMyAdmin SQL Dump
-- version 2.8.0.1
-- http://www.phpmyadmin.net
-- 
-- Host: custsql-ipg04.eigbox.net
-- Generation Time: Dec 16, 2010 at 02:36 AM
-- Server version: 5.0.83
-- PHP Version: 4.4.9
-- 
-- Database: `techmuffin`
-- 

-- --------------------------------------------------------

-- 
-- Table structure for table `subscriptions`
-- 
USE `techmuffin`;

DROP TABLE IF EXISTS `subscriptions`;
CREATE TABLE IF NOT EXISTS `subscriptions` (
  `subscriptionid` int(11) NOT NULL auto_increment,
  `email` varchar(50) NOT NULL,
  `created` date NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY  (`subscriptionid`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

-- 
-- Dumping data for table `subscriptions`
-- 

INSERT INTO `subscriptions` VALUES (3, 'andy@apaladino.com', '2010-12-09', 1);
INSERT INTO `subscriptions` VALUES (4, 'andy_paladino@yahoo.com', '2010-12-09', 1);
INSERT INTO `subscriptions` VALUES (12, 'apogeelam@yahoo.com', '2010-12-11', 1);
INSERT INTO `subscriptions` VALUES (7, 'dinotime09@gmail.com', '2010-12-10', 1);
