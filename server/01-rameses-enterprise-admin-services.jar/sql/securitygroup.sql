[getList]
SELECT * 
FROM sys_securitygroup sg
WHERE sg.usergroup_objid = $P{usergroupid}

[getUserGroupPermissions]
SELECT * FROM sys_usergroup_permission WHERE usergroup_objid=$P{usergroupid} 
 
 
[getSecurityGroupPermissions]
SELECT p.*,sg.exclude 
FROM sys_securitygroup sg
INNER JOIN sys_usergroup_permission p ON sg.usergroup_objid=p.usergroup_objid
WHERE sg.objid = $P{objid}  
