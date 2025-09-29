pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "rahulsng07/ticket-app:latest"
        KUBE_CONFIG = "C:/Users/rahul/.kube/config" // Update if your kubeconfig is elsewhere
    }

    tools {
        maven 'Maven-3.9'   // Make sure your Jenkins Maven installation matches this name
        jdk 'Java-17'       // Use your installed JDK name in Jenkins
    }

    stages {

        stage('Checkout SCM') {
            steps {
                git url: 'https://github.com/rahulsng07/SpringBoot-Ticket-Booking.git', branch: 'main', credentialsId: 'github-pat'
            }
        }

        stage('Build JAR') {
            steps {
                echo 'Building Spring Boot JAR...'
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Docker image...'
                bat "docker build -t %DOCKER_IMAGE% ."
            }
        }

        stage('Docker Push') {
            steps {
                echo 'Pushing Docker image to Docker Hub...'
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat "echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin"
                    bat "docker push %DOCKER_IMAGE%"
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                echo 'Deploying to Kubernetes...'
                bat "kubectl --kubeconfig=%KUBE_CONFIG% apply -f k8s/"
            }
        }

    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check logs!'
        }
    }
}
