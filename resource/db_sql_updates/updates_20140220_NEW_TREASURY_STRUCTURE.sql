
INSERT INTO sys_usergroup (objid, title, domain, userclass, orgclass, role ) 
VALUES ( 'TREASURY.MASTER', 'TREASURY MASTER', 'TREASURY', 'usergroup', NULL, 'MASTER')

INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-viewFund', 'TREASURY.MASTER', 'fund', 'view', 'View');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-createFund', 'TREASURY.MASTER', 'fund', 'create', 'Create');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-openFund', 'TREASURY.MASTER', 'fund', 'open', 'Open');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-editFund', 'TREASURY.MASTER', 'fund', 'edit', 'Edit');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-deleteFund', 'TREASURY.MASTER', 'fund', 'delete', 'Delete');

INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-viewRevenueItem', 'TREASURY.MASTER', 'revenueitem', 'view', 'View');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-createRevenueItem', 'TREASURY.MASTER', 'revenueitem', 'create', 'Create');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-openRevenueItem', 'TREASURY.MASTER', 'revenueitem', 'open', 'Open');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-editRevenueItem', 'TREASURY.MASTER', 'revenueitem', 'edit', 'Edit');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-deleteRevenueItem', 'TREASURY.MASTER', 'revenueitem', 'delete', 'Delete');

INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-viewbank', 'TREASURY.MASTER', 'bank', 'view', 'View');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-createbank', 'TREASURY.MASTER', 'bank', 'create', 'Create');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-openbank', 'TREASURY.MASTER', 'bank', 'open', 'Open');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-editbank', 'TREASURY.MASTER', 'bank', 'edit', 'Edit');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-deletebank', 'TREASURY.MASTER', 'bank', 'delete', 'Delete');

INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-viewcashbook', 'TREASURY.MASTER', 'cashbook', 'view', 'View');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-createcashbook', 'TREASURY.MASTER', 'cashbook', 'create', 'Create');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-opencashbook', 'TREASURY.MASTER', 'cashbook', 'open', 'Open');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-editcashbook', 'TREASURY.MASTER', 'cashbook', 'edit', 'Edit');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-deletecashbook', 'TREASURY.MASTER', 'cashbook', 'delete', 'Delete');

INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-viewcollectiontype', 'TREASURY.MASTER', 'collectiontype', 'view', 'View');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-createcollectiontype', 'TREASURY.MASTER', 'collectiontype', 'create', 'Create');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-opencollectiontype', 'TREASURY.MASTER', 'collectiontype', 'open', 'Open');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-editcollectiontype', 'TREASURY.MASTER', 'collectiontype', 'edit', 'Edit');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-deletecollectiontype', 'TREASURY.MASTER', 'collectiontype', 'delete', 'Delete');

INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-viewcollectiongroup', 'TREASURY.MASTER', 'collectiongroup', 'view', 'View');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-createcollectiongroup', 'TREASURY.MASTER', 'collectiongroup', 'create', 'Create');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-opencollectiongroup', 'TREASURY.MASTER', 'collectiongroup', 'open', 'Open');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-editcollectiongroup', 'TREASURY.MASTER', 'collectiongroup', 'edit', 'Edit');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-deletecollectiongroup', 'TREASURY.MASTER', 'collectiongroup', 'delete', 'Delete');

INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-viewsreaccount', 'TREASURY.MASTER', 'sreaccount', 'view', 'View');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-createsreaccount', 'TREASURY.MASTER', 'sreaccount', 'create', 'Create');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-opensreaccount', 'TREASURY.MASTER', 'sreaccount', 'open', 'Open');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-editsreaccount', 'TREASURY.MASTER', 'sreaccount', 'edit', 'Edit');
INSERT INTO sys_usergroup_permission (objid,usergroup_objid,object,permission,title) VALUES ( 'TREASURY-MASTER-deletesreaccount', 'TREASURY.MASTER', 'sreaccount', 'delete', 'Delete');

ALTER TABLE revenueitem ADD org_objid varchar(50);
ALTER TABLE revenueitem ADD org_name varchar(50);

ALTER TABLE fund ADD system int;
UPDATE fund SET system=1 WHERE objid IN ('GENERAL','SEF','TRUST')





CREATE TABLE [dbo].[revenueitem_attribute_type](
	[objid] [varchar](50) NOT NULL,
	[title] [varchar](50) NULL,
	[handler] [varchar](50) NULL,
 CONSTRAINT [PK_revenueitem_attribute_type] PRIMARY KEY CLUSTERED 
(
	[objid] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

go

CREATE TABLE [dbo].[revenueitem_attribute](
	[objid] [varchar](50) NOT NULL,
	[revitemid] [varchar](50) NULL,
	[attribute_objid] [varchar](50) NULL,
	[account_objid] [varchar](50) NULL,
	[account_code] [varchar](50) NULL,
	[account_title] [varchar](250) NULL,
 CONSTRAINT [PK_revenueitem_attribute] PRIMARY KEY CLUSTERED 
(
	[objid] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE revenueitem_attribute ADD CONSTRAINT IX_revitemid_attribute_objid UNIQUE (revitemid,attribute_objid)
go

alter table revenueitem_attribute add constraint FX_revenueitem_attribute_objid foreign key( attribute_objid) references revenueitem_attribute_type(objid) ;
go

INSERT INTO revenueitem_attribute_type (objid, title, handler ) VALUES ( 'ngasstandard', 'NGAS Standard', 'accountdetail:lookup' );
INSERT INTO revenueitem_attribute_type (objid, title, handler ) VALUES ( 'srestandard', 'SRE Standard', 'sreaccount:lookup' );
INSERT INTO revenueitem_attribute_type (objid, title, handler ) VALUES ( 'businessaccounttype', 'Business Account Types', 'businessaccounttype:lookup' );
