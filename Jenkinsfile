pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'Java-17'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/rahulsng07/SpringBoot-Ticket-Booking.git',
                    credentialsId: 'github-pat'
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

    post {
        success {
            echo "✅ Build and tests completed successfully!"
        }
        failure {
            echo "❌ Build or test failed!"
        }
    }
}
