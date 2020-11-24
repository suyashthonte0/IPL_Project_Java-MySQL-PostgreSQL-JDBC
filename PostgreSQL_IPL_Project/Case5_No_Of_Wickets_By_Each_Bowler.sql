SELECT bowler, count(*) AS WICKETS
FROM deliveries
WHERE player_dismissed IS NOT NULL
GROUP BY bowler;