Requirements:

Tomcat 8.5.20
MySQL Server v. 5.7.19

Default DB settings:

Host & port: localhost:3306
DB name: test
User name: user
User pass: password

By default data will be saved into database via Hibernate implementation.
It can be changed to in memory via Collections implementation by setting use.collections.storage=true property.
This and other Hibernate custom properties can be found in src/main/resources/app.properties

