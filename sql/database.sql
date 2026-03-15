-- Создание таблицы книг
CREATE TABLE IF NOT EXISTS books (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    author TEXT NOT NULL,
    year INTEGER
);

-- Создание таблицы читателей
CREATE TABLE IF NOT EXISTS readers (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    email TEXT
);

-- Таблица операций выдачи книг
CREATE TABLE IF NOT EXISTS loans (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    book_id INTEGER NOT NULL,
    reader_id INTEGER NOT NULL,
    issue_date TEXT,
    return_date TEXT,

    FOREIGN KEY(book_id) REFERENCES books(id),
    FOREIGN KEY(reader_id) REFERENCES readers(id)
);

-- Индексы для ускорения поиска
CREATE INDEX IF NOT EXISTS idx_books_title
ON books(title);

CREATE INDEX IF NOT EXISTS idx_loans_book
ON loans(book_id);

CREATE INDEX IF NOT EXISTS idx_loans_reader
ON loans(reader_id);
