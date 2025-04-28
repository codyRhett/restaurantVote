FROM adoptopenjdk/openjdk11:ubi

# Создаем системного юзера и группу с явным UID/GID
RUN useradd -r -u 1001 appuser && \
    # создаем директорию app
    mkdir /app && \
    # Настройка прав доступа к директории /app \
    # 7 (владелец): Чтение + запись + выполнение (rwx).
    # 5 (группа): Чтение + выполнение (r-x).
    # 0 (остальные): Нет прав (---).
     chmod 750 /app && \
    # Назначение владельца директории /app
    chown appuser:appuser /app

# укзываем рабочую дирректорию
WORKDIR /app

# Копируем файлы с правами
ARG WAR_FILE=target/restaurantVote-1.0-SNAPSHOT.war
# Копирование файлов с назначением владельца и группы
COPY --chown=appuser:appuser ${WAR_FILE} /app/application.war
COPY --chown=appuser:appuser src/main/webapp /app/webapp

# Явное переключение пользователя и рабочей директории
# Используем UID вместо имени. Запуск от имени нового созданного юзера
USER 1001

ENTRYPOINT ["java","-jar","/app/application.war"]