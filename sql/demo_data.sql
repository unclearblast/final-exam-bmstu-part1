-- Демонстрационные книги
INSERT INTO books (title, author, year) VALUES
('Clean Code', 'Robert C. Martin', 2008),
('Effective Java', 'Joshua Bloch', 2018),
('Design Patterns', 'Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides', 1994),
('Refactoring', 'Martin Fowler', 1999),
('The Pragmatic Programmer', 'Andrew Hunt, David Thomas', 1999);

-- Демонстрационные читатели
INSERT INTO readers (name, email) VALUES
('Александр Большаков', 'alex@delarge.com'),
('Пётр Петров', 'petr@example.com'),
('Дмитрий Серов', 'ugadayte@example.com'),
('Снид Тамзарян', 'sneed@feedandseeed.com');

-- Демонстрационные операции выдачи
INSERT INTO loans (book_id, reader_id, issue_date, return_date) VALUES
(1, 1, '2026-03-01', NULL),
(2, 2, '2026-03-05', '2026-03-12'),
(3, 3, '2026-03-10', NULL),
(4, 1, '2026-03-15', NULL);
