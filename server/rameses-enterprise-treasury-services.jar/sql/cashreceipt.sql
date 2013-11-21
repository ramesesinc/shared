[getTxnTypes]
SELECT * FROM cashreceipt_txntype 

[getList]
SELECT c.*, 
CASE WHEN v.receiptid IS NULL THEN 0 ELSE 1 END AS voided,  
CASE WHEN r.objid IS NULL THEN 0 ELSE 1 END AS remitted  
FROM cashreceipt c 
LEFT JOIN remittance_cashreceipt r ON c.objid=r.objid
LEFT JOIN cashreceipt_void v ON c.objid=v.receiptid
WHERE c.receiptno LIKE $P{searchtext} 
	OR c.paidby LIKE $P{searchtext}
	OR c.payer_name LIKE $P{searchtext}
ORDER BY c.formno, c.receiptno 

[getCashReceiptInfo]
SELECT c.*, 
CASE WHEN v.receiptid IS NULL THEN 0 ELSE 1 END AS voided,  
CASE WHEN r.objid IS NULL THEN 0 ELSE 1 END AS remitted,
ct.handler AS collectiontype_handler  
FROM cashreceipt c 
LEFT JOIN remittance_cashreceipt r ON c.objid=r.objid
LEFT JOIN cashreceipt_void v ON c.objid=v.receiptid
LEFT JOIN collectiontype ct ON ct.objid=c.collectiontype_objid
WHERE c.objid = $P{objid}

[getItems]
SELECT ci.*, r.fund_objid AS item_fund_objid, r.fund_title AS item_fund_title
FROM cashreceiptitem ci
INNER JOIN revenueitem r ON r.objid = ci.item_objid
WHERE ci.receiptid=$P{objid}

[getCheckPayments]
SELECT * FROM cashreceiptpayment_check WHERE receiptid=$P{objid}

[updateState]
update cashreceipt set state=$P{state} where objid=$P{objid}

