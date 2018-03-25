# TheURLShortner

HTTP service for shortening URLs 

## Getting Started

To start using the application, either:
* clone the repository and run as Java Application from your favorite IDE
* download and execute the executable .jar

### API Documentation

**BASE URL** : localhost:8080/

`POST` /account

**Auth required** : NO

## Request

Provide name of Account to be created.

```json
{
    "AccountId": "[unicode 20 chars max]" [required]
}
```

**Data example** All fields must be sent.

```json
{
    "AccountId": "Somename"
}
```
## Success Response

**Condition** : If everything is OK and an Account didn't exist for this User.

**Code** : `201 CREATED`

**Content example**

```json
{
    "success": true,
    "description": "Your account is opened!",
    "password": "El23YsrB"
}
```

## Error Responses

**Condition** : If Account already exists for User.

**Code** : `400 Bad Request`
```json
{
    "success": false,
    "description": "Account with id xxxxx already exists!",
    "password": null`
}
```
`POST` /register

**Auth required** : YES (Basic Auth)

**Data constraints**

Provide URL that you want to short

```json
{
    "url": "[unicode 255 chars max]" [required]
    "redirectType": 301 or 302
}
```
**Data example** 

```json
{
    "url": ["https://www.example.com"], [required]
    "redirectType": 302 [optional, 301/302, default is 302] 
}
```
## Success Response
**Code** : `200 OK`

**Content example**

```json
{
    "shortUrl": "localhost:8080/dHn0Ww"
}
```

## Error Responses

**Condition** : If the same URL is already shortened

**Code** : `400 BAD REQUEST`
```json
{
    "timestamp": "",
    "status": 400,
    "error": "Bad Request",
    "message": "You already shortened URL: http://www.something.com",
    "path": "/register"
}
```
**Code** : `401 UNAUTHORIZED`

`GET` /statistic/{AccountID}

**Auth required** : YES (Basic Auth)

**Usage**

Used to get number of redirects per shorted URL for your account

## Success Response
**Code** : `200 OK`

**Content example**

```json
{
    "http://www.example.com": 13
}
```

## Error Responses

**Condition** : If trying to get statistics for not your account

**Code** : `403 FORBIDEN`
```json
{
    "timestamp": "",
    "status": 403,
    "error": "Forbidden",
    "message": "You are not allowed to see statistic for account flasa1",
    "path": "/statistic/{AccountId}"
}
```
