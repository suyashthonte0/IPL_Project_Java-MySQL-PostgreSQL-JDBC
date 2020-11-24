SELECT deliveriestable.bowling_team,sum(deliveriestable.extra_runs) AS Total_Extra_runs 
FROM deliveriestable 
INNER JOIN matchestable ON deliveriestable.match_id = matchestable.id
WHERE matchestable.season=2016
GROUP BY deliveriestable.bowling_team;
