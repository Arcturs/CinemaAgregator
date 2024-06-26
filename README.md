# Cinema Agregator
## Описание
Проект CRUD сервиса для работы с фильмами и режиссерами. 
Выполнен в рамках заключительного этапа по Мегаолимпиаде от ИТМО "Промышленный бэкенд".

## Детальное описание
Данный сервис позволяет:
- получать все фильмы и всех режиссеров;
- получать по ИД фильм или режиссера;
- создавать режиссера или фильм;
- обновлять уже существующих фильмов или режиссеров;
- удалять фильмы или режиссеров.

## Тесты
В проекте реализованы тесты. Для их запуска необходимо перейти в папку проекта
и выполнить команду:
```shell
mvn clean install
```

## Запуск проекта
Для запуска проекта необходимо перейти в папку проекта и выполнить команду:
```shell
docker-compose up - d
```
Для этого понадобится установка [Docker](https://www.docker.com/).

## Список доступных эндпоинтов, моедли запросов и ответов
Список доступны эндпоинтов находится по ссылке после запуска:
``
http://localhost:8080/swagger-ui/index.html
``