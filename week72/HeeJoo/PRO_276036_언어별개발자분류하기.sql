-- 스킬코드 2의 제곱수
-- 개발자 스킬코드는 사용 가능한 스킬들의 합
-- ex) 400 = 256 + 128 + 16
-- GRADE별 개발자 정보 조회
-- A : FrontEnd + Python = 2048, 8192, 16384 + 256
-- B : C# = 1024
-- C : 그 외 FrontEnd = 2048, 8192, 16384
-- GRADE, ID, EMAIL
-- ORDER BY GRADE, ID ASC;

-- 1. 조건에 맞는 코드 구하기(FE, PY, C#)
-- 2. 비트연산자 비교

WITH
FECODE AS (
    SELECT SUM(CODE) AS CODE
    FROM SKILLCODES
    WHERE CATEGORY = 'Front End'
),
PYCODE AS (
    SELECT CODE
    FROM SKILLCODES
    WHERE NAME = 'Python'
),
CCODE AS (
SELECT CODE
    FROM SKILLCODES
    WHERE NAME = 'C#'
)

SELECT (
    CASE
        WHEN (SKILL_CODE & FECODE.CODE) AND (SKILL_CODE & PYCODE.CODE) THEN 'A' # FrontEnd with Python
        WHEN SKILL_CODE & CCODE.CODE THEN 'B' # C#
        WHEN SKILL_CODE & FECODE.CODE THEN 'C' # FrontEnd
    END) AS GRADE,
    ID, EMAIL
FROM DEVELOPERS, FECODE, CCODE, PYCODE
HAVING GRADE IS NOT NULL
ORDER BY GRADE, ID ASC;
