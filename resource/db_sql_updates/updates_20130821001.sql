USE etracs25;

ALTER TABLE `sys_usergroup_member` 
	ADD INDEX `ix_user_lastname_firstname` (`user_lastname`, `user_firstname`), 
	ADD INDEX `ix_user_firstname` (`user_firstname`),   
	ADD INDEX `ix_username` (`user_username`); 
	
	