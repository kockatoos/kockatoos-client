# Kockatoos Client

🚀 **A Powerful Plugin for Spring-Based Applications to Extract and Structure Resource Information**

## Overview
Kockatoos Client is a lightweight yet powerful tool designed to seamlessly integrate with **Spring-based applications**. It automatically scans and extracts metadata about various resources used within the application, including:

- **JMS Queues & Topics**
- **HTTP Endpoints (REST & SOAP Services)**
- **Database Connections & Queries**
- **Inbound & Outbound Integrations**

The extracted data is then **structured into a JSON format**, enabling easy consumption, analysis, and integration into monitoring, documentation, or configuration management systems.

## Features
✅ **Plug & Play Integration** – Works with any Spring Boot application with minimal setup.  
✅ **Automatic Resource Detection** – Scans and collects metadata about configured endpoints.  
✅ **JSON-Based Output** – Standardized format for interoperability with other tools.  
✅ **Support for Multiple Protocols** – REST, SOAP, JMS, Databases, and more.  
✅ **Customizable & Extensible** – Override default implementations to fit your needs.

## How It Works
Kockatoos Client functions as a **Spring Boot plugin**, registering itself within the application context. Once integrated, it performs the following steps:

1. **Scans the application context** to detect configured resources.
2. **Extracts metadata** related to HTTP endpoints, JMS, SOAP, and database configurations.
3. **Generates a structured JSON output**, making it easy to consume or export.

## Installation & Setup
To integrate Kockatoos Client into your Spring Boot project, follow these steps:

### Step 1: Add Dependency
```xml
<dependency>
    <groupId>com.kockatoos</groupId>
    <artifactId>kockatoos-client</artifactId>
    <version>X.Y.Z</version>
</dependency>
```
### Step 2: Enable the Plugin with **@EnableKockatoos**

```java
@EnableKockatoos
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}

```

## Default Properties for Spring Boot

```properties
kockatoos.rest.endpoint.expose=false
kockatoos.cloud.server.expose=false
kockatoos.cloud.server.url=https://xyz.com
kockatoos.application.name=spring.application.name 
kockatoos.application.server.port=server.port
kockatoos.application.context-path=server.servlet.context-path
```

## Add Properties for Spring Boot

```properties
kockatoos.cloud.server.secret -> create cloud account to get secret.

```

## Custom Implementation (Optional)
```java
@Component
public class CustomPropertyProvider extends KockatoosAbstractPropertyProvider {

    public CustomPropertyProvider(Environment environment) {
        super(environment);
    }

    @Override
    public Map<String, String> loadCustomProperties() {
        Map<String, String> map = new HashMap<>();
        map.put("kockatoos.application.host.environment", "production");
        map.put("kockatoos.application.server.port", "9090");
        return map;
    }
}
```

### step 4: Expose rest endpoint "/kockatoos" (Optional).
* Enable rest endpoint: kockatoos.rest.endpoint.expose = true
* By default endpoint is not exposed.

### Step 3: Sample output 
```json
{
  "application": "My Spring Boot App",
  "environment": "production",
  "httpEndpoints": [
    { "path": "/api/users", "method": "GET", "auth": "OAuth2" }
  ],
  "jmsQueues": [
    { "name": "orderQueue", "type": "INBOUND" }
  ],
  "databases": [
    { "url": "jdbc:postgresql://localhost:5432/mydb", "user": "admin" }
  ]
}

```





