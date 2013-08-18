ALTER TABLE `osiris3db`.`sys_user` CHANGE `state` `state` INT(11) NULL AFTER `objid`, CHANGE `username` `username` VARCHAR(50) CHARSET latin1 COLLATE latin1_swedish_ci NULL AFTER `state`, CHANGE `name` `name` VARCHAR(50) CHARSET latin1 COLLATE latin1_swedish_ci NULL AFTER `lastname`; 

ALTER TABLE `osiris3db`.`sys_user` ADD COLUMN `dtcreated` DATETIME NULL AFTER `state`, ADD COLUMN `createdby` VARCHAR(50) NULL AFTER `dtcreated`; 

ALTER TABLE `osiris3db`.`sys_user` CHANGE `state` `state` VARCHAR(15) NULL; 

UPDATE `osiris3db`.`sys_user` SET state='DRAFT' WHERE state=1;