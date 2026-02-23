# Clients API

REST API для хранения данных клиентов и их контактов с JWT-аутентификацией.

## Что умеет приложение
- Регистрация, логин, обновление и отзыв токенов (`/auth/**`).
- CRUD-операции для клиентов (`/client/**`).
- CRUD-операции для контактов (`/contact/**`).
- Документация OpenAPI/Swagger UI.

## Требования
- Java 25
- Maven 3.9+ (или Maven Wrapper `./mvnw`)
- Docker и Docker Compose (для запуска через контейнеры)
- PostgreSQL 18+ (если запускать локально без Docker Compose)

## Настройка переменных окружения
1. Скопируйте пример:
   ```bash
   cp .env.example .env
   ```
2. При необходимости измените значения в `.env`.

Основные переменные:
- `POSTGRES_DB`, `POSTGRES_USER`, `POSTGRES_PASSWORD`, `POSTGRES_PORT`
- `APP_PORT`, `SPRING_PROFILES_ACTIVE`
- `JWT_ISSUER`, `JWT_ACCESS_TTL_MINUTES`, `JWT_REFRESH_TTL_DAYS`, `JWT_SECRET`

## Запуск через Docker Compose (рекомендуется)
Из корня проекта:

```bash
docker compose up --build
```

После запуска будут доступны:
- API: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

Остановить контейнеры:

```bash
docker compose down
```

## Локальный запуск (без Docker Compose)
1. Поднимите PostgreSQL и создайте БД/пользователя.
2. Экспортируйте переменные окружения (или настройте их в IDE):
    - `DB_HOST`
    - `DB_PORT`
    - `DB_NAME`
    - `DB_USER`
    - `DB_PASSWORD`
    - `JWT_SECRET` (обязательно для `prod`, в `dev` есть значение по умолчанию)
3. Запустите приложение:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

## Профили
- `dev` — профиль разработки (подробные security/sql-логи).
- `prod` — профиль продакшна (более строгие требования к переменным, минимальные логи).

## Миграции БД
При старте приложения Liquibase автоматически применяет миграции из:
`src/main/resources/db/changelog/master.yaml`.
