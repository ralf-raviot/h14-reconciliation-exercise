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
- The weakness of this model is the "duplication" between position and ConsolidatedPosition. There is maybe a better way
  to do it.

### Model:

- The bank object is an enum since we need one specific integration for each bank.
- There is no client object since there is no direct feature associated with it for now. Only the ID is used.

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

### Error handling:

- No specific error handling is done.

# Change in production

- Manage pagination and filters (by clientId, for example)
- Add a health check endpoint.
- Use Spring Security.
- Use a real database.
- Cronjob can be replaced with a scheduler if kubernetes is used.
- Add meaningful error messages.
- Add meaningful information, like the total value of the portfolio.
- Implement a real repository for importing the data.
- Mock the bank and swarm repositories in the tests.