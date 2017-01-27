# dynamodb-local-view
This is for DynamoDB Local view. To see DynamoDB Local contents like Amazon DynamoDB view.

# Preparation
If needed you can arrange ```application.yml``` file for your own URL. Default value is below.

```application.yml
amazon:
  dynamodb:
    endpoint: http://localhost:8000/
```

You don't need to modify "accesskey" and "secretkey".

# Build
Firstly to get a jar file type mvn command.

```
$ mvn clean package
```

# Execution
Type below command after creating jar file with maven command at project root folder.

```
$ java -jar target/dynamodb-view-0.0.1-SNAPSHOT.jar
```
If you want to access to DynamoDB with not default host and port, you can type below.

```
$ java -jar target/dynamodb-view-0.0.1-SNAPSHOT.jar --server.host=<YOUR HOST> --server.port=<YOUR PORT>
```
