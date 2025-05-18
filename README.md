# Customer Management System

This is a full-stack Customer Management System built using **Spring Boot**, **React.js**, and **PostgreSQL**.  
It supports customer CRUD operations, including:

- Creating a customer with multiple mobile numbers, addresses, and family members
- Updating a customer, including adding new or updating existing mobile numbers and addresses
- Fetching customer details with pagination and filtering
- Viewing customer details by ID

---

## 🛠 Tech Stack

### Backend
- Java 8+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

- 
## Database Structure

**Customer Table**
- `id`, `name`, `date_of_birth`, `nic`

**MobileNumber Table**
- `id`, `number`, `customer_id`

**Address Table**
- `id`, `address_line_1`, `address_line_2`, `city`, `country`, `customer_id`

**FamilyMembers Table**
- Many-to-many relationship with Customer (`customer_family_members` join table)
