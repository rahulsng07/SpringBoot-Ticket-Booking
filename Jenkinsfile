pipeline {
    agent any

    environment {
        // Use the Docker daemon exposed over TCP
        DOCKER_HOST = 'tcp://localhost:2375'
        DOCKER_IMAGE = 'rahulsng07/ticket-booking:latest'
    }

    tools {
        // Match your Jenkins-installed tools
        jdk 'Java-17'
        maven 'Maven-3.9'
    }

    stages {

        stage('Checkout SCM') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo '⚡ Building JAR using Maven...'
                bat 'mvnw.cmd clean package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Docker Build') {
            steps {
                echo '🐳 Building Docker image...'
                bat "docker build -t %DOCKER_IMAGE% ."
            }
        }

        stage('Docker Push') {
            steps {
                echo '📤 Pushing Docker image to registry...'
                bat "docker push %DOCKER_IMAGE%"
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                echo '☸️ Deploying to Kubernetes...'
                bat "kubectl apply -f k8s/"
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed. Check logs!'
        }
    }
}
