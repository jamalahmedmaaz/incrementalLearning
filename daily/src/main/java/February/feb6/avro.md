Avro is an open source project that provides data serialization and data 
exchange services for Apache Hadoop. These services can be used together or 
independently. Avro facilitates the exchange of big data between programs 
written in any language. With the serialization service, programs can 
efficiently serialize data into files or into messages. The data storage is 
compact and efficient. Avro stores both the data definition and the data 
together in one message or file.

Avro stores the data definition in JSON format making it easy to read and 
interpret; the data itself is stored in binary format making it compact and 
efficient. Avro files include markers that can be used to split large data 
sets into subsets suitable for Apache MapReduce processing. Some data exchange 
services use a code generator to interpret the data definition and produce 
code to access the data. Avro doesn't require this step, making it ideal for 
scripting languages.

A key feature of Avro is robust support for data schemas that change over 
time — often called schema evolution. Avro handles schema changes like missing 
fields, added fields and changed fields; as a result, old programs can read new 
data and new programs can read old data. Avro includes APIs for Java, Python, 
Ruby, C, C++ and more. Data stored using Avro can be passed from programs 
written in different languages, even from a compiled language like C to a 
scripting language like Apache Pig.