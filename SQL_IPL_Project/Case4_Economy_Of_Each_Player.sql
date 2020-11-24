SELECT deliveriestable.bowler,sum((deliveriestable.wide_runs + deliveriestable.noball_runs + deliveriestable.batsman_runs)*6)/count(deliveriestable.bowler) AS Economy
FROM deliveriestable
INNER JOIN matchestable ON matchestable.id = deliveriestable.match_id
WHERE matchestable.season = 2016
GROUP BY deliveriestable.bowler
ORDER BY Economy ASC;