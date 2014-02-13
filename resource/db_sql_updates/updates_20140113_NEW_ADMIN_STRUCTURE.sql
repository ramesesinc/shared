EXEC sp_rename 'sys_org.parentid', 'parent_objid', 'COLUMN';
EXEC sp_rename 'sys_org.parentclass', 'parent_class', 'COLUMN';

ALTER TABLE sys_org ADD code varchar(50);
UPDATE sys_org SET code=REPLACE(name,' ', '_');
ALTER TABLE sys_org ADD CONSTRAINT uix_orgcode UNIQUE (code);
UPDATE sys_org SET name=objid WHERE objid='169'

ALTER TABLE sys_orgclass ADD parentclass varchar(255);
ALTER TABLE sys_orgclass ADD handler varchar(50);
alter table sys_orgclass DROP COLUMN childnodes; 

ALTER TABLE sys_usergroup_permission ADD title varchar(50);
UPDATE sys_usergroup_permission SET title=permission;

EXEC sp_rename 'sys_usergroup_member.securitygroupid', 'securitygroup_objid', 'COLUMN';
EXEC sp_rename 'sys_usergroup_member.usergroupid', 'usergroup_objid', 'COLUMN';
ALTER TABLE sys_usergroup_member DROP COLUMN jobtitle;

EXEC sp_rename 'sys_usergroup_permission.usergroupid', 'usergroup_objid', 'COLUMN';
EXEC sp_rename 'sys_securitygroup.usergroupid', 'usergroup_objid', 'COLUMN';

ALTER TABLE sys_user ADD txncode varchar(10);
ALTER TABLE sys_usergroup_member DROP COLUMN usertxncode;

ALTER TABLE sys_usergroup_member ADD CONSTRAINT uix_ UNIQUE (usergroup_objid, user_objid,org_objid)
