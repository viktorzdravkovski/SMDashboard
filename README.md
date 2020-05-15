# TaskTrackingTool
A tool used for tracking tasks between users.

# Виктор Здравковски 193292
Проектот не е завршен, не работи Frontend конекцијаta со Backend.

Апликацијата е наменета за работа со таскови и корисници. Можно е креирање, бришење, читање на таскови, корисници, коментари...
За стартување на Backend-от, треба да се направи clean install на root ниво, со цел да се изгенерираат потребните сервиси и модели од .yaml фајловите, со користење на Swagger, потоа само со Run се стартува апликацијата на порта 8080.
Frontend-от се стартува со наредната команда: npm install && ng build swagger-client && ng build swagger-auth-client && ng build swagger-user-info-client && ng build.

На патеката http://localhost:8080/swagger-ui.html е поставен Swagger UI каде што покрај документација на API, може и да се тестираат сите endpoints. Најпрво треба да се пушти POST request до Login endpoint за автентикација на корисникот. Тест корисникот кој што е достапен е со credentials:
 - username: Viktor
 - password: Admin123

Апликацијата користи H2 in-memory database со Liquibase migration scripts, што значи дека нема потреба за сетапирање на MySQL server или нешто слично за подигнување на база, а Liquibase скриптите се повикуваат на startup и ги креираат потребните табели, како и тест податоците во нив.
