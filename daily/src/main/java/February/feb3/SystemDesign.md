API Gateway:

An API gateway provides a single, unified API entry point across one or more 
internal APIs. They typically layer rate limiting and security as well.

A microservice-based architecture may have from 10 to 100 or more services. 
An API gateway can help provide a unified entry point for external consumers, 
independent of the number and composition of internal microservices.

The Benefits of an API Gateway For Microservices

Prevents exposing internal concerns to external clients.
Adds an additional layer of security to your microservices
Enables support for mixing communication protocols
Decreased microservice complexity
Microservice Mocking and Virtualization

The Drawbacks of an Microservice API Gateway

While there are many benefits to using an API microservice gateway, 
there are some downsides:

Your deployment architecture will require more orchestration and management 
with the addition of an API gateway
Configuration of the routing logic must be managed during deployment, to ensure 
proper routing from the external API to the proper microservice
Unless properly architected for high availability and scale, an API gateway can 
become a limiting factor and even a single point of failure.


Microservices Communication: Zuul API Gateway

What Is a Zuul Proxy?

The crux of the microservices pattern is to create an independent service which 
can be scaled and deployed independently. So in a complex business domain, more 
than 50-100 microservices is very common. Let's imagine a system where we have 
fifty microservices now we have to implement a UI which is kind of a dashboard, 
so it calls multiple services to fetch and show the important information in 
the UI.

From a UI developer perspective, to collect information from fifty underlying 
microservices, it has to call fifty REST APIs, as each microservice exposes a 
REST API for communication. So the client has to know the details of all REST 
API and URL patterns/ports to call them. Certainly, it does not sound like a 
good design. It is kind of a breach of encapsulation; the UI has to know all 
microservices server/port details to query the services.

Moreover, think about the common aspects of a web program, like CORS, 
authentication, security, and monitoring in terms of this design- each 
microservice team has to develop all these aspects into its own service, 
so the same code has been replicated over fifty microservices. Changes in 
the authentication requirements or CORS policy will ripple over all services. 
It is against the DRY principle, so this type of design is very error-prone 
and rigid. To make it robust, it has to be changed in such way so that we 
have only one entry point where all common aspects code is written and the 
client communicates with that common service. Here, the Zuul 
(The Gatekeeper/Demigod) concept pops up.

Zuul acts as an API gateway or Edge service. It receives all the requests coming 
from the UI and then delegates the requests to internal microservices. So, 
we have to create a brand new microservice which is Zuul-enabled, and this 
service sits on top of all other microservices. It acts as an Edge service or 
client-facing service. Its Service API should be exposed to the client/UI. 
The client calls this service as a proxy for an internal microservice, then 
this service delegates the request to the appropriate service.

The advantage of this type of design is that common aspects like CORS, 
authentication, and security can be put into a centralized service, so 
all common aspects will be applied on each request, and if any changes 
occur in the future, we just have to update the business logic of this 
Edge Service.

Also, we can implement any routing rules or any filter implementation. 
Say we want to append a special tag into the request header before it reaches 
the internal microservices, we can do it in the Edge service.

As the Edge service itself is a microservice, it can be independently scalable 
and deployable, so we can perform some load testing, also.

CHECK THE IMPLEMENTATION IN CODE SECTION OF THIS DAY.

https://github.com/Alfinandika/Netflix-zuul-api-gateway-example.git