# beer-service

Demo project for Spring Boot Beer Lovers.

## Build and Use Project

```
./mvnw clean package
java -jar target/beer-service-0.0.1-SNAPSHOT.jar
```

## Rest Endpoints

* You can use 'httpie' to test the REST endpoints, e.g. https://httpie.org/
* It's easy to install `httpie` client on Mac, e.g.

```
brew install httpie
```

* Test the REST endpoints, e.g.

```
% http localhost:8080

HTTP/1.1 200 
Content-Type: application/hal+json;charset=UTF-8
Date: Mon, 12 Aug 2019 12:00:00 GMT
Transfer-Encoding: chunked

{
    "_links": {
        "beers": {
            "href": "http://localhost:8080/beers{?page,size,sort}",
            "templated": true
        },
        "profile": {
            "href": "http://localhost:8080/profile"
        }
    }
}
```

```
% http localhost:8080/beers

HTTP/1.1 200 
Content-Type: application/hal+json;charset=UTF-8
Date: Mon, 12 Aug 2019 12:00:00 GMT
Transfer-Encoding: chunked

{
    "_embedded": {
        "beers": []
    },
    "_links": {
        "profile": {
            "href": "http://localhost:8080/profile/beers"
        },
        "self": {
            "href": "http://localhost:8080/beers{?page,size,sort}",
            "templated": true
        }
    },
    "page": {
        "number": 0,
        "size": 20,
        "totalElements": 0,
        "totalPages": 0
    }
}
```

```
% http post localhost:8080/beers name="New Beer" hop="HopMax" malt="Super Malt" yeast="Y2K" style="Ale" 

HTTP/1.1 201 
Content-Type: application/json;charset=UTF-8
Date: Mon, 12 Aug 2019 12:00:00 GMT
Location: http://localhost:8080/beers/1
Transfer-Encoding: chunked

{
    "_links": {
        "beer": {
            "href": "http://localhost:8080/beers/1"
        },
        "self": {
            "href": "http://localhost:8080/beers/1"
        }
    },
    "hop": "HopMax",
    "malt": "Super Malt",
    "name": "New Beer",
    "style": "Ale",
    "yeast": "Y2K"
}
```

