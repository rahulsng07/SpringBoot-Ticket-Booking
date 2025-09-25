pipeline {
    agent any

    tools {
        jdk 'Java-17'       // Make sure this matches the name in Jenkins Tools
        maven 'Maven-3.9'   // Make sure this matches the name in Jenkins Tools
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout code from GitHub
                git branch: 'main', 
                    url: 'https://github.com/rahulsng07/SpringBoot-Ticket-Booking.git',
                    credentialsId: 'github-pat' // your GitHub PAT credential
            }
        }

        stage('Build') {
            steps {
                // Run Maven build on Windows
                bat "\"${tool 'Maven-3.9'}\\bin\\mvn\" clean package -DskipTests"
            }
        }

        stage('Test') {
            steps {
                // Run tests if needed
                bat "\"${tool 'Maven-3.9'}\\bin\\mvn\" test"
            }
        }

        stage('Archive') {
            steps {
                // Archive the built jar file
                archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
            }
        }

        stage('Deploy (Optional)') {
            steps {
                // Example deployment step
                echo "You can add deployment commands here (Docker, Kubernetes, etc.)"
            }
        }
    }

    post {
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed. Check console output for errors.'
        }
    }
}

