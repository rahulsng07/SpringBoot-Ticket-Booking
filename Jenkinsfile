pipeline {
    agent any
    tools {
        maven 'Maven-3.9'   // your Maven installation name
        jdk 'Java-17'       // your JDK installation name
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    credentialsId: 'github-pat',
                    url: 'https://github.com/rahulsng07/SpringBoot-Ticket-Booking.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }
}
