<div align="center">
  <h1>JWT Auth, JUnit Test, Docker</h1>
</div>

## Create a database for auth purpose on MySQL

```bash
CREATE DATABASE rbstb;
```

```bash
INSERT INTO rbstb.ROLES (ROLE_NAME, STATUS_NAME) VALUES ('ROLE_ADMIN','ACTIVE');
```
Then run the application.
Your Swagger-UI will be running on [localhost:5000/swagger-ui/][running-url]

## Token Generation

Register a user: `auth/register`

```bash
{
  "active": true,
  "email": "nazran91@gmail.com",
  "mobile": "01714516763",
  "password": "string",
  "roles": [
    {
      "id": 1,
      "name": "ROLE_ADMIN",
      "statusName": "ACTIVE"
    }
  ],
  "userName": "nazran91"
}
```
Login: `auth/login`

```bash
{
  "mobileOrEmail": "01714516763",
  "password": "string"
}
```
Will get JWT token at Response Header. Then authorize with `Bearer Token`

## Check Courier Price: `courier-price/check`

It's a dummy request to submit

```bash
{
  "destinationCountry": "BD",
  "destinationPostcode": 50000,
  "destinationState": "Bangladesh",
  "documentWeight": 0,
  "height": 10,
  "length": 10,
  "originCountry": "MY",
  "originPostcode": 50250,
  "originState": "Kuala Lumpur",
  "parcelWeight": 10,
  "selectedType": 1,
  "width": 10
}
```
Will get desired response.

## Docker Configuration: 

```bash
docker-compose build --no-cache app && docker-compose up
```

This command will run redis and mssql.

Please make sure you've added the database `rbtsb` either into the container or via any mssql client like dbeaver.

If you want to create that `rbtsb` database from the mssql container, you need to do the following:

```sh
docker exec -it <container_id|container_name> /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P <your_password>

# sqlcmd
> CREATE DATABASE rbtsb;
> GO
```

To see the `container_id`, open another terminal while running the spring boot app and hit this command and you will be able to see the mssql container's id listed

```sh
docker ps
```

## Author

- [Nazran Khondokar][author]

<!-- Definitions -->
[author]: https://www.linkedin.com/in/nazran91/
[running-url]: http://localhost:5000/swagger-ui/
