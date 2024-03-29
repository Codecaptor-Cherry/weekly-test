SELECT CONVERT(DATE_FORMAT(START_DATE, '%c') , SIGNED) AS MONTH, CAR_ID, COUNT(*) AS RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY 
WHERE START_DATE BETWEEN '2022-08-01' AND '2022-10-31' AND
    CAR_ID IN 
    (SELECT CAR_ID
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE DATE_FORMAT(START_DATE,'%Y-%m') BETWEEN '2022-08' AND '2022-10'
     GROUP BY CAR_ID
     HAVING count(*) >= 5)
GROUP BY MONTH, CAR_ID
ORDER BY MONTH,CAR_ID DESC;
