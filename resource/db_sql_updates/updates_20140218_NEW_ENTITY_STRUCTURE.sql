CREATE TABLE [dbo].[entityid](
	[objid] [varchar](50) NOT NULL,
	[entityid] [varchar](50) NOT NULL,
	[idtype] [varchar](50) NOT NULL,
	[idno] [varchar](25) NOT NULL,
	[dtissued] [date] NULL,
	[dtexpiry] [date] NULL,
 CONSTRAINT [PK__entityid__530D6FE442E1EEFE] PRIMARY KEY CLUSTERED 
(
	[objid] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [ux_entityidcard_idtype_idno] UNIQUE NONCLUSTERED 
(
	[idtype] ASC,
	[idno] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]


EXEC sp_rename 'entitymember.taxpayer_objid', 'member_objid', 'COLUMN';
EXEC sp_rename 'entitymember.taxpayer_name', 'member_name', 'COLUMN';
EXEC sp_rename 'entitymember.taxpayer_address', 'member_address', 'COLUMN';

INSERT INTO sys_usergroup (objid, title, domain, userclass, orgclass, role ) VALUES ( 'ENTITY.MASTER', 'ENTITY MASTER', 'ENTITY', 'usergroup', NULL, 'MASTER')
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title)VALUES ( 'ENTITY-MASTER-createIndividual', 'ENTITY.MASTER', 'individualentity', 'create', 'Create');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'ENTITY-MASTER-editIndividual', 'ENTITY.MASTER', 'individualentity', 'edit', 'Edit');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'ENTITY-MASTER-deleteIndividual', 'ENTITY.MASTER', 'individualentity', 'delete', 'Delete');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'ENTITY-MASTER-openIndividual', 'ENTITY.MASTER', 'individualentity', 'open', 'Open');

INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title)VALUES ( 'ENTITY-MASTER-createJuridical', 'ENTITY.MASTER', 'juridicalentity', 'create', 'Create');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'ENTITY-MASTER-editJuridical', 'ENTITY.MASTER', 'juridicalentity', 'edit', 'Edit');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'ENTITY-MASTER-deleteJuridical', 'ENTITY.MASTER', 'juridicalentity', 'delete', 'Delete');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'ENTITY-MASTER-openJuridical', 'ENTITY.MASTER', 'juridicalentity', 'open', 'Open');

INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title)VALUES ( 'ENTITY-MASTER-createMultiple', 'ENTITY.MASTER', 'multipleentity', 'create', 'Create');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'ENTITY-MASTER-editMultiple', 'ENTITY.MASTER', 'multipleentity', 'edit', 'Edit');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'ENTITY-MASTER-deleteMultiple', 'ENTITY.MASTER', 'multipleentity', 'delete', 'Delete');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'ENTITY-MASTER-openMultiple', 'ENTITY.MASTER', 'multipleentity', 'open', 'Open');
