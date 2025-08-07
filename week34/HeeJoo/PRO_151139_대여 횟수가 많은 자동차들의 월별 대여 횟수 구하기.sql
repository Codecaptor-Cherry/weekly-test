-- 2022년 8월부터 10월까지 총 대여 횟수가 5회 이상인 차들에 대해서
-- 해당 기간동안의 월별 자동차 ID 별 총 대여 횟수 리스트 출력
-- 월을 기준으로 오름차순
-- 월이 같다면 자동차 ID 기준 내림차순 정렬
-- 특정 월의 총 대여 횟수가 0인 경우 제외
-- 1. 22년 8월 ~ 10월
-- 2. car_id로 묶기
-- 3. 0회 제외 출력
-- 실수 : 서브 쿼리에만 날짜 조건을 붙임... 메인 쿼리에도 날짜 조건이 있어야 함. 없으면 범위를 벗어나는 달도 출력됨
SELECT month(start_date) as month, car_id, count(*) as records
from car_rental_company_rental_history
where car_id in (
    select car_id
    from car_rental_company_rental_history
    where date_format(start_date, '%Y-%m') between'2022-08' and '2022-10'
    group by car_id
    having count(*) >= 5
)
and date_format(start_date, '%Y-%m') between'2022-08' and '2022-10'
group by car_id, month(start_date)
having count(*) > 0
order by month asc, car_id desc;
