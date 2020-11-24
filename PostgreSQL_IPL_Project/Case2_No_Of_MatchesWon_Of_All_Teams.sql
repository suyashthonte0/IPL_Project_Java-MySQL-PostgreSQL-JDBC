SELECT winner,COUNT(*)
FROM matches
WHERE NOT winner = ''
GROUP BY winner 
ORDER BY winner;