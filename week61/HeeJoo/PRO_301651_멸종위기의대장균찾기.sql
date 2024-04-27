-- 각 세대별 자식이 없는 개체의 수, 세대
-- 세대 오름차순 정렬
-- 모든 세대에 자식이 없는 개체 적어도 1개체 존재

-- 1. 세대 구하기 : generation = 이전 세대의 generation + 1
-- 2. 자식 수 구하기 -> pass
-- 3. 세대별 그룹핑 후 자식없는 개체 개수 구하기

with recursive tmp as( # 가상 테이블
    # 기준 테이블 : 1세대
    select
        id,
        parent_id,
        1 as gen
    from ecoli_data
    where parent_id is null
    union all
    # 반복 테이블
    select
        ed.id,
        ed.parent_id,
        (tmp.gen + 1) as gen # 부모의 세대 + 1
    from ecoli_data ed
    inner join tmp on ed.parent_id = tmp.id # 반복 종료 조건
)
select count(id) as count, gen as generation
from tmp
where id not in ( # 부모 ID로 등록되지 않은 ID = 자식이 0명
    select distinct parent_id
    from ecoli_data ed
    where parent_id is not null
)
group by gen
order by gen;
