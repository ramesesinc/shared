
CREATE TABLE IF NOT EXISTS `entity_fingerprint` (
  `objid` varchar(50) character set utf8 NOT NULL default '',
  `entityid` varchar(50) character set utf8 NOT NULL default '',
  `dtfiled` datetime default NULL,
  `leftthumb_image` mediumblob NOT NULL,
  `leftthumb_fmd` mediumblob NOT NULL,
  `rightthumb_image` mediumblob NOT NULL,
  `rightthumb_fmd` mediumblob NOT NULL,
  PRIMARY KEY  (`objid`),
  KEY `FK_entity_fingerprint_entityindividual` (`entityid`),
  CONSTRAINT `FK_entity_fingerprint_entityindividual` FOREIGN KEY (`entityid`) REFERENCES `entityindividual` (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `entity_photo` (
  `objid` varchar(50) character set utf8 NOT NULL default '',
  `entityid` varchar(50) character set utf8 default NULL,
  `dtfiled` datetime default NULL,
  `image` mediumblob NOT NULL,
  PRIMARY KEY  (`objid`),
  KEY `FK_entity_photo_entityindividual` (`entityid`),
  CONSTRAINT `FK_entity_photo_entityindividual` FOREIGN KEY (`entityid`) REFERENCES `entityindividual` (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `entityindividual_physical` (
  `objid` varchar(50) character set utf8 NOT NULL default '',
  `height` varchar(10) character set utf8 NOT NULL default '',
  `weight` varchar(10) character set utf8 NOT NULL default '',
  `eyecolor` varchar(15) character set utf8 default NULL,
  `haircolor` varchar(15) character set utf8 default NULL,
  `photo_objid` varchar(50) character set utf8 default NULL,
  `fingerprint_objid` varchar(50) character set utf8 default NULL,
  `signature_objid` varchar(50) default NULL,
  PRIMARY KEY  (`objid`),
  KEY `FK_entityindividual_physical_fingerprint` (`fingerprint_objid`),
  KEY `FK_entityindividual_physical_photo` (`photo_objid`),
  KEY `ix_signatureid` (`signature_objid`),
  CONSTRAINT `FK_entityindividual_physical_entity` FOREIGN KEY (`objid`) REFERENCES `entityindividual` (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

