## Client Code Features

1. @EnableKockatoos annotation loads the X bean  in Application Context.
2. Consider JMS + HTTP + Database endpoints for now only.
3. should be able to customize model object as application parameters may change application to application.
4. Should be able to make a call to default server to send data.
5. If you want to make call to self-hosted server/ internal server then configuration should be their to point to custom deployed server.
6. Application security code to make call to server.
7. Give feature to load assign values to model from property template file using Environment Interface.
8. Add functionality to add custom classes or interfaces for endpoints.


## Possible Application Parameters:

1. Application Name
2. Application Context Path
3. Application Environment
4. JAVA_VERSION
5. Server/ Hostname


## Possible Application Resources

1. JMS Endpoints : Inbound & Outbound
2. REST HTTP Endpoints: Inbound & Outbound
3. SOAP HTTP Endpoints: Inbound & Outbound
3. Database



