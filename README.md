Requirements:
- Tomcat 8.5.20<br>
- MySQL Server v. 5.7.19<br>

Default DB settings:<br>
Host & port: localhost:3306<br>
DB name: test<br>
User name: user<br>
User pass: password<br>

By default data will be saved into database via Hibernate implementation.<br>
It can be changed to in memory via Collections implementation by setting use.collections.storage=true property.<br>
This and other Hibernate custom properties can be found in src/main/resources/app.properties

