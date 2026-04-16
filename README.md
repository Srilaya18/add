Open vscode -> folder addition-app
addition-app/
├── src/
│ ├── main/java/com/example/App.java
│ └── test/java/com/example/AppTest.java
└── pom.xml
 
pom.xml
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>addition-app</artifactId>
    <version>1.0</version>
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>

App.java
package com.example;
public class App {
    public int add(int a, int b) {
        return a + b;
    }
    public static void main(String[] args) {
        App obj = new App();
        System.out.println("Result = " + obj.add(5, 3));
    }
}

AppTest.java
package com.example;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class AppTest {
    @Test
    public void testAdd() {
        App obj = new App();
        assertEquals(8, obj.add(5, 3));
    }
}

Terminal -> mvn clean package
 
Create git repo, then in terminal
git init
git add .
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/Srilaya18/add.git
git push -u origin main
  
Modify Code & Commit Again
In App.java -> System.out.println("Result = " + obj.add(10, 2));
git add .
git commit -m "Updated addition"
git push
 
Create pipeline and script
pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                git branch: 'main',
                url: 'https://github.com/Srilaya18/add' //our git repo
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }
} 

Create Dockerfile
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/addition-app-1.0.jar app.jar               
CMD ["java", "-jar", "app.jar"]

Commit Again
git add .
git commit -m "Updated"
git push

Update the pipeline script completely
pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Srilaya18/add' //git repo
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Docker Build') {
            steps {
                bat 'docker build -t addition-app .'
            }
        }
    }
}
