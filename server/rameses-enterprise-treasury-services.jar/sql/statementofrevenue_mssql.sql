[getNgasRootAccounts]
SELECT objid, code, title, type FROM account WHERE parentid is null  ORDER BY code 

[getSreRootAccounts]
SELECT objid, code, title, type FROM sreaccount WHERE parentid is null ORDER BY code


[getNgasSubAccounts]
SELECT objid, parentid, code, title, type 
FROM account 
WHERE parentid = $P{parentid} 
  AND type IN ('group', 'detail')
ORDER BY code 


[getSreSubAccounts]
SELECT objid, parentid, code, title, type 
FROM sreaccount 
WHERE parentid = $P{parentid} 
  AND type IN ('group', 'detail')
ORDER BY code 


[getNgasExtendedSubAccounts]
SELECT objid, parentid, code, title, type 
FROM account 
WHERE parentid = $P{parentid} 
ORDER BY code 

[getSreExtendedSubAccounts]
SELECT objid, parentid, code, title, type 
FROM sreaccount 
WHERE parentid = $P{parentid} 
ORDER BY code 

[findAccountById]
SELECT * FROM account WHERE objid = $P{objid}


[getNgasStandardRevenueItemSummaries]
SELECT t.*,
	(SELECT TOP 1 target FROM account_incometarget WHERE objid=t.objid AND year=$P{year}) AS target
FROM (
	SELECT 
		CASE WHEN acct.objid IS NULL THEN 'unmapped' ELSE acct.objid END AS objid,
		CASE WHEN acct.parentid IS NULL THEN 'unmapped' ELSE acct.parentid END AS parentid,
		CASE WHEN acct.objid IS NULL THEN 'unmapped' ELSE acct.objid END AS accountid,
		CASE WHEN acct.code IS NULL THEN 'unmapped' ELSE acct.code END AS code,
		CASE WHEN acct.title IS NULL THEN 'unmapped' ELSE acct.title END AS title,
		CASE WHEN acct.type IS NULL THEN 'unmapped' ELSE acct.type END AS type,
		SUM(cri.amount) AS amount
	FROM (
		SELECT DISTINCT lcf.liquidationid
		FROM bankdeposit bd 
			INNER JOIN bankdeposit_liquidation bl ON bd.objid = bl.bankdepositid
			INNER JOIN liquidation_cashier_fund lcf ON bl.objid = lcf.objid 
		WHERE bd.dtposted BETWEEN $P{fromdate} AND $P{todate}
		) ll
		INNER JOIN liquidation l ON ll.liquidationid = l.objid 
		INNER JOIN liquidation_remittance lr ON l.objid = lr.liquidationid
		INNER JOIN remittance r ON lr.objid = r.objid 
		INNER JOIN remittance_cashreceipt rc ON r.objid = rc.remittanceid
		INNER JOIN cashreceipt cr ON rc.objid = cr.objid 
		INNER JOIN cashreceiptitem cri ON cr.objid = cri.receiptid 
		INNER JOIN revenueitem ri ON cri.item_objid = ri.objid 
		LEFT JOIN revenueitem_account rngas ON ri.objid = rngas.objid 
		LEFT JOIN account acct ON rngas.acctid = acct.objid 
		LEFT JOIN cashreceipt_void vr ON cr.objid = vr.receiptid  
	WHERE vr.objid IS NULL 
	GROUP BY 
		acct.objid,
		acct.parentid,
		acct.code,
		acct.title,
		acct.type
		
	UNION ALL

	SELECT 
		CASE WHEN acct.objid IS NULL THEN 'unmapped' ELSE acct.objid END AS objid,
		CASE WHEN acct.parentid IS NULL THEN 'unmapped' ELSE acct.parentid END AS parentid,
		CASE WHEN acct.objid IS NULL THEN 'unmapped' ELSE acct.objid END AS accountid,
		CASE WHEN acct.code IS NULL THEN 'unmapped' ELSE acct.code END AS code,
		CASE WHEN acct.title IS NULL THEN 'unmapped' ELSE acct.title END AS title,
		CASE WHEN acct.type IS NULL THEN 'unmapped' ELSE acct.type END AS type,
		SUM(dci.amount) AS amount
	FROM directcash_collection dc
		INNER JOIN directcash_collection_item dci ON dc.objid = dci.parentid
		INNER JOIN revenueitem ri ON dci.item_objid = ri.objid 
		LEFT JOIN revenueitem_account rngas ON ri.objid = rngas.objid 
		LEFT JOIN account acct ON rngas.acctid = acct.objid 
	WHERE dc.refdate BETWEEN $P{fromdate} AND $P{todate}
	GROUP BY 
		acct.objid,
		acct.parentid,
		acct.code,
		acct.title,
		acct.type	
)t 
ORDER BY t.code   		



