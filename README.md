# fawryAppWebService

## Project Overview
FawryApp is a payment system inspired by the Fawry system, designed to facilitate payments for a variety of services including mobile recharges, internet payments, landline services, and donations. The application implements a RESTful API to enable users and administrators to interact seamlessly with the system.

## Description
The system provides a robust set of functionalities:

### Services Offered:
- **Mobile Recharge Services**:
  - Providers: Vodafone, Etisalat, Orange, We

- **Internet Payment Services**:
  - Providers: Vodafone, Etisalat, Orange, We

- **Landline Services**:
  - Monthly Receipt
  - Quarterly Receipt

- **Donations**:
  - Cancer Hospital
  - Schools
  - NGOs (Non-profit Organizations)

## Features

### User Features:
1. **User Authentication**:
   - Sign in using email and password.
   - Sign up by providing a username, email, and password, with validation for existing usernames and emails.

2. **Service Search**:
   - Search for services by name, retrieving matching results.

3. **Service Payment**:
   - Pay for services through a payment form, supporting credit card, wallet balance, or cash on delivery.

4. **Refund Requests**:
   - Request refunds for completed transactions, subject to admin approval.

5. **Wallet Management**:
   - Maintain a wallet balance and add funds via credit card.

6. **Discounts**:
   - View applicable discounts for services as managed by the admin.

### Admin Features:
1. **Service Provider Management**:
   - Add new service providers using a dynamic form.

2. **Discount Management**:
   - Apply overall discounts or specific discounts for designated services.

3. **Transaction Management**:
   - View all user transactions, including payments, wallet additions, and refunds.

4. **Refund Management**:
   - Review, accept, or reject refund requests.

## RESTful API Endpoints
The following endpoints provide access to the functionalities:

### User Endpoints:
- **POST** `/user/signIn`: Authenticates the user.
  - Input: email, password
  - Output: success or error message

- **POST** `/user/signUp`: Registers a new user.
  - Input: username, email, password
  - Output: success or error message

- **GET** `/services/{serviceName}/serviceProviders`: Searches for services.
  - Output: list of service providers

- **POST** `/services/{serviceName}/{serviceProvider}/{paymentMethod}`: Processes service payment.
  - Input: service details and payment information
  - Output: transaction details or error message

- **POST** `/user/requestRefund/{transactionId}`: Initiates a refund request.
  - Input: transaction ID
  - Output: confirmation message or error message

- **GET** `/user/wallet/charge/{chargeAmount}`: Adds funds to the wallet.
  - Output: balance update confirmation or sign-in prompt

- **GET** `/discounts`: Retrieves available discounts.
  - Output: list of services with discount details

### Admin Endpoints:
- **POST** `/admin/discounts/{serviceName}/{discount}`: Adds a discount.
  - Input: service name, discount percentage
  - Output: success or error message

- **POST** `/admin/paymentTransactions`: Lists all payment transactions.
  - Output: list of transaction details

- **POST** `/admin/walletTransactions`: Lists all wallet transactions.
  - Output: list of wallet transaction details

- **POST** `/admin/refundRequests`: Lists all refund requests.
  - Output: list of refund requests

- **POST** `/admin/processRefund/{transId}/{decision}`: Processes refund requests.
  - Input: transaction ID, decision (accept/reject)
  - Output: success or rejection message

## Design Patterns
- **Factory Method Pattern**: For creating service providers dynamically.
- **Decorator Pattern**: To handle discounts on transactions.
- **Strategy Pattern**: For various payment methods.
- **Observer Pattern**: For notifying users about transaction updates.

## Notes
1. Admin credentials: Username: `admin@gmail.com`, Password: `0000`.
2. Users must know their transaction ID to request a refund.
3. Wallet payments are enabled for mobile recharge services.
4. Input validation is enforced for mobile recharge service provider forms.
