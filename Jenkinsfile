pipeline {
    agent any
    tools {
        maven 'Maven-3.9'   // Use the Maven installation name you set in Jenkins
        jdk 'Java-17'       // Match the JDK installed in Jenkins
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