[getNgasExtendedRevenueItemSummaries]
SELECT t.*
FROM (
	SELECT 
		CASE 
			WHEN subacct.parentid IS NOT NULL THEN subacct.parentid 
			WHEN acct.parentid IS NOT NULL THEN acct.parentid
			ELSE 'unmapped' 
		END AS parentid,
		CASE 
			WHEN subacct.objid IS NOT NULL THEN subacct.objid 
			WHEN acct.objid IS NOT NULL THEN acct.objid
			ELSE 'unmapped' 
		END AS accountid,
		CASE 
			WHEN acct.code IS NOT NULL THEN acct.code
			ELSE 'unmapped' 
		END AS parentcode,
		CASE 
			WHEN subacct.code IS NOT NULL THEN subacct.code 
			WHEN acct.code IS NOT NULL THEN acct.code
			ELSE 'unmapped' 
		END AS code,
		CASE 
			WHEN subacct.title IS NOT NULL THEN subacct.title 
			WHEN acct.title IS NOT NULL THEN acct.title
			ELSE 'unmapped' 
		END AS title,
		CASE 
			WHEN subacct.type IS NOT NULL THEN subacct.type 
			WHEN acct.type IS NOT NULL THEN acct.type
			ELSE 'unmapped' 
		END AS type,
		SUM(cri.amount) AS amount
	FROM (
		SELECT DISTINCT lcf.liquidationid
		FROM bankdeposit bd 
			INNER JOIN bankdeposit_liquidation bl ON bd.objid = bl.bankdepositid
			INNER JOIN liquidation_cashier_fund lcf ON bl.objid = lcf.objid 
		WHERE bd.dtposted BETWEEN $P{fromdate} AND $P{todate}
		) ll
		INNER JOIN liquidation l ON ll.liquidationid = l.objid 
		INNER JOIN liquidation_remittance lr ON l.objid = lr.liquidationid
		INNER JOIN remittance r ON lr.objid = r.objid 
		INNER JOIN remittance_cashreceipt rc ON r.objid = rc.remittanceid
		INNER JOIN cashreceipt cr ON rc.objid = cr.objid 
		INNER JOIN cashreceiptitem cri ON cr.objid = cri.receiptid 
		INNER JOIN revenueitem ri ON cri.item_objid = ri.objid 
		LEFT JOIN revenueitem_account rngas ON ri.objid = rngas.objid 
		LEFT JOIN account acct ON rngas.acctid = acct.objid 
		LEFT JOIN revenueitem_subaccount rsubacct ON ri.objid = rsubacct.objid
		LEFT JOIN account subacct ON rsubacct.acctid = subacct.objid 
		LEFT JOIN cashreceipt_void vr ON cr.objid = vr.receiptid  
	WHERE vr.objid IS NULL 
	GROUP BY 
		acct.objid,
		acct.parentid,
		acct.code,
		acct.title,
		acct.type,
		subacct.objid,
		subacct.parentid,
		subacct.code,
		subacct.title,
		subacct.type 

	UNION 

	SELECT 
		CASE WHEN acct.parentid IS NULL THEN 'unmapped' ELSE acct.parentid END AS parentid,
		CASE WHEN acct.objid IS NULL THEN 'unmapped' ELSE acct.objid END AS accountid,
		CASE WHEN acct.code IS NULL THEN 'unmapped' ELSE acct.code END AS parentcode,
		CASE WHEN acct.code IS NULL THEN 'unmapped' ELSE acct.code END AS code,
		CASE WHEN acct.title IS NULL THEN 'unmapped' ELSE acct.title END AS title,
		CASE WHEN acct.type IS NULL THEN 'unmapped' ELSE acct.type END AS type,
		SUM(dci.amount) AS amount
	FROM directcash_collection dc
		INNER JOIN directcash_collection_item dci ON dc.objid = dci.parentid
		INNER JOIN revenueitem ri ON dci.item_objid = ri.objid 
		LEFT JOIN revenueitem_account rngas ON ri.objid = rngas.objid 
		LEFT JOIN account acct ON rngas.acctid = acct.objid 
	WHERE dc.refdate BETWEEN $P{fromdate} AND $P{todate}
	GROUP BY 
		acct.objid,
		acct.parentid,
		acct.code,
		acct.title,
		acct.type
) t
ORDER BY t.parentcode, t.code
  

