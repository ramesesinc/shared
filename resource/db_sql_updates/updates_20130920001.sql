USE etracs25;

ALTER TABLE `account` 
	DROP INDEX `NewIndex1`, 
	ADD UNIQUE INDEX `uix_code` (`code`), 
	ADD INDEX `ix_parentid` (`parentid`), 
	ADD INDEX `ix_title` (`title`); 
	
CREATE TABLE IF NOT EXISTS `sreaccount` ( 
  `objid` varchar(50) NOT NULL,
  `parentid` varchar(50) default NULL,
  `state` varchar(10) default NULL,
  `chartid` varchar(50) default NULL,
  `code` varchar(50) default NULL,
  `title` varchar(255) default NULL,
  `type` varchar(20) default NULL,
  `acctgroup` varchar(50) default NULL,
  PRIMARY KEY  (`objid`),
  UNIQUE KEY `uix_code` (`code`),
  KEY `ix_parentid` (`parentid`),
  KEY `ix_title` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT IGNORE INTO sys_usergroup (objid,title,domain,userclass,orgclass) 
VALUES ('ACCOUNT_DATAMGMT','ACCOUNT DATA MANAGEMENT','ACCOUNTS','usergroup',NULL); 

INSERT IGNORE INTO sys_securitygroup (objid,`name`,usergroupid,exclude) 
VALUES ('ACCOUNT_DATAMGMT','ACCOUNT DATA MANAGEMENT','ACCOUNT_DATAMGMT',NULL); 

INSERT IGNORE INTO sys_usergroup (objid,title,domain,userclass,orgclass) 
VALUES ('TREASURY_DATAMGMT','TREASURY DATA MANAGEMENT','TREASURY','usergroup',NULL); 

INSERT IGNORE INTO sys_securitygroup (objid,`name`,usergroupid,exclude) 
VALUES ('TREASURY-DATAMGMT-SG','TREASURY DATA MANAGEMENT','TREASURY_DATAMGMT',NULL); 

CREATE TABLE IF NOT EXISTS `revenueitem_sreaccount` (
  `objid` varchar(50) NOT NULL default '',
  `acctid` varchar(50) NOT NULL default '',
  KEY `FK_revenueitem_sreaccount_sreaccount` (`acctid`),
  CONSTRAINT `FK_revenueitem_sreaccount_sreaccount` FOREIGN KEY (`acctid`) REFERENCES `sreaccount` (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT IGNORE INTO account_segment (`name`,objectname,title,sortorder,depends,source,valuetype) 
VALUES ('sreaccount','revenueitem','SRE Account',3,NULL,'sreaccount','single'); 
