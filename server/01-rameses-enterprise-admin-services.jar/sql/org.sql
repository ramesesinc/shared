[getList]
SELECT * FROM sys_org WHERE orgclass=$P{orgclass} ORDER BY name 

[getOrgClasses]
SELECT * FROM sys_orgclass

[getLookup_old]
SELECT o.* FROM sys_org o WHERE o.orgclass=$P{orgclass} ORDER BY o.name 

[getLookup]
SELECT o.* FROM sys_org o 
WHERE o.name LIKE $P{name} AND o.parent_objid IS NOT NULL 
ORDER BY o.name 

[getInfo]
SELECT * FROM sys_org WHERE name=$P{name} 

[getRoot]
SELECT * FROM sys_org WHERE parent_objid IS NULL