[getRootNodes]
SELECT so.name as caption, so.*, oc.childnodes 
FROM sys_org so
INNER JOIN sys_orgclass oc ON so.orgclass=oc.name 
WHERE so.parent_objid IS NULL 

[getChildNodes]
SELECT so.name as caption, so.*, oc.childnodes 
FROM sys_org so
INNER JOIN sys_orgclass oc ON so.orgclass=oc.name 
WHERE so.parent_objid =$P{parentid} AND so.orgclass=$P{orgclass} 

[getChildClasses]
SELECT * FROM sys_orgclass WHERE name IN ( ${filter} )


[getOrgClasses]
SELECT * FROM sys_orgclass

[getList]
SELECT * FROM sys_org WHERE orgclass=$P{orgclass} ORDER BY name 

[getLookup_old]
SELECT o.* FROM sys_org o WHERE o.orgclass=$P{orgclass} ORDER BY o.name 

[getLookup]
SELECT o.* FROM sys_org o 
WHERE o.name LIKE $P{name} AND o.parent_objid IS NOT NULL 
ORDER BY o.name 

[getRoot]
SELECT * FROM sys_org WHERE parent_objid IS NULL 

[getInfo]
SELECT * FROM sys_org WHERE name=$P{name} 
