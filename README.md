
# Docker

 Create the docker container before running the application for database connection.

 ITGEcommerce> docker-compose up -d     

# React

To run the react application

ITGEcommerce\WebPages\itgecommerce> npm start

# Email

In application.yml the gmail address and password is hidden with system environment variables.

# POSTMAN

To test the Spring Security works:

1 - In postman make post request with:

http://localhost:9092/auth/loginWithSecurity

and pass this request body as JSON raw format

{
"email": "admin@example.com",
"password": "admin123"
}

After successfull login this method returns a JWT token.

2 - After getting the token create a new post request with:

http://localhost:9092/product/createNewProduct

and pass this request body as JSON raw format

{
"title": "new product",
"price": 1.5,
"brand": "new"
}

and from Authorization select Bearer token and put the token obtained from the loginWithSecurity method.
This should return true and since we put the admin token. Only admin is authorized to execute this method.
If this token would belong to a regular user, it wouldn't let us and throw http status 403 Forbidden.
