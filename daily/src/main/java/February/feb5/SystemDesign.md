###### 3V = Volume, Velocity and Variety
             Big     Fast         Different

Think of the 3 V's of data: volume, velocity, and variety. For a while big data 
emphasized data volume; now fast data applications mean velocity and variety 
are key. Two tendencies have emerged from this evolution: first, the variety 
and velocity of data that enterprise needs for decision making continues to grow. 
This data includes not only transactional information, but also business data, 
IoT metrics, operational information, and application logs. Second, modern 
enterprise needs to make those decisions in real time, based on all that 
collected data. This need is best clarified by looking at how modern shopping 
websites work.

Websites are about what we can offer to a particular client at a precise moment. 
We need to know the "heat" zones of the page — the most clicked items — and why 
there are cold zones. If a person is viewing section A, the system must offer 
section B, because data shows many customers moved from section A to section B. 
Again, we have two challenges: collect all this data in an online way, clean it, 
process it, and analyze it in an online way (there is no more overnight 
processing). Second, based on this information we need to make changes to the 
web page immediately, so the analysis must be accurate and tied dynamically to 
the customer for a precise moment. It is indeed a real-time world.

###### Beyond Hadoop: designing architectures for fast data

Big data used to mean Hadoop and NoSQL databases. Hadoop was born in the 
"batch mode" and "off-line" processing era, when data was captured, stored and 
processed periodically with batch jobs. In those years, search engines worked 
by having data gathered by web crawlers and then processed overnight to offer 
updated results the next day. Big data was focused on data capture and off-line 
batch mode operation. As our earlier website example shows, modern "fast data" 
reduces the time between data arriving and data value extraction. Real-time 
event processing is the opposite of batch data. In real-time fast data 
architectures individual events are processed as they arrive, and we talk about 
processing times of milliseconds, even microseconds.

Building fast data architectures that can do this kind of millisecond processing 
means using systems and approaches that deliver timely and cost-efficient data 
processing focused on developer productivity. Any successful a fast data 
architecture must satisfy these high-level requirements:

1. Performance and reliable data acquisition or ingestion
2. Flexible storage and querying
3. Sophisticated analysis tools

###### 3 R's = Reactive, Resilient, Responsive.

It’s also important to mention that architecture components must comply with the 
R's: reactive (scaling up and down based on demand), resilient (against 
failures in all the distributed systems), and responsive (even if the failures 
limit the ability to deliver services). Related to the R’s is the fact that fast 
data processing has also changed data center operations, so data center management 
has become a key tool in fast data architecture that meets the demands of 
real-time decision making.

Modern fast data systems are composed of four transformation stages that provide 
the following data capabilities:

1. Acquisition
2. Storage
3. Processing and analysis
4. Presentation and visualization

Let’s look at each of these stages and their tools in turn.

###### Data Acquisition: Pipeline for performance.

##### Parsing -> Validation -> Cleansing -> De-duping -> Transformation.

Key Points:

1. Data transfer should be asynchronous and avoid back pressure.
When a synchronous system becomes asynchronous we have to consider two factors: 
the tokens subsystem and the transaction subsystem. This sounds easy, but there 
are two ways to implement an asynchronous system: using a file-feed transfer or 
using a Message-Oriented-Middleware (MOM), an infrastructure that supports 
messaging in distributed systems. Imagine data is generated faster than it is 
consumed; in distributed systems design this is called back pressure. One main 
objective of any MOM system and distributed queues is to deal with the back 
pressure.

2. Parsing can be expensive so parallelize when possible
Using the right parser can be a crucial factor because conversion between data 
formats (binary, XML, text) should be optimal for the APIs implemented by the 
MOM. The most expensive step of data acquisition is data transformation — the 
step where time and resources are consumed, so If you can use a parallel process 
to transform your data before processing, it is most valuable here. Another 
time saver is to filter invalid data as early as possible. Processing must 
work with valid data sets, so clean and remove duplicates before transforming 
data.

###### Technologies

For this stage you should consider streaming APIs and messaging solutions like:

Apache Kafka - open-source stream processing platform
Akka Streams - open-source stream processing based on Akka
Amazon Kinesis - Amazon data stream processing solution
ActiveMQ - open-source message broker with a JMS client in Java
RabbitMQ - open-source message broker with a JMS client in Erlang
JBoss AMQ - lightweight MOM developed by JBoss
Oracle Tuxedo - middleware message platform by Oracle
Sonic MQ - messaging system platform by Sonic

For handling many of these key principles of data acquisition, the winner 
is Apache Kafka because it’s open source, focused on high-throughput, 
low-latency, and handles real-time data feeds.

###### Data storage: flexible experimentation leads to solutions

There are a lot of points of view for designing this layer, but all should consider two perspectives: logical (i.e. the model) and physical data storage. The key focus for this stage is "experimentation” and flexibility.

###### 1. Every problem has its solution but don’t reinvent the wheel.
   
The term database is huge, always keep in mind that different solutions have 
different capabilities: some are better with reading, some for inserting, and 
some at updating. Another important consideration — not all data is relational 
because not all data is text. Remember efficiency at every step makes all the 
processes faster and improves resource consumption for things like network 
transfer and disk space.

###### 2. I configure then I am, and when configured, reconfigure

There is no more default, "out-of-the-box" configuration. The same solution can have two distinct configurations making the same tool behave totally orthogonally. To be competitive, a storage system must have the ability to modify two key settings: replication level and consistency level. The art of the solution is trying new configurations till you get the desired behavior.

###### 3. Does relational solve your problem and is normalization still an option?
         
Think big, out of the box. In the RDBM systems you have to adapt your problem to the relational model, so you have to find your entities, relationships, keys and foreign keys. In the fast data era, data systems are modeled based on use cases solved. Another question, that seemed absurd in the past but now makes sense, is deciding between normalizing data and performance. The solution you choose has a direct impact on performance.

###### Technologies

For this stage consider distributed database storage solutions like:

Apache Cassandra - distributed NoSQL DBMS
Couchbase - NoSQL document-oriented database
Amazon DynamoDB - fully managed proprietary NoSQL database
Apache Hive - data warehouse built on Apache Hadoop
Redis - distributed in-memory key-value store
Riak - distributed NoSQL key-value data store
Neo4J - graph database management system
MariaDB - with Galera form a replication cluster based on MySQL
MongoDB - cross-platform document-oriented database
MemSQL - distributed in-memory SQL RDBMS

For handling many of key principles of data storage just explained, the most balanced option is Apache Cassandra. It is open source, distributed, NoSQL, and designed to handle large data across many commodity servers with no single point of failure.

