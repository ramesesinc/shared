[getList]
SELECT sg.*, ug.orgclass  
FROM sys_securitygroup sg 
	INNER JOIN sys_usergroup ug ON sg.usergroupid=ug.objid 
WHERE usergroupid=$P{usergroupid} 
ORDER BY sg.name  
 

[removeMembership]
DELETE FROM sys_usergroup_member WHERE objid=$P{objid} AND user_objid=$P{userid} 