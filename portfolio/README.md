# CountriesApi

---
### Project Description
1. You can get neighbour countries of the country via REST API call.
2. You can get a list of neighbours for each country, if requesting data for more than one country.
3. Country data are fetched from external API (https://date.nager.at/)

---
### Configuration
1. Clone this project: `git clone git@github.com:yapijs/CountriesApi.git`
2. Run the project in console: `./mvnw spring-boot:run`
---
### Requirements:
1. Have JRE/JDK 17 installed.
2. If you would like to test API calls, use some tool like Postman. 

---
### Endpoints
1. `http://localhost:8080/api/country/{countryCode}/neighbours` - where countryCode is String value of country like `US` or `AU`.
    * for incorrect code caller will get http error: `404 NOT FOUND`
2. `http://localhost:8080/api/countries/neighbours` - where in the request body indicate array of country codes like `["US", "AU"]`, for which api will return neighbouring countries in synchronous way.
    * if some incorrect codes are added along with correct one, only correct ones will be processed.
    * if none of codes are correct, there will be http error: `400 BAD REQUEST`

---
### Tests
1. There are unit tests to verify correctness of the app.
2. Run tests in console: `./mvnw test`