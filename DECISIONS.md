This file is optional, if you find it useful to document your decisions.

Our discussion will be based on the code, not on this file.

## Content
- Assumptions made
- Trade-offs
- Open questions
- What I would change in production

### Database:

- Since it's an exercise, H2 it's the most suitable choice.
- For simplicity, no pagination or filter is used.

### Model:

- The bank object is an enum since we need one specific integration for each bank.
- There is no client object since there is no direct feature associated with it for now. Only the ID is used.
- The weakness of this model is the "duplication" between position and ConsolidatedPosition. There is maybe a better way
  to do it.
- The isin is considered as always coherent. The best way to handle it is, of course, to have a unique source of truth.
- The position object should be linked to a client/portfolio ID, but I realize this too late. This has lead to some
  complexity
  in the code.

### Test:

- Bank and Swarm are not tested because they are mocked.
- In the same logic, the tests used directly the mocked data of the "production" repository instead of mocking it to
  avoid duplication of the mocking.

### CronJob:

- Since portfolio management is usually done manually, an update time of 24h should be enough.
- Since this is an exercise, the job is also launched at the start of the application, to be able to have data
  immediately.

### Importing data:

- For simplicity, the data import is mocked in the different bank and swarm repositories.
- "Technically" Swarm and Bank "repositories" are not really repositories.
- All bank repositories have the same code (=> Duplication). In real conditions, all banks have their own API, and so,
  the code will differ.

### Error handling:

- No specific error handling is done.

### Frontend:

- For timing reasons, the frontend is REALLY minimalistic.

# Change in production

- Manage pagination and filters (by clientId, for example)
- Add a health check endpoint.
- Use Spring Security.
- Use a real database.
- Cronjob can be replaced with a scheduler if kubernetes is used.
- Add meaningful error messages.
- Add meaningful information, like the total value of the portfolio.
- Implement a real repository for importing the data. This includes respecting the API contract of each bank.
- Mock the bank and swarm repositories in the tests.