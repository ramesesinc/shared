[getUsers]
SELECT 
u.objid as objid, 
u.username as username, 
u.lastname as lastname,
u.firstname as firstname,
ug.role as role,
ugm.org_name,
u.jobtitle as title,
ugm.usertxncode
FROM sys_usergroup_member ugm
INNER JOIN sys_usergroup ug ON ug.objid=ugm.usergroup_objid
INNER JOIN sys_user u ON u.objid=ugm.user_objid 
WHERE u.lastname LIKE '%'
AND ug.role IN (${roles})

[findUserTxnCode]
SELECT ugm.usertxncode 
FROM sys_usergroup_member ugm
INNER JOIN sys_usergroup ug ON ug.objid=ugm.usergroup_objid
WHERE ugm.user_objid = $P{userid}
AND ug.role = $P{role}