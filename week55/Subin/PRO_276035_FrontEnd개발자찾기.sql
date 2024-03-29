SELECT DISTINCT D.ID, D.EMAIL, D.FIRST_NAME, D.LAST_NAME
FROM SKILLCODES S
JOIN DEVELOPERS D
WHERE S.CATEGORY = 'Front End'
AND D.SKILL_CODE & S.CODE = S.CODE
ORDER BY ID ASC
;
