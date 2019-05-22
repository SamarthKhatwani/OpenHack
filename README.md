# OpenHack

## S3 Access URL
http://275openhack.s3-website.us-east-2.amazonaws.com/

## Team Member
1. Nirbhay Kekre 012455313 <nirbhay.kekre@sjsu.edu>
2. Rajat Dineshchandra Chaurasia 013734279 <rajat.chaurasia@sjsu.edu>
3. Samarth  Khatwani 013592072 <samarth.khatwami@sjsu.edu>
4. Yash Kumar Mahajan 013766675 <yashkumar.mahajan@sjsu.edu>

## Pre-requisite:
### Backend
1. Maven and Java (1.8 or higher) should be installed.
2. MySQL database server should be installed and up and runnging
3. create a database named openHack, using following command:<br>
```sql
CREATE DATABASE openHack;
```
4. Optional - eclipse
### Frontend
1. ReactJs and npm should be installed, Open Command Propmt in Front End Folder.
2. Run npm install
3. After successful installation of node module, Run npm start to Start Front End.
4. HomeAway HomePage can be accessible at http://localhost:3000

## How To run
### Backend
1. clone this repository
2. Do necessary changes for database in application.properties file located in resource folder
3. If you have eclipse installed, open the project in backend directory. And run it as spring boot application
4. If eclipse is not installed, open terminal and run following command:
```sh
mvn clean install -DskipTests
```
5. go to target folder once build is successful
6. run following command at terminal:
```sh
java -jar open-hack-0.0.1-SNAPSHOT.jar
```

