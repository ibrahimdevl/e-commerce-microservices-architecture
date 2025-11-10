# e-commerce springboot microservices 

Important note: This project is still under development and enhancement, so stay tuned.

## Introduction

- This project is a development of a small set of Microservices projects based on Spring Boot, reactive programming, event-driven, Microservices design patterns, and coding best practices.
- This project uses cutting edge technologies like Docker,  Java SE 17, and PostgreSQL database,kafka as a message broker and Stripe for payment, all components developed with TDD in mind, covering integration & performance testing, and many more.
- This project is going to be developed as stages, and will be  documented under the project e-Commerce- springboot microservices  README file .

## Getting Started

System components Structure
First, let's demonstrate the structure of the system in order to understand its components:

```bash
  ecommerce-microservice-backend-app [shopify] --> Parent folder.

    |- api-gateway --> API Gateway server
    |- service-discovery --> Service Registery server
    |- cloud-config --> Centralized Configuration server
    |- user-service --> Manage app users (customers & admins) as well as their credentials
    |- product-service --> Manage app products and their respective categories
    |- order-service --> Manage app orders 
    |- payment-service --> Manage app order payments

|- docker-compose.yaml --> contains all services
```
## UML class diagram
![image](https://github.com/fedi-guizeni/e-commerce-microservices-architecture/assets/78599201/94094512-9c85-42e2-8833-f5d4b8e57a65)

## Saga Choreography Pattern
<h2>Overview</h2>
The Saga Choreography pattern is a distributed architectural pattern used for managing long-lived transactions in a microservices architecture. Instead of relying on a central coordinator, as in the Saga Orchestration pattern, each microservice involved in the transaction broadcasts events to communicate and coordinate the overall process.

<h2>Architecture</h2>

In this example project, the Saga Choreography pattern is implemented using a set of microservices, each responsible for specific business capabilities. The microservices communicate by emitting events and reacting to events emitted by other services. This decentralized approach allows for better scalability and fault tolerance.

### Components
- **Order Service:** Manages order creation and processing.
- **Payment Service:** Handles payment processing.
- **product Service:** Manages inventory and order fulfillment.
  
### Communication
Microservices communicate by emitting events such as `Order_created`, `pending_Payment`, and `Payment_Completed`. Each service reacts to relevant events to perform its part in the overall transaction.


## The End
In the end, I hope you enjoyed the application and find it useful,

<h2>NB: this project is still under development</h2>

If you would like to enhance, please:
- Open PRs,
- Give feedback,
- Add new suggestions, and
- Finally, give it a 🌟.