[getNgasDetailedRevenueItemSummaries]
SELECT t.* 
FROM (
	SELECT 
		ri.objid,
		CASE 
			WHEN subacct.objid IS NOT NULL THEN subacct.objid 
			WHEN acct.objid IS NOT NULL THEN acct.objid
			ELSE 'unmapped'
		END AS parentid,
		CASE 
			WHEN subacct.objid IS NOT NULL THEN subacct.objid 
			WHEN acct.objid IS NOT NULL THEN acct.objid
			ELSE 'unmapped'
		END AS accountid,

		CASE 
			WHEN acct.code IS NOT NULL THEN acct.code
			ELSE 'unmapped'
		END AS acctcode,
		CASE 
			WHEN subacct.code IS NOT NULL THEN subacct.code 
			ELSE 'unmapped'
		END AS subacctcode,

		ri.code,
		ri.title,
		'revenueitem' AS type,
		SUM(cri.amount) AS amount
	FROM (
		SELECT DISTINCT lcf.liquidationid
		FROM bankdeposit bd 
			INNER JOIN bankdeposit_liquidation bl ON bd.objid = bl.bankdepositid
			INNER JOIN liquidation_cashier_fund lcf ON bl.objid = lcf.objid 
		WHERE bd.dtposted BETWEEN $P{fromdate} AND $P{todate}
		) ll
		INNER JOIN liquidation l ON ll.liquidationid = l.objid 
		INNER JOIN liquidation_remittance lr ON l.objid = lr.liquidationid
		INNER JOIN remittance r ON lr.objid = r.objid 
		INNER JOIN remittance_cashreceipt rc ON r.objid = rc.remittanceid
		INNER JOIN cashreceipt cr ON rc.objid = cr.objid 
		INNER JOIN cashreceiptitem cri ON cr.objid = cri.receiptid 
		INNER JOIN revenueitem ri ON cri.item_objid = ri.objid 
		LEFT JOIN revenueitem_account rngas ON ri.objid = rngas.objid 
		LEFT JOIN account acct ON rngas.acctid = acct.objid 
		LEFT JOIN revenueitem_subaccount rsubacct ON ri.objid = rsubacct.objid
		LEFT JOIN account subacct ON rsubacct.acctid = subacct.objid 
		LEFT JOIN cashreceipt_void vr ON cr.objid = vr.receiptid  
	WHERE vr.objid IS NULL 
	GROUP BY 
		acct.objid,
		acct.parentid,
		acct.code,
		subacct.objid,
		subacct.parentid,
		subacct.code,
		ri.objid,
		ri.code,
		ri.title 
	  
	UNION ALL

	SELECT 
		ri.objid,
		CASE 
			WHEN subacct.objid IS NOT NULL THEN subacct.objid 
			WHEN acct.objid IS NOT NULL THEN acct.objid
			ELSE 'unmapped'
		END AS parentid,
		CASE 
			WHEN subacct.objid IS NOT NULL THEN subacct.objid 
			WHEN acct.objid IS NOT NULL THEN acct.objid
			ELSE 'unmapped'
		END AS accountid,
		CASE 
			WHEN acct.code IS NOT NULL THEN acct.code
			ELSE 'unmapped'
		END AS acctcode,
		CASE 
			WHEN subacct.code IS NOT NULL THEN subacct.code 
			ELSE 'unmapped'
		END AS subacctcode,
		ri.code,
		ri.title,
		'revenueitem' AS type,
		SUM(dci.amount) AS amount
	FROM directcash_collection dc
		INNER JOIN directcash_collection_item dci ON dc.objid = dci.parentid
		INNER JOIN revenueitem ri ON dci.item_objid = ri.objid 
		LEFT JOIN revenueitem_account rngas ON ri.objid = rngas.objid 
		LEFT JOIN account acct ON rngas.acctid = acct.objid 
		LEFT JOIN revenueitem_subaccount rsubacct ON ri.objid = rsubacct.objid
		LEFT JOIN account subacct ON rsubacct.acctid = subacct.objid 
	WHERE dc.refdate BETWEEN $P{fromdate} AND $P{todate}
	GROUP BY 
		acct.objid,
		acct.parentid,
		acct.code,
		subacct.objid,
		subacct.parentid,
		subacct.code,
		ri.objid,
		ri.code,
		ri.title 
) t
ORDER BY t.acctcode, t.subacctcode, t.code 



#******************************************
# SRE Report
# Note: tempory add union to migrated tracs data 
#*****************************************

[getSREStandardRevenueItemSummaries]
SELECT t.*,
	(SELECT TOP 1 target FROM sreaccount_incometarget WHERE objid=t.objid AND year=$P{year}) AS target
