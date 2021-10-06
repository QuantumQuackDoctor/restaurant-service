# Restaurant Service

This service handles all requests related to the creation and management of restaurants, as well as the search functionality.

## Responsibilities

- Restaurant creation and deletion (Admin)
- Searching and viewing restaurants (User and Admin)

## Endpoints
- GET /restaurants/search: search functionality
- GET /restaurants/{id}: get single restaurant
- DELETE /restaurants/{id}: admin delete restaurant
- PUT /restaurants: admin create restaurant
- PATCH /restaurants: admin update restaurants
- PATCH /restaurants/menu: admin add menu item