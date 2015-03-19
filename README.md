# repository-pattern

Sample implementation of [Repository Pattern] defined in [Patterns of Enterprise Application Architecture].

Basic ideas are:

- A Repository mediates between the domain and data mapping layers
- Mapping code is encapsulated behind the Repository
- Clean separation and on-way dependency between the domain and data mapping layers

## Implementation Details

### Entity

In this sample the following two types of entities are used.

- Company - Represents the company contains company name and URL of company website.
- Contact - Represents the contact information of employee includes first name and last name.

Both of entities inherit from abstract class called Entity. The Entity class only has one id field which represents the unique identifier of each entity.

### Repository

The repository represents the collection of entities.

- CompanyRepository - Collection of company entity.
- ContactRepository - Collection of contact entity.

### Repository Strategy

There are three types of repository strategies are implemented.

- InMemoryStrategy - Queries the in-memory collection objects
- FileStrategy - Queries the files in local filesystem
- MongoStrategy - Queries the MongoDB


[Repository Pattern]: http://martinfowler.com/eaaCatalog/repository.html
[Patterns of Enterprise Application Architecture]: http://martinfowler.com/books/eaa.html
