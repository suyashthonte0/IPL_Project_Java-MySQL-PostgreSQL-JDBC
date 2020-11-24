SELECT bowler, count(*) AS WICKETS
FROM deliveriestable
WHERE player_dismissed IS NOT NULL
GROUP BY bowler;