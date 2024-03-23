# restaurantVote
Voting System for restaurants - ЗАПУСК ПРИЛОЖЕНИЯ БЕЗ КОНТЕЙНЕРИЗАЦИИ

Version 0.0.1 - REST HTTP MAVEN

Service is developed using Spring Boot, Spring MVC, Spring Security, Spring Data, Liquibase, Hibernate

Backend for Voting System for the best Restaurant

Before launching application it is neccessary:
1. Install Postgresql 13

   Далее идет часть для развертывания на VM (я разворачивал на яндекс клауде)
***********************************************************************************
3. If you use Ubuntu you should do next:
Я использовал виртуальную машину на яндекс клауде - https://vk.com/@web.varlamov-java-prilozhenie-na-yandexcloud
После создания VM осуществляем подключение к виртуальной машине:
ssh -i ubuntu restaurant@158.160.136.62

install chrome in VM ubuntu:
- wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | sudo apt-key add -
- sudo sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list'
- sudo apt-get update
- sudo apt-get install google-chrome-stable

Подключение к БД из командной строки - https://aristov.tech/blog/podklyuchenie-k-postgresu-iz-komandnoj-stroki/

УСТАНАВЛИВАЕМ Postgresql на VM

    - sudo apt install curl gpg gnupg2 software-properties-common apt-transport-https lsb-release ca-certificates
    - curl -fsSL https://www.postgresql.org/media/keys/ACCC4CF8.asc|sudo gpg --dearmor -o /etc/apt/trusted.gpg.d/postgresql.gpg
    - sudo apt-get update
    - sudo apt install postgresql-13
  Связано это с тем, что при установке СУБД создается пользователь Linux – postgres и также внутри СУБД создается пользователь postgres. Это разные пользователи – один для ОС, другой для СУБД, просто их имя совпадает, как раз для целей авторизации и предоставления доступа. Роли СУБД aeugene, естественно, не создается. Для того, чтобы зайти в СУБД нам необходимо или создать пользователя aeugene в СУБД с соответствующими правами (а как нам это сделать, если мы еще не зашли?) или стандартный вариант – запустить psql под пользователем postgres.
    - sudo -u postgres psql

  

ИЗМЕНИТЬ конфиги
sudo nano /etc/postgresql/13/main/pg_hba.conf
sudo nano /etc/postgresql/13/main/postgresql.conf

ПЕРЕЗАПУСТИТЬ postgresql
sudo pg_ctlcluster 13 main restart

Запуск самого приложения - mvn spring-boot:run

Для доступа открываем браузер и http://158.160.136.62:8080/
158.160.136.62 - внешний IP адрес из клауда
***********************************************************************************

Если не нужно VM, то сразу переходим к пункту 4

4. Run command in command line psql -U postgres -W -h localhost. Connect to Env
5. Then run
    - CREATE ROLE role;
    - CREATE DATABASE restaurant WITH OWNER = role ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    - GRANT ALL PRIVILEGES ON DATABASE restaurant TO role;

Functionality
User can be authentiphicated using endpoint - http://localhost:8080/api/registration/user with example body:
{
    "username":"cody",
    "firstName":"artem1",
    "lastName":"ryvkin1",
    "password":"cody1234",
    "email":"cod1y@ya.ru"
}

Password will be encrypted and only user with ADMIN role has an access to get list of all users and delete users

Endpoint to get All Users - http://localhost:8080/api/admin/users
Endpoint to delete Users - http://localhost:8080/api/admin/user/delete/22

Also User can register new Restaurant, but this user must be authentiphicated

User can vote for one restaurant only once, but he/she can vote for infinite amount of restaurants

Vote Endpoint: http://localhost:8080/api/vote/save with example body
{
    "restaurant": {
        "id": 41,
        "name":"McDonalds",
        "cuisine":"USA"
    },
    "rating": 10,
    "userId": 22
}

User can get all the Restaurants sorted by Name: http://localhost:8080/api/restaurant/list?sort=name,ASC
