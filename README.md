# AppleCodingChallenge


The application structure is as follows.
- **apple-coding-challenge** - Microservice implemented using Spring boot. [More info](apple-coding-challenge/README.md)
- **client-path-sum** - A NodeJs application implemented using Angular 7. This consumes services hosted by server side.  [More info](client-path-sum/README.md)
- **docker-compose.yml** - Docker compose file to run server services and course-enrollment-client in container.

### Build

#### 1) Build Docker images and run it in containers using docker-compose from parent directory.
   This also create container for Mysql and run it.
   
```
$ docker-compose up
```


#### 2) Build and run client-path-sum application (Important: docker-compose up will handle everything so this is not necessary part.)
NOTE: To run without docker container follow [steps](apple-coding-challenge/README.md) in apple-coding-challenge project.

```
$ cd client-user-management
$ docker build -t client-path-sum.
$ docker run -d -p 4200:80 client-path-sum

### Access application using following URL

```
http://localhost:4200
```
