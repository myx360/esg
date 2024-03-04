# esg_interview

## Task 1

To build and run the application:

```bash
docker compose up -d --build
```

To parse the esg_data.csv file, run the parse_csv.sh shell script from the root directory of the project. The script will parse the csv file and send the data from each row to the POST /api/customer/<ref> endpoint.

```bash
./parse_csv.sh
```

Available endpoints: 

POST: /api/customer/<ref> - Create a customer
```bash
curl -X POST --location 'http://localhost:8080/api/customer/cus_12wd21f' \
-H 'Content-Type: application/json' \
-d '{
  "name": "Christie Third",
  "address1": "3 Tree Lane",
  "address2": "",
  "town": "Threebury",
  "county": "Essex",
  "country": "United Kingdom",
  "postcode": "E34 4JH"
}'
```


GET: /api/customer/<ref> - Get a customer by reference (will 400 on ref that already exists)
```bash
curl --location 'http://localhost:8080/api/customer/cus_12wd11f' \
--header 'Content-Type: application/json'
```

## Task 2

```bash
java StringCalculator.java
```