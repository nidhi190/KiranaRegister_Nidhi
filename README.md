# Kirana Register Backend Service

## Author
Nidhi Rani (nidhi19088@gmail.com)

## Overview

The Kirana Register project is designed to empower Kirana stores in managing their transaction registers. It provides a service for the daily tracking of all credit and debit transactions, offering features like recording transactions, fetching transactions with optional date-wise filtering, and supporting pagination. Also It supports currency conversion.

## Technology Stack

- **Spring Boot**: The project is built using the Spring Boot framework.
- **H2 In-Memory Database**: An in-memory database is utilized for temporary storage.
- **External API for Currency Conversion**: The [fxratesapi.com](https://api.fxratesapi.com/latest) API is used for currency conversion.

## Project Structure

### Entity

The project contains a single entity:

#### Transaction

- **id (Long, primary key):** Unique identifier for each transaction.
- **amount (Double):** The amount of the transaction.
- **currency (CURRENCY):** The currency of the transaction. (USD or INR)
- **transactionType (TRANSACTION_TYPE):** Type of the transaction (CREDIT or DEBIT).
- **description (String):** Description of the transaction.
- **timestamp (Long):** Timestamp of the transaction in epoch seconds.

### APIs

Link to access API contract:
[https://documenter.getpostman.com/view/13254149/2s9YkuYdLa](https://documenter.getpostman.com/view/13254149/2s9YkuYdLa)

#### 1. `recordTransaction`

- **Endpoint:** `/api/v1/kirana-register/transaction`
- **Method:** POST
- **Description:** Records a transaction by accepting a payload and storing it in the database. If the currency is not INR, it performs a conversion using the [fxratesapi.com](https://api.fxratesapi.com/latest) API.

- **Request Payload:**
  ```json
  {
      "amount": 20.0,
      "currency": "USD",
      "transactionType": "CREDIT",
      "description": "Purchase of groceries",
      "timestamp": 1703727695
  }
```

- ** Response:**
 - Status code 201 (Created):
 ```json
 {
		"timestamp": "2023-12-28T07:23:27.543663",
		"data": {
			"transactionId": 8
		},
		"message": "Successfully recorded transaction"
}
```
- Status Code 4xx (Bad Request):
 ```json
 {
		"timestamp": "2023-12-28T07:24:05.832303",
		"data": null,
		"message": "Missing required parameters: [currency]"
}
```
- Status Code 5xx (Internal Server Error):
 ```json
 {
		"timestamp": "2023-12-28T07:24:05.832303",
		"data": null,
		"message": "Internal server error"
}
```


#### 2. `getTransaction`

- **Endpoint:** `/api/v1/kirana-register/transaction`
- **Method:** GET
- **Description:** Fetches transactions with optional date-wise filtering and supports pagination.
- **Request Parameters:**
   - `startDate` (Optional, epoch Long value in seconds)
   - `endDate` (Optional, epoch Long value in seconds)
   - `pageSize` (Optional, default pageSize = 50)
   - `pageIndex` (Optional, default pageIndex = 1)
- ** Example Request**
```json
GET http://localhost:8080/kirana-register/api/v1/transaction?startDate=1703727694&endDate=1703727695&pageIndex=1&pageSize=50
```

- ** Response:**
- Status code 200 (OK):
 ```json
{
		"timestamp": "2023-12-28T07:24:45.155604",
		"data": {
			"transactions": [
				{
					"id": 1,
					"amount": 1664.908058634,
					"currency": "INR",
					"transactionType": "CREDIT",
					"description": "Purchase of groceries",
					"timestamp": 1703727694
				}
			],
			"totalEntries": 1,
			"pageSize": 50,
			"pageIndex": 1
		},
		"message": null
}
```
- Status Code 4xx (Bad Request):
 ```json
{
		"timestamp": "2023-12-28T07:26:06.249202",
		"data": null,
		"message": "pageIndex must be greater than zero"
}
```
- Status Code 5xx (Internal Server Error):
 ```json
 {
		"timestamp": "2023-12-28T07:24:05.832303",
		"data": null,
		"message": "Internal server error"
}
```


## Database Selection
- This project uses SQL to develop as it ensures data accuracy in transactions and provides efficient tools for detailed financial reporting.  Hence finalised, **SQL** as primary database.
- Used H2 (in-memory database) for its ease of setup, rapid development, and resource efficiency in a temporary project context.

## Currency Conversion

Currency conversion is handled by the `CurrencyService`, which utilizes the `WebClient` to interact with the [fxratesapi.com](https://api.fxratesapi.com/latest) API.

## Testing

Unit testing has been implemented for the service and controller layers, achieving 100% code coverage.

## High-Level Design (HLD)

The architecture of the Kirana Register project follows a typical Spring Boot application structure. The application is designed with a modular approach, separating concerns into entities, services, controllers, and repositories. The H2 database provides temporary storage, and the external API is integrated for currency conversion.

## How to Use

1. Clone the repository.
2. open in intelliJ.
3. reload the maven project.
4. go to src/main/java/KiranaRegisterApplication.java
5. click on the play button and run the application