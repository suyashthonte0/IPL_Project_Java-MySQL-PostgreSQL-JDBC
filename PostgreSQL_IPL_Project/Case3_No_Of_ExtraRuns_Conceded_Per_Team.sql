SELECT deliveries.bowling_team,sum(deliveries.extra_runs) AS Total_Extra_runs 
FROM deliveries 
INNER JOIN matches ON deliveries.match_id = matches.id
WHERE matches.season=2016
GROUP BY deliveries.bowling_team;