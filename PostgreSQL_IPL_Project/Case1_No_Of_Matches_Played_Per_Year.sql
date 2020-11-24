SELECT season,count(*)
FROM matches
GROUP BY season
ORDER BY season;