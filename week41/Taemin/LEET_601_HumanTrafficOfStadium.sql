SELECT      DISTINCT t1.*
FROM        Stadium t1, Stadium t2, Stadium t3
WHERE       t1.people >= 100 AND t2.people >= 100 AND t3.people >= 100
            AND ((t2.id - t1.id = 1 AND t1.id - t3.id = 1 AND t2.id - t3.id = 2) OR
                 (t3.id - t2.id = 1 AND t2.id - t1.id = 1 AND t3.id - t1.id = 2) OR
                 (t1.id - t3.id = 1 AND t3.id - t2.id = 1 AND t1.id - t2.id = 2))
ORDER BY    t1.visit_date ASC;
