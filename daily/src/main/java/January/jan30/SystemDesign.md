System Design : 

-------------------------------------------------------------------------------
|Function | NonFun. | Assumptions | Reliability     |CAP| Technology|Security  |
|Req.     | Req.    |             | Scalability     |   |           |vigilance |
|         |         |             | Maintainability |   |           |Monitoring|
-------------------------------------------------------------------------------
|         |         |             |                 |   |           |          |
|         |         |             |                 |   |           |          |
|         |         |             |                 |   |           |          |
|         |         |             |                 |   |           |          |
|         |         |             |                 |   |           |          |
|         |         |             |                 |   |           |          |
|         |         |             |                 |   |           |          |
|         |         |             |                 |   |           |          |
|         |         |             |                 |   |           |          |
|         |         |             |                 |   |           |          |
|         |         |             |                 |   |           |          |
-------------------------------------------------------------------------------


Measurements/Estimates:

1. Traffic Estimates:
2. Storage Estimates:
3. Bandwidth Estimates:
4. Memory Estimates:



How to save a website from bots?

First is identify is there a bot attack happening. What are the parameters on
 which you can identify this?
 
 1. Increase in page load time.
 2. Higher Bounce rate
 3. Lower average time on page.
 
 
 Not all bots should be blocked or stopped (because there can be good bots 
 as-well).
 
 Good Bots: Bots do things such as index your website in search engines, 
 monitor your websites health and fetch RSS feeds.
 
 Bad Bots: Do things such as scrape your links and content, post spam 
 messages, or attempt to disrupt your site.
 
 Steps to identify bot attach:
 
 1. The first way to check your website for bots is to check your 
 Google Analytics stats for any inconsistencies. By paying attention to the
number of page views, average session duration and referrers, you can quickly
work out if bots are visiting you, and how frequently.
 
 2. One of the most obvious things you’ll notice when being visited by bots is 
 a sharp increase in the number of page views. If a robot is crawling your 
 entire website, then they’ll load up countless pages at the same time. 
 If your average page visit per user is 3 and suddenly you see someone visit
all 50 pages of your site, then they’re probably a bot.

 3. Other metrics you should be checking on your site should be the average 
 page duration and bounce rate. If you notice your average page duration
decreasing and your bounce rate increasing, then this is a sign you’re 
being visited by bots.
 
 4. Since bots are incredibly quick, they usually only take a few seconds to 
 crawl your site and get the information they need. Compare this to a typical 
 user, and the bot’s page duration is likely to be a lot lower. Once the bot 
 has finished crawling the page, the bot will leave and move onto the next 
 site. This will have a big effect on your bounce rate. By leaving without 
 visiting another page, Google will class the bot visit as a bounce, even 
 though they were never a real user! Over time, this can wear down you Google
metrics. By paying attention to a change in these metrics this can give you
a heads up that your website is experiencing significant bot traffic.

 5. Another way to detect bot traffic on your website is to be aware of the
speed of your site. If you’re experiencing a massive influx of bots then
you’ll probably notice your site loading slower. One bot might not make
much difference, but having several bots at the same time can start to 
strain your server. There’s actually a chance that malicious bots are 
attempting to overrun your server and take it offline! Also known as a
DDOS attack, these attacks can have devastating effects on businesses.
Especially when their website is the primary source for doing business
and receiving orders.


How to Stop Bots Visiting Your Site








DDOS attach - Distribute Denial of Service Attach:

It can be explained as highway traffic which have clogging/blocked the highway 
and stops actual normal traffic to reach to its destination.

A distributed denial-of-service (DDoS) attack is a malicious attempt to disrupt
 normal traffic of a targeted server, service or network by overwhelming the
target or its surrounding infrastructure with a flood of Internet traffic.
DDoS attacks achieve effectiveness by utilizing multiple compromised 
computer systems as sources of attack traffic. Exploited machines can
include computers and other networked resources such as IoT devices.
 From a high level, a DDoS attack is like a traffic jam clogging up
with highway, preventing regular traffic from arriving at its desired
destination.

To understand what how to save ourself from ddos, we need to think like an 
hacker and then build a plan to attach (ddos).

A DDoS attack requires an attacker to gain control of a network of online 
machines in order to carry out an attack. Computers and other machines 
(such as IoT devices) are infected with malware, turning each one into a bot 
(or zombie). The attacker then has remote control over the group of bots, 
which is called a botnet.

Doing this the targeted system will not be able to differentiate between 
normal traffic and attacking traffic (because the IP address are same)

1. (HTTP Flood) Basic Type of attach: From different machine make index.html 
calls, example M1,M2,M3 are continuasly calling application.com/index.html.

2. (Protocol Attack): State exhaustion attacks, cause service disruption by 
consuming all the available state table capacity of web application servers 
or intermediate resource like firewalls and load balancer. This attacks 
exploits the weakness of layer 3 and layer 4

3. Volumetric Attack: Congestion Attack:
This category of attacks attempts to create congestion by consuming all 
available bandwidth between the target and the larger Internet. 
Large amounts of data are sent to a target by using a form of amplification 
or another means of creating massive traffic, such as requests from a botnet.


A DNS Amplification is like if someone were to call a restaurant and 
say “I’ll have one of everything, please call me back and tell me my 
whole order,” where the callback phone number they give is the target’s number. 
With very little effort, a long response is generated.








