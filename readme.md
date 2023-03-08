<div align="center">
  <h1>Digital Backend Assignment Documentation</h1>
</div>

## Create a database for auth purpose on MySQL/MsSQL

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

## Author

- [Nazran Khondokar][author]

<!-- Definitions -->
[author]: https://www.linkedin.com/in/nazran91/
[running-url]: http://localhost:5000/swagger-ui/
