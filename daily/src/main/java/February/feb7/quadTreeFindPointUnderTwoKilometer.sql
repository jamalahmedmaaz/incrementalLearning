SELECT *
FROM (
       SELECT *,
              (((acos(sin((@latitude * pi() / 180)) *
                      sin((Latitude * pi() / 180)) +
                      cos((@latitude * pi() / 180)) *
                      cos((Latitude * pi() / 180)) *
                      cos(((@longitude - Longitude) * pi() / 180)))) * 180 /
                pi()) * 60 * 1.1515 * 1.609344) AS distance
       FROM Distances) t
WHERE distance <= @distance

--Just provide the distance 1 kilometer or 2 kilometer