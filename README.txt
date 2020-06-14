
# To run the tests and launch the server run the following command:

./mvnw package && java -jar target/anagram-0.0.1-SNAPSHOT.jar

 Then you will find the URL's for the required endpoints here:
    http://localhost:8080/anagrams/{string1}/{string2}
    http://localhost:8080/anagrams/{string1}

Docker
To build and run Docker image:
  docker build -t mrdon/anagram-docker .

  docker run -p 8080:8080 mrdon/anagram-docker