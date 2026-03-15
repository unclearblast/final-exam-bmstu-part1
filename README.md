# Library Management System

Консольное приложение для управления библиотекой, разработанное на языке **Java** с использованием **JDBC** и **SQLite**.

Проект создан в рамках итоговой аттестации по дисциплинам:

- Современные базы данных: технологии и подходы
- Программирование на языках высокого уровня: продвинутые техники

---

# Используемые технологии

- Java
- JDBC
- SQLite
- Maven
- Git / GitHub

---

# Архитектура проекта

Проект построен по принципу **разделения ответственности (Separation of Concerns)**.

Описание модулей:

**model**  
Классы моделей данных.

**dao (Data Access Object)**  
Слой работы с базой данных через JDBC.

**db**  
Подключение к базе данных.

**menu**  
Консольное меню приложения.

**Main**  
Точка входа в программу.

---

# Функциональность приложения

## Работа с книгами

- добавление книги
- просмотр списка книг
- поиск книги по названию

## Работа с читателями

- регистрация читателя
- просмотр списка читателей

## Операции выдачи

- выдача книги читателю
- возврат книги
- просмотр выданных книг

## Статистика

- популярные книги
- список выданных книг

---

# База данных

Используется **реляционная база данных SQLite**.

Основные таблицы:

### books

Хранит информацию о книгах.

| поле | тип |
|-----|-----|
| id | INTEGER |
| title | TEXT |
| author | TEXT |
| year | INTEGER |

---

### readers

Хранит информацию о читателях.

| поле | тип |
|-----|-----|
| id | INTEGER |
| name | TEXT |
| email | TEXT |

---

### loans

Хранит операции выдачи книг.

| поле | тип |
|-----|-----|
| id | INTEGER |
| book_id | INTEGER |
| reader_id | INTEGER |
| issue_date | DATE |
| return_date | DATE |

---

# Связи между таблицами


Reader 1 ---- * Loan * ---- 1 Book


Это означает:

- один читатель может взять много книг
- одна книга может быть выдана много раз

---

# Индексы

Для оптимизации SQL-запросов используются индексы:


books(title)
loans(book_id)
loans(reader_id)


Это ускоряет:

- поиск книг
- соединения таблиц (JOIN)

---

# ER-диаграмма базы данных

Схема базы данных находится в файле:


docs/ER_diagram.png


---

# Демонстрационная база данных

В репозитории находится файл:


library.db


Он уже содержит тестовые данные.

Примеры записей:

Книги:

- Clean Code
- Effective Java
- Design Patterns
- Refactoring

Читатели:

- Иван Иванов
- Пётр Петров
- Анна Смирнова

---

# Примеры SQL-запросов

## Список выданных книг

```sql
SELECT books.title, readers.name, issue_date
FROM loans
JOIN books ON books.id = loans.book_id
JOIN readers ON readers.id = loans.reader_id
WHERE return_date IS NULL;
Популярные книги
SELECT books.title, COUNT(loans.book_id) AS issue_count
FROM loans
JOIN books ON books.id = loans.book_id
GROUP BY books.title
ORDER BY issue_count DESC;

```



# Запуск проекта

Скачать проект

git clone https://github.com/USERNAME/library-management-system

Перейти в папку проекта

cd library-management-system

Скомпилировать проект

mvn compile

Запустить приложение

mvn exec:java -Dexec.mainClass="Main"
Пример работы программы

После запуска появится консольное меню:

LIBRARY MENU

1 Add book
2 Show books
3 Find book
4 Register reader
5 Show readers
6 Issue book
7 Return book
8 Issued books
9 Popular books
0 Exit
