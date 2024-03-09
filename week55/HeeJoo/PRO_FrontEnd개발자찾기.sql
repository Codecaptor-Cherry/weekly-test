-- ID, 이메일, 이름, 성
-- ID 오름차순
-- skill_code는 code의 합
-- conv(숫자, 원본 진법, 변경 진법)

with front as (
    select *
    from skillcodes
    where category = 'Front End'
)    
select distinct (dev.id), dev.email, dev.first_name, dev.last_name
from developers as dev
inner join front
on dev.skill_code & front.code -- bit변환 안하고 해야됨 !
order by dev.id;
