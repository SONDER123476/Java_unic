-- Таблица authors
CREATE TABLE authors (
                         id BIGSERIAL PRIMARY KEY,
                         first_name VARCHAR(100) NOT NULL,
                         last_name VARCHAR(100) NOT NULL,
                         email VARCHAR(255) UNIQUE
);

-- Таблица books
CREATE TABLE books (
                       id BIGSERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       isbn VARCHAR(20) NOT NULL UNIQUE,
                       author_id BIGINT NOT NULL,
                       CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES authors (id) ON DELETE CASCADE
);
-- Таблица логов
CREATE TABLE change_logs (
                             id SERIAL PRIMARY KEY,
                             change_type VARCHAR(50), -- Вставка, Обновление, Удаление
                             entity_name VARCHAR(100), -- Класс сущности
                             entity_id BIGINT,         -- ID изменённого объекта
                             change_description TEXT,  -- Описание изменения
                             timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Время изменения
);
CREATE TABLE notification_conditions (
                                         id BIGSERIAL PRIMARY KEY,
                                         email VARCHAR(255) NOT NULL,
                                         entity_name VARCHAR(255) NOT NULL,
                                         condition_type VARCHAR(50), -- например, "ADD", "DELETE", "UPDATE"
                                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);