FROM (
	SELECT 
		CASE WHEN acct.objid IS NULL THEN 'unmapped' ELSE acct.objid END AS objid,
		CASE WHEN acct.parentid IS NULL THEN 'unmapped' ELSE acct.parentid END AS parentid,
		CASE WHEN acct.objid IS NULL THEN 'unmapped' ELSE acct.objid END AS accountid,
		CASE WHEN acct.code IS NULL THEN 'unmapped' ELSE acct.code END AS code,
		CASE WHEN acct.title IS NULL THEN 'unmapped' ELSE acct.title END AS title,
		CASE WHEN acct.type IS NULL THEN 'unmapped' ELSE acct.type END AS type,
		CASE WHEN acct.title like 'REAL%PROPERTY%(BASIC)%' 
			then (
				select SUM(crip.lgushare + crip.lguintshare) 
				from 
				(
					SELECT DISTINCT lcf.liquidationid
					FROM bankdeposit bd 
						INNER JOIN bankdeposit_liquidation bl ON bd.objid = bl.bankdepositid
						INNER JOIN liquidation_cashier_fund lcf ON bl.objid = lcf.objid 
					WHERE bd.dtposted BETWEEN $P{fromdate} AND $P{todate} 
					) ll
					INNER JOIN liquidation l ON ll.liquidationid = l.objid 
					INNER JOIN liquidation_remittance lr ON l.objid = lr.liquidationid
					INNER JOIN remittance r ON lr.objid = r.objid 
					INNER JOIN remittance_cashreceipt rc ON r.objid = rc.remittanceid
					INNER JOIN cashreceipt cr ON rc.objid = cr.objid 
					INNER JOIN cashreceiptitem_rpt crip ON crip.rptreceiptid = cr.objid 

					LEFT JOIN cashreceipt_void v ON cr.objid = v.receiptid
				  where v.objid is null 

			)
		ELSE  SUM(cri.amount) END AS amount 
	FROM (
		SELECT DISTINCT lcf.liquidationid
		FROM bankdeposit bd 
			INNER JOIN bankdeposit_liquidation bl ON bd.objid = bl.bankdepositid
			INNER JOIN liquidation_cashier_fund lcf ON bl.objid = lcf.objid 
		WHERE bd.dtposted BETWEEN $P{fromdate} AND $P{todate}
		) ll
		INNER JOIN liquidation l ON ll.liquidationid = l.objid 
		INNER JOIN liquidation_remittance lr ON l.objid = lr.liquidationid
		INNER JOIN remittance r ON lr.objid = r.objid 
		INNER JOIN remittance_cashreceipt rc ON r.objid = rc.remittanceid
		INNER JOIN cashreceipt cr ON rc.objid = cr.objid 
		INNER JOIN cashreceiptitem cri ON cr.objid = cri.receiptid 
		INNER JOIN revenueitem ri ON cri.item_objid = ri.objid 
		LEFT JOIN revenueitem_sreaccount rngas ON ri.objid = rngas.objid 
		LEFT JOIN sreaccount acct ON rngas.acctid = acct.objid 
		LEFT JOIN cashreceipt_void vr ON cr.objid = vr.receiptid  
	WHERE vr.objid IS NULL 
	GROUP BY 
		acct.objid,
		acct.parentid,
		acct.code,
		acct.title,
		acct.type
		
	UNION ALL

	SELECT 
		CASE WHEN acct.objid IS NULL THEN 'unmapped' ELSE acct.objid END AS objid,
		CASE WHEN acct.parentid IS NULL THEN 'unmapped' ELSE acct.parentid END AS parentid,
		CASE WHEN acct.objid IS NULL THEN 'unmapped' ELSE acct.objid END AS accountid,
		CASE WHEN acct.code IS NULL THEN 'unmapped' ELSE acct.code END AS code,
		CASE WHEN acct.title IS NULL THEN 'unmapped' ELSE acct.title END AS title,
		CASE WHEN acct.type IS NULL THEN 'unmapped' ELSE acct.type END AS type,
		SUM(dci.amount) AS amount
	FROM directcash_collection dc
		INNER JOIN directcash_collection_item dci ON dc.objid = dci.parentid
		INNER JOIN revenueitem ri ON dci.item_objid = ri.objid 
		LEFT JOIN revenueitem_sreaccount rngas ON ri.objid = rngas.objid 
		LEFT JOIN sreaccount acct ON rngas.acctid = acct.objid 
	WHERE dc.refdate BETWEEN $P{fromdate} AND $P{todate}
	GROUP BY 
		acct.objid,
		acct.parentid,
		acct.code,
		acct.title,
		acct.type	
	
	UNION ALL	
		
	SELECT 
		CASE WHEN acct.objid IS NULL THEN 'unmapped' ELSE acct.objid END AS objid,
		CASE WHEN acct.parentid IS NULL THEN 'unmapped' ELSE acct.parentid END AS parentid,
		CASE WHEN acct.objid IS NULL THEN 'unmapped' ELSE acct.objid END AS accountid,
		CASE WHEN acct.code IS NULL THEN 'unmapped' ELSE acct.code END AS code,
		CASE WHEN acct.title IS NULL THEN 'unmapped' ELSE acct.title END AS title,
		CASE WHEN acct.type IS NULL THEN 'unmapped' ELSE acct.type END AS type,
		CASE WHEN MIN(ri.title) like 'RPT%BASIC%' then sum( tci.lgushare)
			ELSE  SUM(tci.amount) END AS amount 
	FROM tracs_remittance tr
		INNER JOIN tracs_cashreceipt tc ON tc.remittanceid = tr.objid
		INNER JOIN tracs_cashreceiptitem tci ON tci.receiptid = tc.objid 
		INNER JOIN revenueitem ri ON tci.item_objid = ri.objid 
		LEFT JOIN revenueitem_sreaccount rngas ON ri.objid = rngas.objid 
		LEFT JOIN sreaccount acct ON rngas.acctid = acct.objid 
	WHERE tr.dtposted BETWEEN $P{fromdate} AND $P{todate}
		and tc.amount > 0.0 
	GROUP BY 
		acct.objid,
		acct.parentid,
		acct.code,
		acct.title,
		acct.type		
)t 
ORDER BY t.code  



[getSREExtendedRevenueItemSummaries]
SELECT t.*,
	(SELECT TOP 1 target FROM sreaccount_incometarget WHERE objid=t.accountid AND year=$P{year}) AS target
