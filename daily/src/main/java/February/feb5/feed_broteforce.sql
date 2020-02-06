(SELECT FeedItemId
 FROM FeedItem
 WHERE UserId IN
       (SELECT EntityOrFriendId
        FROM UserFollow
        WHERE UserId = "curreUserId"
          AND type = "(0)user"))
UNION
(SELECT FeedItemId
 FROM FeedItem
 WHERE UserId IN (SELECT EntityOrFriendId
                  FROM UserFollow
                  WHERE UserId = "curreUserId"
                    AND Type = "(1)Entity"))
ORDER BY CreateDate DESC
LIMIT 100;


SELECT feeditemid
FROM feeditem fe
       JOIN userfollow uf ON uf.entityorfriendid = fe.userid
  AND uf.userid = '3' AND uf.type IN ('1', '2')
ORDER BY fe.createddate DESC
LIMIT 100;