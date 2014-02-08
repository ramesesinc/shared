[getList]
SELECT * FROM sys_notification_user WHERE recipientid=$P{recipientid} ORDER BY dtfiled 

[findByPrimary]
SELECT * FROM sys_notification_user WHERE objid=$P{objid} 

[findByFileid]
SELECT * FROM sys_notification_user WHERE fileid=$P{fileid} 
