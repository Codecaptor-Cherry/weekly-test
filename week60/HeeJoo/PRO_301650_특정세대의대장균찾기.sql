# 3세대 대장균 ID를 오름차순 정렬
select id
from ecoli_data
where parent_id in (
    # 2세대
    select id
    from ecoli_data
    where parent_id in (
        # 1세대
        select id
        from ecoli_data
        where parent_id is null
    )
)
order by id asc;
