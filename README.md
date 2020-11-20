## How to run the server

Make sure you are currently in crypto/ folder, then run the following command:

```mvn spring-boot:run```

### createAccount(name, usd_balance)

```
curl \
  --header "Content-Type: application/json" \
  --request POST \
  --data '{"name": "liu", "usd_balance": 6000}' \
  http://localhost:8080/api/v1/account
```

### fetchAccountDetails(account_id)

```
curl \
  --request GET \
  http://localhost:8080/api/v1/account/{ACCOUNT_ID_FROM_PREVIOUS_CMD}
```

### createLimitOrder(account_id, price_limit)

```
curl \
  --header "Content-Type: application/json" \
  --request POST \
  --data '{"account_id": {ACCOUNT_ID_FROM_PREVIOUS_CMD}, "price_limit": 3000}' \
  http://localhost:8080/api/v1/order
```

### fetchOrderDetails(order_id)

```
curl \
  --request GET \
  http://localhost:8080/api/v1/order/{ORDER_ID_FROM_PREVIOUS_CMD}
```

## Note

I made assumption that for each trading, it will use 60% of the current USD balance. You can change this setting in
the `application.properties` file:

```TRADING_PERCENTAGE=0.6```
