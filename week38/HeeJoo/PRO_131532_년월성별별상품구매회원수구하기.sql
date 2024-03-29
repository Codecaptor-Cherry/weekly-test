# COUNT(DISTINCT UI.USER_ID)를 해줘야 해당 월에 여러 번 구매한 유저의 중복을 제외할 수 있음
SELECT DATE_FORMAT(OS.SALES_DATE, "%Y") AS YEAR, DATE_FORMAT(OS.SALES_DATE, "%m") AS MONTH, UI.GENDER, COUNT(DISTINCT UI.USER_ID) AS USERS
FROM ONLINE_SALE AS OS
INNER JOIN USER_INFO AS UI
ON OS.USER_ID = UI.USER_ID
GROUP BY DATE_FORMAT(OS.SALES_DATE, "%Y-%m"), UI.GENDER
HAVING UI.GENDER IS NOT NULL
ORDER BY 1, 2, 3;

# SELECT YEAR(S.SALES_DATE) AS YEAR,
#     MONTH(S.SALES_DATE) AS MONTH,
#     I.GENDER,
#     COUNT(DISTINCT I.USER_ID) AS USERS
# FROM USER_INFO I
# INNER JOIN ONLINE_SALE S
#     ON I.USER_ID = S.USER_ID
# WHERE I.GENDER IS NOT NULL
# GROUP BY 1, 2, 3
# ORDER BY 1, 2, 3
