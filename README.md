# Nierelacyjne bazy danych - Wine Reviews API

Api: localhost:8080/api/

Docs: localhost:8080/api/swagger-ui/#/

# Run with docker (preferred method)
## While being in project root directory (parent of src directory):
```
gradle clean build
docker-compose up --build
```
To fill database with data:
```
python fill.py [noOfRecords]
```

# Run images separately
## Cassandra
Run cassandra container (where [cassandraContainer] is a name for the container, e.g. cassandra):
```
 docker run -p 9042:9042 -v ./data:/var/lib/cassandra --name [cassandraContainer] cassandra:4.0
```
To open cqlsh:
```
docker exec -it [cassandraContainer] /bin/bash
cqlsh
```

## API

#### While being in project root directory (parent of src directory):
Build gradle project:
```
gradle clean build
```
Build docker image (where [apiImage] is a name for built image and [version] is a version tag for this image, e.g. v1):
```
docker build -t [apiImage]:[version] .
```
Run built image (where [apiContainer] is a name for the container in which image will be run, e.g. api):
```
docker run -p 8080:8080 --name [apiName] [apiImage]:[version]
```

...now do some crazy stuff with api available at localhost:8080...

To stop container:
```
docker stop [apiContainer]
```
To start stopped container:
```
docker start [apiContainer]
```
To list all containers:
```
docker ps -a
```
To list local images:
```
docker image ls
```
