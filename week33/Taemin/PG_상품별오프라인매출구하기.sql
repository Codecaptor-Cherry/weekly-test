SELECT
    PRODUCT.PRODUCT_CODE, 
    SUM(OFFLINE_SALE.SALES_AMOUNT * PRODUCT.PRICE) AS SALES
FROM
    OFFLINE_SALE
JOIN
    PRODUCT
ON
    OFFLINE_SALE.PRODUCT_ID = PRODUCT.PRODUCT_ID
GROUP BY
    PRODUCT.PRODUCT_CODE
ORDER BY
    SALES DESC, PRODUCT.PRODUCT_CODE ASC;
