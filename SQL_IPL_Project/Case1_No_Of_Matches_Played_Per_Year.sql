SELECT season,count(*)
FROM matchestable
GROUP BY season
ORDER BY season;