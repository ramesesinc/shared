CREATE TABLE `bankaccount` (
  `objid` varchar(50) NOT NULL default '',
  `state` varchar(10) NOT NULL default '' COMMENT 'DRAFT, APPROVED',
  `code` varchar(50) NOT NULL default '',
  `title` varchar(100) NOT NULL default '',
  `description` varchar(255) default NULL,
  `accttype` varchar(50) NOT NULL default '' COMMENT 'CHECKING, SAVING',
  `bank_objid` varchar(50) NOT NULL,
  `bank_code` varchar(50) NOT NULL,
  `bank_name` varchar(100) NOT NULL default '',
  `fund_objid` varchar(50) NOT NULL,
  `fund_code` varchar(50) NOT NULL,
  `fund_title` varchar(50) NOT NULL default '',
  `currency` varchar(10) NOT NULL default '' COMMENT 'PHP, USD',
  `cashreport` varchar(50) default NULL,
  `cashbreakdownreport` varchar(50) default NULL,
  `checkreport` varchar(50) default NULL,
  `forwardbalance` decimal(16,2) default NULL,
  `beginbalance` decimal(16,2) default NULL,
  `totaldr` decimal(16,2) default NULL,
  `totalcr` decimal(16,2) default NULL,
  `endbalance` decimal(16,2) default NULL,
  `currentpageno` int(1) default NULL,
  `currentlineno` int(1) default NULL,
  PRIMARY KEY  (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;


CREATE TABLE `bankaccount_account` (
  `objid` varchar(50) NOT NULL default '',
  `acctid` varchar(50) NOT NULL default '',
  PRIMARY KEY  (`objid`),
  KEY `FK_bankaccount_account_account` (`acctid`),
  CONSTRAINT `FK_bankaccount_account_account` FOREIGN KEY (`acctid`) REFERENCES `account` (`objid`),
  CONSTRAINT `FK_bankaccount_account_bankaccount` FOREIGN KEY (`objid`) REFERENCES `bankaccount` (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `bankaccount_entry` (
  `objid` varchar(50) NOT NULL,
  `parentid` varchar(50) default NULL,
  `refdate` date default NULL,
  `refid` varchar(50) default NULL,
  `refno` varchar(50) default NULL,
  `reftype` varchar(50) default NULL,
  `particulars` varchar(255) default NULL,
  `dr` decimal(16,2) default NULL,
  `cr` decimal(16,2) default NULL,
  `runbalance` decimal(16,2) default NULL,
  `pageno` int(11) default NULL,
  `lineno` int(11) default NULL,
  `postingrefid` varchar(50) default NULL,
  PRIMARY KEY  (`objid`),
  UNIQUE KEY `uix_bankaccount_parent_refid_reftype` (`parentid`,`refid`,`reftype`),
  KEY `bankaccount_parent` (`parentid`),
  KEY `idx_bankaccount_refid` (`refid`),
  CONSTRAINT `bankaccount_parent` FOREIGN KEY (`parentid`) REFERENCES `bankaccount` (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `bankdeposit` (
  `objid` varchar(50) NOT NULL,
  `txnno` varchar(50) default NULL,
  `dtposted` date default NULL,
  `cashier_objid` varchar(50) default NULL,
  `cashier_name` varchar(100) default NULL,
  `cashier_title` varchar(50) default NULL,
  `totalcash` decimal(16,2) default NULL,
  `totalnoncash` decimal(16,2) default NULL,
  `amount` decimal(16,2) default NULL,
  PRIMARY KEY  (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `bankdeposit_entry` (
  `objid` varchar(50) NOT NULL,
  `parentid` varchar(50) NOT NULL,
  `bankaccount_objid` varchar(50) NOT NULL,
  `bankaccount_code` varchar(50) NOT NULL,
  `bankaccount_title` varchar(255) NOT NULL,
  `totalcash` decimal(16,2) NOT NULL,
  `totalnoncash` decimal(16,2) NOT NULL,
  `amount` decimal(16,2) NOT NULL,
  `cashbreakdown` varchar(600) NOT NULL,
  PRIMARY KEY  (`objid`),
  UNIQUE KEY `uix_bankacct_deposit` (`parentid`,`bankaccount_objid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `bankdeposit_entry_check` (
  `objid` varchar(50) NOT NULL,
  `parentid` varchar(50) default NULL,
  PRIMARY KEY  (`objid`),
  KEY `FK_bankdeposit_check` (`parentid`),
  CONSTRAINT `FK_bankdeposit_check_checkpayment` FOREIGN KEY (`objid`) REFERENCES `cashreceiptpayment_check` (`objid`),
  CONSTRAINT `FK_bankdeposit_check` FOREIGN KEY (`parentid`) REFERENCES `bankdeposit_entry` (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `bankdeposit_liquidation` (
  `objid` varchar(50) NOT NULL,
  `bankdepositid` varchar(50) default NULL,
  PRIMARY KEY  (`objid`),
  KEY `FK_bankdeposit_liquidation` (`bankdepositid`),
  CONSTRAINT `FK_bankdeposit_liquidation_fund` FOREIGN KEY (`objid`) REFERENCES `liquidation_cashier_fund` (`objid`),
  CONSTRAINT `FK_bankdeposit_liquidation` FOREIGN KEY (`bankdepositid`) REFERENCES `bankdeposit` (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `liquidation` (
  `objid` varchar(50) NOT NULL default '',
  `state` varchar(50) NOT NULL default '',
  `txnno` varchar(20) NOT NULL default '',
  `dtposted` date NOT NULL,
  `liquidatingofficer_objid` varchar(50) NOT NULL default '',
  `liquidatingofficer_name` varchar(100) NOT NULL default '',
  `liquidatingofficer_title` varchar(50) NOT NULL default '',
  `amount` decimal(18,2) default NULL,
  `totalcash` decimal(18,2) default NULL,
  `totalnoncash` decimal(18,2) default NULL,
  `cashbreakdown` varchar(600) default NULL,
  PRIMARY KEY  (`objid`),
  UNIQUE KEY `ux_liquidation_txnno` (`txnno`),
  KEY `ix_liquidation_state` (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `liquidation_cashier_fund` (
  `objid` varchar(50) NOT NULL,
  `liquidationid` varchar(50) default NULL,
  `fund_objid` varchar(50) default NULL,
  `fund_title` varchar(50) default NULL,
  `cashier_objid` varchar(50) default NULL,
  `cashier_name` varchar(50) default NULL,
  `amount` decimal(16,2) default NULL,
  PRIMARY KEY  (`objid`),
  KEY `FK_liquidation_cashier_fund_liquidation` (`liquidationid`),
  CONSTRAINT `FK_liquidation_cashier_fund_liquidation` FOREIGN KEY (`liquidationid`) REFERENCES `liquidation` (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `liquidation_remittance` (
  `objid` varchar(50) NOT NULL default '',
  `liquidationid` varchar(50) NOT NULL default '',
  PRIMARY KEY  (`objid`),
  KEY `FK_liquidationitem_liquidation` (`liquidationid`),
  CONSTRAINT `FK_liquidation_remittance_liquidation` FOREIGN KEY (`liquidationid`) REFERENCES `liquidation` (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

