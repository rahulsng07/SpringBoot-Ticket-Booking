pipeline {
    agent any

    tools {
        // Use the tool names you configured in Jenkins global tools
        jdk 'Java-17'
        maven 'Maven-3.9'
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the repository
                git branch: 'main',
                    url: 'https://github.com/rahulsng07/SpringBoot-Ticket-Booking.git',
                    credentialsId: 'github-pat'
            }
        }

        stage('Build') {
            steps {
                // Run Maven build on Windows
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                // Run tests if needed
                bat 'mvn test'
            }
        }

        stage('Archive') {
            steps {
                // Archive built jar files
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed. Check logs!'
        }
    }
}
