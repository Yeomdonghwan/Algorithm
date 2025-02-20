-- 코드를 입력하세요
SELECT ANIMAL_TYPE, COUNT(*) as count
from ANIMAL_INS
group by ANIMAL_TYPE
having ANIMAL_TYPE IN ('Dog', 'Cat')
ORDER BY ANIMAL_TYPE 