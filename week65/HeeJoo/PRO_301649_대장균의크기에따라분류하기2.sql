-- 크기를 내림차순으로 정렬했을 때,
-- 상위 0 ~ 25% : CRITICAL
-- 26 ~ 50% : HIGH
-- 51 ~ 75% : MEDIUM
-- 76 ~ 100% : LOW
-- 개체 ID와 분류된 이름 출력
-- 개체의 ID에 대해 오름차순 정렬

with tmp as (
    select id, (
        case 
            when percent_rank() over (order by size_of_colony desc) <= 0.25 then 'CRITICAL'
            when percent_rank() over (order by size_of_colony desc) <= 0.5 then 'HIGH'
            when percent_rank() over (order by size_of_colony desc) <= 0.75 then 'MEDIUM'
            else 'LOW'
            end) as COLONY_NAME
    from ecoli_data
    order by size_of_colony
)
select *
from tmp
order by id asc;
