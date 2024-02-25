# JAVA ADVANCED
## Module 3: REST API Architecture

1. **Create a Maven project with 4 modules:**
    - `jmp-dto`
    - `jmp-service-api`
    - `jmp-cloud-service-impl`
    - `jmp-service-rest`

2. **Under the `jmp-dto` module, create the following classes:**
    - `User`: `Long id; String name; String surname; LocalDate birthday;`
    - `Subscription`: `Long id; User user; LocalDate startDate;`
    - `UserRequestDto`: `Long id; String name; String surname; String birthday;`
    - `SubscriptionRequestDto`: `Long id; Long userId;`
    - `UserResponseDto`: `Long id; String name; String surname; String birthday;`
    - `SubscriptionResponseDto`: `Long id; Long userId; String startDate;`

3. **In the `jmp-service-rest` module, create `UserController` with the following methods:**
    - `createUser(UserRequestDto);`
    - `updateUser(UserRequestDto);`
    - `deleteUser(Long);`
    - `getUser(Long);`
    - `getAllUsers();`

4. **Note: Use the `id` field in `UserRequestDto` only for updating data.**

5. **The following methods must return `UserResponseDto`:**
    - `createUser(UserRequestDto);`
    - `updateUser(UserRequestDto);`
    - `getUser(Long);`
    - `getAllUsers();`

6. **In the `jmp-service-rest` module, create `ServiceController` with the following methods:**
    - `createSubscription(SubscriptionRequestDto);`
    - `updateSubscription(SubscriptionRequestDto);`
    - `deleteSubscription(Long);`
    - `getSubscription(Long);`
    - `getAllSubscriptions();`

7. **Note: Use the `id` field in `SubscriptionRequestDto` only for updating data.**

8. **The following methods must return `SubscriptionResponseDto`:**
    - `createSubscription(SubscriptionRequestDto);`
    - `updateSubscription(SubscriptionRequestDto);`
    - `getSubscription(Long);`
    - `getAllSubscriptions();` (as list)

9. **Add necessary interfaces/methods to `jmp-service-api`.**

10. **Implement interfaces/methods in `jmp-cloud-service-impl`.**

11. **`UserController` and `ServiceController` must provide REST API interfaces according to Level 2 of the Richardson Maturity Model.**

12. **Use in-memory structures (List, Map, etc.) to store data.**

13. **Utilize `jmp-cloud-service-impl` to implement `UserController` and `ServiceController`.**

14. **Implement an Application class with the `@SpringBootApplication` annotation and a main method.**

15. **Employ lambdas and Java 8 features wherever applicable.**

16. **Make sample requests to `UserController` and `ServiceController`, and provide screenshots/responses.**

*For a 5-star implementation, also include:*

17. **Use a database to store data.**

18. **Utilize Spring Boot Data JPA module for data access.**

19. **Use Spring converters to convert between DTOs and entities:**
    - `UserRequestDto` to `User`
    - `User` to `UserResponseDto`
    - `SubscriptionRequestDto` to `Subscription`
    - `Subscription` to `SubscriptionResponseDto`

20. **Document methods in `UserController` and `ServiceController` using Swagger 2 annotations.**

21. **Make sample requests via Swagger UI and provide screenshots.**

*For a comprehensive implementation, also:*

22. **Apply Java 9 modules wherever applicable.**

23. **Implement REST APIs in accordance with all levels (0-3) of the Richardson Maturity Model.**

24. **Make sample requests to `UserController` and `ServiceController`, and provide screenshots/responses.**
