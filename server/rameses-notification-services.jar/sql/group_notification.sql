[getList]
SELECT * FROM sys_notification_group WHERE groupid=$P{groupid} ORDER BY dtfiled 

[findByPrimary]
SELECT * FROM sys_notification_group WHERE objid=$P{objid} 

[findByFileid]
SELECT * FROM sys_notification_group WHERE fileid=$P{fileid} 

