The DB used is MySQL. To test the persistence a XAMPP server was used.

The data in application.properties is used for local use and do not contain production secrets.

This was created only to test a connection to the IBM MQ Manager.

To deploy:
  - Create DockerFile for the front
  - Create DockerFile for the backend
  - Create a docker compose file to run everything at once
  - Make sure to store credentials in .env and use env variables in application.properties and wherever needed
