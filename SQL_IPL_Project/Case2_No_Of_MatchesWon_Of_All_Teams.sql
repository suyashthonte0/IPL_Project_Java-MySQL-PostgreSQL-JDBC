SELECT winner,COUNT(*)
FROM matchestable
WHERE NOT winner = ''
GROUP BY winner 
ORDER BY winner;