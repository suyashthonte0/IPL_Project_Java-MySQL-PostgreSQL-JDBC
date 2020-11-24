SELECT deliveries.bowler,sum((deliveries.wide_runs + deliveries.noball_runs + deliveries.batsman_runs)*6)/count(deliveries.bowler) AS Economy
FROM deliveries
INNER JOIN matches ON matches.id = deliveries.match_id
WHERE matches.season = 2016
GROUP BY deliveries.bowler
ORDER BY Economy ASC;