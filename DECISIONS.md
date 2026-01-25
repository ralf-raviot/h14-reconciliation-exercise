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

### Test:

- Bank and Swarm are not tested because they are mocked.

### CronJob:

- Since portfolio management is usually done manually, an update time of 24h should be enough.
- Since this is an exercise, the job is also launched at the start of the application, to be able to have data
  immediately.

### Error handling:

- No specific error handling is done.

## Change in production

- Manage pagination and filters (by clientId, for example)
- Add a health check endpoint.
- Use Spring Security.
- Use a real database.
- Cronjob can be replaced with a scheduler if kubernetes is used.
- Add meaningful error messages.
- Add meaningful information, like the total value of the portfolio.