pipeline {
    agent any
    tools {
        maven 'Maven-3.9'
        jdk 'Java-21'
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
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}

