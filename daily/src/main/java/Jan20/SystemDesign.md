System Design - Interview

1. The amount of data we are getting, the complexity of data and the speed at which it is changing.
2. Send a message to another process, to be handled asyc. (stream processing)
3. Periodically crunch a large amount of accumulated data (batch processing)
4. Remember the result of an expensive operation, or same data getting read multiple times (cache)
5. Store data such that current or other applications can find it later (database)
6. Lets try to achieve reliable, scalable, maintainable system. By taking different decision on the design for the current problem.
7. How do we ensure that the data remain correct and complete, event when things go wrong interally?
8. How we will provide consistently good performance for clients even when parts of tthe system are degraded?
9. How do we scale to handle an increase on load? black friday.
10. Lets draw the diagram of CAP vs RSM (reliable scalable maintainable)

11. Reliable - Continue to work correctly even when things go wrong.
(1. The application perform the expected operation the user expects;
2. The application can tolerate the user making mistakes or using the applicaiton in unexpected ways;
3. The application performance is good enough for the required use case,
under the expected load and data volume;
4. The system prevents any unauthorized access and abuse;
5. Tolerating faults (meaning resilient)).


12. Scalability - Even a system is working today doesnt mean it will will work reliably in future.
one common reason for degradation is increased load (ex: system grew from 10,000 to 100,000 concurrent
users, or it is processing much larger volume of data than it did before).

Scalability - Ability to cope when load increases. But it is not just connect to a single thing,
(example, it is WRONG to say i scaled one rest api in a micro-service and all
 other api's are not scaled).
Correct questions to ask:
1. If the system grows in a particular way what are our options to cope with
the growth?
2. How can we add computing resources to handle the additional load?

How can we describe LOAD? First important thing is to describe CLEARLY the current load on the system.
then we can discuss growth questions (ex: what happens if our load doubles?).
LOAD PARAMETERS - to the rescue.

This completely depends on what is happening more on the system.

1. Request per second to the application.
2. Read to write ration on the DB.
3. Concurrent active users in a chat room.
4. Hit rate on cache.
5. OR what matters for you as a system. (It is possible the bottleneck is dominated by a small no of extreme cases.

Tweeter problem on scaling is FAN-OUT.

FAN-OUT = Output of something becoming input of other thing.
In twitter case, a single tweet will fanout to millions of users HOME Time Line.

Example: Barack Obama tweets and there are 10 million followers of him, 10 million user times will get updated
with the tweet.

(query db directly) or (use usertime to solve the problem) or (combination of 1st and second, based on the user).

Once load is described - we can investigate what happens when load increases 
TWO ways.

1. When you increase a load parameter and keep the system resources (CPU, 
MEM, N/W) unchanged, how is the performance of your system affected?

2. When you increase a load parameter, how much do you need to increase the 
resource if you want to keep performance unchanged?

