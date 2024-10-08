WITH FOOD AS (
    SELECT FOOD_TYPE, MAX(FAVORITES) FAVORITES
    FROM REST_INFO
    GROUP BY FOOD_TYPE)
SELECT R.FOOD_TYPE, REST_ID, REST_NAME, R.FAVORITES
FROM REST_INFO R JOIN FOOD F
    ON R.FOOD_TYPE = F.FOOD_TYPE AND R.FAVORITES = F.FAVORITES
ORDER BY R.FOOD_TYPE DESC