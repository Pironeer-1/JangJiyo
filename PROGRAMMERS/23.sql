SELECT I.ITEM_ID, ITEM_NAME
FROM ITEM_INFO AS I JOIN ITEM_TREE AS T
    ON I.ITEM_ID = T.ITEM_ID
WHERE ISNULL(PARENT_ITEM_ID)