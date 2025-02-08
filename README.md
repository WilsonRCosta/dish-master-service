# dish-master-service
- Spring WebFlux service running on Java 23 with Gradlew as dependency manager

# Run locally
- ./gradlew bootRun
- Access: http://localhost:8080/api/swagger-ui

## Database
- Run Elasticsearch on Docker on port 9200
``` shell
docker run -p 127.0.0.1:9200:9200 -p 127.0.0.1:9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.17.24
```

# CORS
- By default, allows origin http://localhost:4200
