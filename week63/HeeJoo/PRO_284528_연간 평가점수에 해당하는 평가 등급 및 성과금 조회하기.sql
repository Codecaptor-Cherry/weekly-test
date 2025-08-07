-- 사원별 성과금 정보를 조회 : 사번, 성명, 평가 등급, 성과금
-- 사번 오름차순 정렬
-- 96(S) : 20% / 90(A) : 15% / 80(B) : 10% / 이외(C) : 0%

with avg_score as (
    select emp_no, (
        case 
            when avg(score) >= 96 then 'S'
            when avg(score) >= 90 then 'A'
            when avg(score) >= 80 then 'B'
            else 'C'
        end
    ) as grade,
    (
        case 
            when avg(score) >= 96 then 0.2
            when avg(score) >= 90 then 0.15
            when avg(score) >= 80 then 0.1
            else 0
        end
    ) as incentive
    from hr_grade
    group by emp_no
)
select he.emp_no, he.emp_name, avs.grade, he.sal * avs.incentive as bonus
from hr_employees he
inner join avg_score avs
on he.emp_no = avs.emp_no
order by he.emp_no asc;