FROM (
	SELECT 
		CASE 
			WHEN subacct.parentid IS NOT NULL THEN subacct.parentid 
			WHEN acct.parentid IS NOT NULL THEN acct.parentid
			ELSE 'unmapped' 
		END AS parentid,
		CASE 
			WHEN subacct.objid IS NOT NULL THEN subacct.objid 
			WHEN acct.objid IS NOT NULL THEN acct.objid
			ELSE 'unmapped' 
		END AS accountid,
		CASE 
			WHEN acct.code IS NOT NULL THEN acct.code
			ELSE 'unmapped' 
		END AS parentcode,
		CASE 
			WHEN subacct.code IS NOT NULL THEN subacct.code 
			WHEN acct.code IS NOT NULL THEN acct.code
			ELSE 'unmapped' 
		END AS code,
		CASE 
			WHEN subacct.title IS NOT NULL THEN subacct.title 
			WHEN acct.title IS NOT NULL THEN acct.title
			ELSE 'unmapped' 
		END AS title,
		CASE 
			WHEN subacct.type IS NOT NULL THEN subacct.type 
			WHEN acct.type IS NOT NULL THEN acct.type
			ELSE 'unmapped' 
		END AS type,
		CASE WHEN acct.title like 'REAL%PROPERTY%(BASIC)%' or acct.title like 'RPT%BASIC%'
			then (
				case when subacct.code like '588-001' then (
						select 	
							sum(lgushare)
						from 
							(
								SELECT DISTINCT lcf.liquidationid
								FROM bankdeposit bd 
									INNER JOIN bankdeposit_liquidation bl ON bd.objid = bl.bankdepositid
									INNER JOIN liquidation_cashier_fund lcf ON bl.objid = lcf.objid 
								WHERE bd.dtposted BETWEEN $P{fromdate} AND $P{todate}
							) ll
							INNER JOIN liquidation l ON ll.liquidationid = l.objid 
							INNER JOIN liquidation_remittance lr ON l.objid = lr.liquidationid
							INNER JOIN remittance r ON lr.objid = r.objid 
							INNER JOIN remittance_cashreceipt rc ON r.objid = rc.remittanceid
							INNER JOIN cashreceipt cr ON rc.objid = cr.objid 
							INNER JOIN cashreceiptitem_rpt crip ON crip.rptreceiptid = cr.objid 
							LEFT JOIN cashreceipt_void v ON cr.objid = v.receiptid
						  where v.objid is null AND crip.revtype = 'current'
					)			
				 	when subacct.code like '588-003' then (
				 		select 	
							sum(lgushare)
						from 
							(
								SELECT DISTINCT lcf.liquidationid
								FROM bankdeposit bd 
									INNER JOIN bankdeposit_liquidation bl ON bd.objid = bl.bankdepositid
									INNER JOIN liquidation_cashier_fund lcf ON bl.objid = lcf.objid 
								WHERE bd.dtposted BETWEEN $P{fromdate} AND $P{todate}
							) ll
							INNER JOIN liquidation l ON ll.liquidationid = l.objid 
							INNER JOIN liquidation_remittance lr ON l.objid = lr.liquidationid
							INNER JOIN remittance r ON lr.objid = r.objid 
							INNER JOIN remittance_cashreceipt rc ON r.objid = rc.remittanceid
							INNER JOIN cashreceipt cr ON rc.objid = cr.objid 
							INNER JOIN cashreceiptitem_rpt crip ON crip.rptreceiptid = cr.objid 
							LEFT JOIN cashreceipt_void v ON cr.objid = v.receiptid
						  where v.objid is null AND crip.revtype IN ('prior', 'previous') 				 		
				 	)
				 	when subacct.code like '588-004' then (
				 		select 	
							sum(lguintshare)
						from 
							(
								SELECT DISTINCT lcf.liquidationid
								FROM bankdeposit bd 
									INNER JOIN bankdeposit_liquidation bl ON bd.objid = bl.bankdepositid
									INNER JOIN liquidation_cashier_fund lcf ON bl.objid = lcf.objid 
								WHERE bd.dtposted BETWEEN $P{fromdate} AND $P{todate}
							) ll
							INNER JOIN liquidation l ON ll.liquidationid = l.objid 
							INNER JOIN liquidation_remittance lr ON l.objid = lr.liquidationid
							INNER JOIN remittance r ON lr.objid = r.objid 
							INNER JOIN remittance_cashreceipt rc ON r.objid = rc.remittanceid
							INNER JOIN cashreceipt cr ON rc.objid = cr.objid 
							INNER JOIN cashreceiptitem_rpt crip ON crip.rptreceiptid = cr.objid 
							LEFT JOIN cashreceipt_void v ON cr.objid = v.receiptid
						  where v.objid is null AND crip.revtype = 'current'		 		
				 	)
				 	when subacct.code like '588-005' then (
				 		select 	
							sum(lguintshare)
						from 
							(
								SELECT DISTINCT lcf.liquidationid
								FROM bankdeposit bd 
									INNER JOIN bankdeposit_liquidation bl ON bd.objid = bl.bankdepositid
									INNER JOIN liquidation_cashier_fund lcf ON bl.objid = lcf.objid 
								WHERE bd.dtposted BETWEEN $P{fromdate} AND $P{todate}
							) ll
							INNER JOIN liquidation l ON ll.liquidationid = l.objid 
							INNER JOIN liquidation_remittance lr ON l.objid = lr.liquidationid
							INNER JOIN remittance r ON lr.objid = r.objid 
							INNER JOIN remittance_cashreceipt rc ON r.objid = rc.remittanceid
							INNER JOIN cashreceipt cr ON rc.objid = cr.objid 
							INNER JOIN cashreceiptitem_rpt crip ON crip.rptreceiptid = cr.objid 
							LEFT JOIN cashreceipt_void v ON cr.objid = v.receiptid
						  where v.objid is null AND crip.revtype IN ('prior', 'previous') 				 		
				 	) 
				else (
					select 	
						sum(lguintshare + lgushare)
					from 
						(
							SELECT DISTINCT lcf.liquidationid
							FROM bankdeposit bd 
								INNER JOIN bankdeposit_liquidation bl ON bd.objid = bl.bankdepositid
								INNER JOIN liquidation_cashier_fund lcf ON bl.objid = lcf.objid 
							WHERE bd.dtposted BETWEEN $P{fromdate} AND $P{todate}
						) ll
						INNER JOIN liquidation l ON ll.liquidationid = l.objid 
						INNER JOIN liquidation_remittance lr ON l.objid = lr.liquidationid
						INNER JOIN remittance r ON lr.objid = r.objid 
						INNER JOIN remittance_cashreceipt rc ON r.objid = rc.remittanceid
						INNER JOIN cashreceipt cr ON rc.objid = cr.objid 
						INNER JOIN cashreceiptitem_rpt crip ON crip.rptreceiptid = cr.objid 
						LEFT JOIN cashreceipt_void v ON cr.objid = v.receiptid
					  where v.objid is null AND crip.revtype IN ('advance') 	
					 )
				END  
			)
		ELSE  SUM(cri.amount) END AS amount 
	FROM (
		SELECT DISTINCT lcf.liquidationid
		FROM bankdeposit bd 
			INNER JOIN bankdeposit_liquidation bl ON bd.objid = bl.bankdepositid
			INNER JOIN liquidation_cashier_fund lcf ON bl.objid = lcf.objid 
		WHERE bd.dtposted BETWEEN $P{fromdate} AND $P{todate}
		) ll
		INNER JOIN liquidation l ON ll.liquidationid = l.objid 
		INNER JOIN liquidation_remittance lr ON l.objid = lr.liquidationid
		INNER JOIN remittance r ON lr.objid = r.objid 
		INNER JOIN remittance_cashreceipt rc ON r.objid = rc.remittanceid
		INNER JOIN cashreceipt cr ON rc.objid = cr.objid 
		INNER JOIN cashreceiptitem cri ON cr.objid = cri.receiptid 
		INNER JOIN revenueitem ri ON cri.item_objid = ri.objid 
		LEFT JOIN revenueitem_sreaccount rngas ON ri.objid = rngas.objid 
		LEFT JOIN sreaccount acct ON rngas.acctid = acct.objid 
		LEFT JOIN revenueitem_sresubacct rsubacct ON ri.objid = rsubacct.objid
		LEFT JOIN sreaccount subacct ON rsubacct.acctid = subacct.objid 
		LEFT JOIN cashreceipt_void vr ON cr.objid = vr.receiptid  
	WHERE vr.objid IS NULL 
	GROUP BY 
		acct.objid,
		acct.parentid,
		acct.code,
		acct.title,
		acct.type,
		subacct.objid,
		subacct.parentid,
		subacct.code,
		subacct.title,
		subacct.type 

	UNION ALL

	SELECT 
		CASE 
			WHEN subacct.parentid IS NOT NULL THEN subacct.parentid 
			WHEN acct.parentid IS NOT NULL THEN acct.parentid
			ELSE 'unmapped' 
		END AS parentid,
		CASE 
			WHEN subacct.objid IS NOT NULL THEN subacct.objid 
			WHEN acct.objid IS NOT NULL THEN acct.objid
			ELSE 'unmapped' 
		END AS accountid,
		CASE 
			WHEN acct.code IS NOT NULL THEN acct.code
			ELSE 'unmapped' 
		END AS parentcode,
		CASE 
			WHEN subacct.code IS NOT NULL THEN subacct.code 
			WHEN acct.code IS NOT NULL THEN acct.code
			ELSE 'unmapped' 
		END AS code,
		CASE 
			WHEN subacct.title IS NOT NULL THEN subacct.title 
			WHEN acct.title IS NOT NULL THEN acct.title
			ELSE 'unmapped' 
		END AS title,
		CASE 
			WHEN subacct.type IS NOT NULL THEN subacct.type 
			WHEN acct.type IS NOT NULL THEN acct.type
			ELSE 'unmapped' 
		END AS type,
		SUM(dci.amount) AS amount
	FROM directcash_collection dc
		INNER JOIN directcash_collection_item dci ON dc.objid = dci.parentid
		INNER JOIN revenueitem ri ON dci.item_objid = ri.objid 
		LEFT JOIN revenueitem_sreaccount rngas ON ri.objid = rngas.objid 
		LEFT JOIN sreaccount acct ON rngas.acctid = acct.objid 
		LEFT JOIN revenueitem_sresubacct rsubacct ON ri.objid = rsubacct.objid
		LEFT JOIN sreaccount subacct ON rsubacct.acctid = subacct.objid 
	WHERE dc.refdate BETWEEN $P{fromdate} AND $P{todate}
	GROUP BY 
		acct.objid,
		acct.parentid,
		acct.code,
		acct.title,
		acct.type,
		subacct.objid,
		subacct.parentid,
		subacct.code,
		subacct.title,
		subacct.type 
	
	UNION ALL	


	SELECT 
		CASE 
			WHEN subacct.parentid IS NOT NULL THEN subacct.parentid 
			WHEN acct.parentid IS NOT NULL THEN acct.parentid
			ELSE 'unmapped' 
		END AS parentid,
		CASE 
			WHEN subacct.objid IS NOT NULL THEN subacct.objid 
			WHEN acct.objid IS NOT NULL THEN acct.objid
			ELSE 'unmapped' 
		END AS accountid,
		CASE 
			WHEN acct.code IS NOT NULL THEN acct.code
			ELSE 'unmapped' 
		END AS parentcode,
		CASE 
			WHEN subacct.code IS NOT NULL THEN subacct.code 
			WHEN acct.code IS NOT NULL THEN acct.code
			ELSE 'unmapped' 
		END AS code,
		CASE 
			WHEN subacct.title IS NOT NULL THEN subacct.title 
			WHEN acct.title IS NOT NULL THEN acct.title
			ELSE 'unmapped' 
		END AS title,
		CASE 
			WHEN subacct.type IS NOT NULL THEN subacct.type 
			WHEN acct.type IS NOT NULL THEN acct.type
			ELSE 'unmapped' 
		END AS type,
		CASE WHEN MIN(ri.title) like 'RPT%BASIC%' then sum( tci.lgushare)
			ELSE  SUM(tci.amount) END AS amount 
	FROM tracs_remittance tr
		INNER JOIN tracs_cashreceipt tc ON tc.remittanceid = tr.objid
		INNER JOIN tracs_cashreceiptitem tci ON tci.receiptid = tc.objid 
		INNER JOIN revenueitem ri ON tci.item_objid = ri.objid 
		LEFT JOIN revenueitem_sreaccount rngas ON ri.objid = rngas.objid 
		LEFT JOIN sreaccount acct ON rngas.acctid = acct.objid 
		LEFT JOIN revenueitem_sresubacct rsubacct ON ri.objid = rsubacct.objid
		LEFT JOIN sreaccount subacct ON rsubacct.acctid = subacct.objid 
	WHERE tr.dtposted BETWEEN $P{fromdate} AND $P{todate}
		 and tc.amount > 0.0 
	GROUP BY 
		acct.objid,
		acct.parentid,
		acct.code,
		acct.title,
		acct.type,
		subacct.objid,
		subacct.parentid,
		subacct.code,
		subacct.title,
		subacct.type 

) t
ORDER BY t.parentcode, t.code
  


