# Local Postgres (Docker)

This project uses PostgreSQL for local development via Docker.

## Start the database

```zsh
docker compose up -d
```

## Configure Spring profile

Use the `local` profile to load the Postgres settings:

```zsh
export SPRING_PROFILES_ACTIVE=local
```

## Run the app

```zsh
./gradlew bootRun
```

## Stop the database

```zsh
docker compose down
```

## Connection details

- URL: `jdbc:postgresql://localhost:5432/academy`
- User: `academy`
- Password: `academy`
