**Мини проект по окончанию курса Java Core**
Вариант 3 - 1.
Система Приемная комиссия. Абитуриент регистрируется на один из Факультетов.
Что необходимо сделать:
1.  Создать базу данных в любой СУБД (MySQL, MS SQL, SQLite, PostreSQL и тд) на ваш вкус;
2.  Организовать доступ к базе данных из вашего приложения используя JDBC и шаблон DAO;
3.  Реализовать необходимые сервисы, которые будут каки-либо образом использовать данные из таблиц БД (просто получать, получать по параметру, получать в определённом порядке, считать статистику на основе полученных данных и тд);
4.  Реализовать вывод результатов работы сервисного слоя в консоль;
5.  Написать тесты для сервисного слоя (TestNG);
6.  Классы и интерфейсы должны быть хорошо структурированы по пакетам;
7.  Соблюдать Java Code Conventions;

Установка и запуск:
1. git clone
2. mvn clean install
3. mvn test (для запуска testNG тестов)

Проект работает с БД Oracle Autonomous 19c с использованием Wallet для доступа к ней.
Соответственно тесты можно запустить локально на своем компьютере
