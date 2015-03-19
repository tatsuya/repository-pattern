# repository-pattern

Sample implementation of [Repository Pattern] defined in [Patterns of Enterprise Application Architecture].

## Implementation Details

### Entity

In this sample the following two types of entities are implemented.

- Company - Represents the company contains company name and website
- Contact - Represents the contact information of employees includes first name and last name.

Both of entities inherit from abstract Entity class. The Entity class has only one field "id" represents the unique identifier of each entity.

### Repository

The repository represents the collection of entities.

- CompanyRepository - Collection of company entity.
- ContactRepository - Collection of contact entity.

### Repository Strategy

There are three types of repository strategies are implemented.

- InMemoryStrategy - On memory database
- FileStrategy - Use file system as persistent data storage
- MongoStrategy - Use MongoDB database


[Repository Pattern]: http://martinfowler.com/eaaCatalog/repository.html
[Patterns of Enterprise Application Architecture]: http://martinfowler.com/books/eaa.html
