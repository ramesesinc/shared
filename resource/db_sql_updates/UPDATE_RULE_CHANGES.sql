
/****** Object:  Table [dbo].[sys_ruleset_actiondef]    Script Date: 12/03/2013 08:41:31 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[sys_ruleset_actiondef](
	[ruleset] [varchar](50) NOT NULL,
	[actiondef] [varchar](50) NOT NULL,
 CONSTRAINT [PK_sys_ruleset_actiondef] PRIMARY KEY CLUSTERED 
(
	[ruleset] ASC,
	[actiondef] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


GO

/****** Object:  Table [dbo].[sys_ruleset_fact]    Script Date: 12/03/2013 08:41:43 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[sys_ruleset_fact](
	[ruleset] [varchar](50) NOT NULL,
	[rulefact] [varchar](50) NOT NULL,
 CONSTRAINT [PK_sys_ruleset_fact] PRIMARY KEY CLUSTERED 
(
	[ruleset] ASC,
	[rulefact] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

INSERT INTO sys_ruleset_fact (ruleset, rulefact)
SELECT ruleset, objid from sys_rule_fact
go


INSERT INTO sys_ruleset_actiondef (ruleset, actiondef)
SELECT ruleset, objid from sys_rule_actiondef
go

ALTER TABLE sys_ruleset_fact
ADD CONSTRAINT fk_rulesetfact_rulefact 
FOREIGN KEY (rulefact)
REFERENCES sys_rule_fact (objid)
go

ALTER TABLE sys_ruleset_fact
ADD CONSTRAINT fk_rulesetfact_ruleset 
FOREIGN KEY (ruleset)
REFERENCES sys_ruleset (name)
go


ALTER TABLE sys_ruleset_actiondef
ADD CONSTRAINT fk_rulesetactiondef_actiondef 
FOREIGN KEY (actiondef)
REFERENCES sys_rule_actiondef (objid)
go

ALTER TABLE sys_ruleset_actiondef
ADD CONSTRAINT fk_rulesetactiondef_ruleset 
FOREIGN KEY (ruleset)
REFERENCES sys_ruleset (name)
go

alter table sys_rule_fact drop constraint DF__sys_rule___rules__707E9C7C
go
alter table sys_rule_fact drop constraint uid_ruleset_fact
go
ALTER TABLE sys_rule_fact DROP COLUMN ruleset
go

alter table sys_rule_actiondef drop constraint DF__sys_rule___rules__4B4D17CD
go
alter table sys_rule_actiondef drop constraint uid_ruleset_actiondef
go
ALTER TABLE sys_rule_actiondef DROP COLUMN ruleset
go



