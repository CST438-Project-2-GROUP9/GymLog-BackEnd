# GymLog-BackEnd CST438 Group 9 P2
Fitness Tracker Backend

# Description
Implemented REST API's for our backend API hosted on Render by using SpringBoot, OAuth2, and Dockerfile. The API gets the workout the user created and shows what exercises they have.

# Team Members
- Neil Cabanilla
- Isaiah Artiaga
- Dima Krayilo
- Victor Perez

# Tech Stack
- SpringBoot
- DockerFile
- OAuth2
- Render

# How to run
Just go to the file FitnessTrackerApplication and run it. It should prompt you to localhost8080. For Docker, must have docker app online and then do the following two commands...
docker build -t fitness-tracker . <-- This command first
docker run --rm -p 8080:8080 fitness-tracker  <-- Then finally this command after

P.S: Might need to have Google Auth for GOOGLE_CLIENT_ID & GOOGLE_CLIENT_SECRET for applications.properties and paste thsoe values in there. When doing on render just add environmental variable.

# Live API URL
https://gymlog-backend-5.onrender.com

(Takes a minute for it to load since render is starting up)

# Swagger API
https://gymlog-backend-5.onrender.com/swagger-ui/index.html

# Mockup
<img width="1160" height="579" alt="Mockup" src="https://github.com/user-attachments/assets/8dac6a9c-a097-44d0-b807-ff5e6171d3e1" />

# ERD
<img width="1155" height="582" alt="ERD" src="https://github.com/user-attachments/assets/d536abf8-4e37-4cc2-8aaa-d219604d904a" />
