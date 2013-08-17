ALTER TABLE `osiris3db`.`sys_usergroup_member` ADD COLUMN `user_username` VARCHAR(50) NULL AFTER `user_objid`; 

ALTER TABLE `osiris3db`.`sys_usergroup_admin` ADD COLUMN `user_username` VARCHAR(50) NULL AFTER `user_objid`; 

UPDATE sys_usergroup_member um, sys_user u SET um.user_username = u.username WHERE um.user_objid = u.objid ; 

INSERT INTO `osiris3db`.`sys_var`(`name`,`value`,`description`,`datatype`,`category`) VALUES ( 'sa_pwd','560145c20d7508ecb59223999c4654dd',NULL,NULL,NULL); 