[getSREDetailedRevenueItemSummaries]
SELECT t.* 
FROM (
	SELECT 
		ri.objid,
		CASE 
			WHEN subacct.objid IS NOT NULL THEN subacct.objid 
			WHEN acct.objid IS NOT NULL THEN acct.objid
			ELSE 'unmapped'
		END AS parentid,
		CASE 
			WHEN subacct.objid IS NOT NULL THEN subacct.objid 
			WHEN acct.objid IS NOT NULL THEN acct.objid
			ELSE 'unmapped'
		END AS accountid,

		CASE 
			WHEN acct.code IS NOT NULL THEN acct.code
			ELSE 'unmapped'
		END AS acctcode,
		CASE 
			WHEN subacct.code IS NOT NULL THEN subacct.code 
			ELSE 'unmapped'
		END AS subacctcode,

		ri.code,
		ri.title,
		'revenueitem' AS type,
		CASE WHEN acct.title like 'REAL%PROPERTY%(BASIC)%' or acct.title like 'RPT%BASIC%'
			then SUM(cri.amount) * 0.70 
		ELSE  SUM(cri.amount) END AS amount 
	FROM (
		SELECT DISTINCT lcf.liquidationid
		FROM bankdeposit bd 
			INNER JOIN bankdeposit_liquidation bl ON bd.objid = bl.bankdepositid
			INNER JOIN liquidation_cashier_fund lcf ON bl.objid = lcf.objid 
		WHERE bd.dtposted BETWEEN $P{fromdate} AND $P{todate}
		) ll
		INNER JOIN liquidation l ON ll.liquidationid = l.objid 
		INNER JOIN liquidation_remittance lr ON l.objid = lr.liquidationid
		INNER JOIN remittance r ON lr.objid = r.objid 
		INNER JOIN remittance_cashreceipt rc ON r.objid = rc.remittanceid
		INNER JOIN cashreceipt cr ON rc.objid = cr.objid 
		INNER JOIN cashreceiptitem cri ON cr.objid = cri.receiptid 
		INNER JOIN revenueitem ri ON cri.item_objid = ri.objid 
		LEFT JOIN revenueitem_sreaccount rngas ON ri.objid = rngas.objid 
		LEFT JOIN sreaccount acct ON rngas.acctid = acct.objid 
		LEFT JOIN revenueitem_sresubacct rsubacct ON ri.objid = rsubacct.objid
		LEFT JOIN sreaccount subacct ON rsubacct.acctid = subacct.objid 
		LEFT JOIN cashreceipt_void vr ON cr.objid = vr.receiptid  
	WHERE vr.objid IS NULL 
	GROUP BY 
		acct.objid,
		acct.parentid,
		acct.code,
		acct.title,
		subacct.objid,
		subacct.parentid,
		subacct.code,
		ri.objid,
		ri.code,
		ri.title 
	  
	UNION ALL

	SELECT 
		ri.objid,
		CASE 
			WHEN subacct.objid IS NOT NULL THEN subacct.objid 
			WHEN acct.objid IS NOT NULL THEN acct.objid
			ELSE 'unmapped'
		END AS parentid,
		CASE 
			WHEN subacct.objid IS NOT NULL THEN subacct.objid 
			WHEN acct.objid IS NOT NULL THEN acct.objid
			ELSE 'unmapped'
		END AS accountid,
		CASE 
			WHEN acct.code IS NOT NULL THEN acct.code
			ELSE 'unmapped'
		END AS acctcode,
		CASE 
			WHEN subacct.code IS NOT NULL THEN subacct.code 
			ELSE 'unmapped'
		END AS subacctcode,
		ri.code,
		ri.title,
		'revenueitem' AS type,
		SUM(dci.amount) AS amount
	FROM directcash_collection dc
		INNER JOIN directcash_collection_item dci ON dc.objid = dci.parentid
		INNER JOIN revenueitem ri ON dci.item_objid = ri.objid 
		LEFT JOIN revenueitem_sreaccount rngas ON ri.objid = rngas.objid 
		LEFT JOIN sreaccount acct ON rngas.acctid = acct.objid 
		LEFT JOIN revenueitem_sresubacct rsubacct ON ri.objid = rsubacct.objid
		LEFT JOIN sreaccount subacct ON rsubacct.acctid = subacct.objid 
	WHERE dc.refdate BETWEEN $P{fromdate} AND $P{todate}
	GROUP BY 
		acct.objid,
		acct.parentid,
		acct.code,
		acct.title,
		subacct.objid,
		subacct.parentid,
		subacct.code,
		ri.objid,
		ri.code,
		ri.title 
		
	UNION ALL	
		
	SELECT 
		ri.objid,
		CASE 
			WHEN subacct.objid IS NOT NULL THEN subacct.objid 
			WHEN acct.objid IS NOT NULL THEN acct.objid
			ELSE 'unmapped'
		END AS parentid,
		CASE 
			WHEN subacct.objid IS NOT NULL THEN subacct.objid 
			WHEN acct.objid IS NOT NULL THEN acct.objid
			ELSE 'unmapped'
		END AS accountid,
		CASE 
			WHEN acct.code IS NOT NULL THEN acct.code
			ELSE 'unmapped'
		END AS acctcode,
		CASE 
			WHEN subacct.code IS NOT NULL THEN subacct.code 
			ELSE 'unmapped'
		END AS subacctcode,
		ri.code,
		ri.title,
		'revenueitem' AS type,
		CASE WHEN MIN(ri.title) like 'RPT%BASIC%' then sum( tci.lgushare)
			ELSE  SUM(tci.amount) END AS amount 
	FROM tracs_remittance tr
		INNER JOIN tracs_cashreceipt tc ON tc.remittanceid = tr.objid
		INNER JOIN tracs_cashreceiptitem tci ON tci.receiptid = tc.objid 
		INNER JOIN revenueitem ri ON tci.item_objid = ri.objid 
		LEFT JOIN revenueitem_sreaccount rngas ON ri.objid = rngas.objid 
		LEFT JOIN sreaccount acct ON rngas.acctid = acct.objid 
		LEFT JOIN revenueitem_sresubacct rsubacct ON ri.objid = rsubacct.objid
		LEFT JOIN sreaccount subacct ON rsubacct.acctid = subacct.objid 
	WHERE tr.dtposted BETWEEN $P{fromdate} AND $P{todate}
		 and tc.amount > 0.0 
	GROUP BY 
		acct.objid,
		acct.parentid,
		acct.code,
		acct.title,
		subacct.objid,
		subacct.parentid,
		subacct.code,
		ri.objid,
		ri.code,
		ri.title
) t
ORDER BY t.acctcode, t.subacctcode, t.code 