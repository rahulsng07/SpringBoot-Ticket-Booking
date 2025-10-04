pipeline {
    agent any

    // Tools configuration
    tools {
        jdk 'OpenJDK 17'          // Ensure this is configured in Jenkins
        maven 'Maven 3.9'      // Ensure this is configured in Jenkins
        git 'Default'             // Ensure Git is configured
    }

    environment {
        DOCKER_IMAGE = 'rahulsng07/ticket-booking:latest'
        K8S_NAMESPACE = 'ticket-system'
    }

    stages {
        stage('Checkout SCM') {
            steps {
                echo '🔄 Checking out source code from Git...'
                checkout([$class: 'GitSCM',
                    branches: [[name: '*/main']],
                    userRemoteConfigs: [[
                        url: 'https://github.com/rahulsng07/SpringBoot-Ticket-Booking.git',
                        credentialsId: 'github-pat'
                    ]]
                ])
            }
        }

        stage('Build JAR') {
            steps {
                echo '⚡ Building Spring Boot JAR using Maven...'
                bat 'mvnw.cmd clean package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Docker Build') {
            steps {
                echo '🐳 Building Docker image using Minikube Docker...'
                // Load Minikube Docker environment
                bat 'minikube docker-env --shell cmd > minikube-env.cmd'
                bat 'call minikube-env.cmd'
                bat "docker build -t %DOCKER_IMAGE% ."
            }
        }

        stage('Docker Push') {
            steps {
                echo '📤 Pushing Docker image to Docker Hub...'
                bat "docker login -u rahulsng07 -p <YOUR_DOCKER_PASSWORD>"
                bat "docker push %DOCKER_IMAGE%"
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                echo '📦 Deploying to Kubernetes...'
                bat "kubectl apply -f k8s/mariadb-deployment.yaml -n %K8S_NAMESPACE%"
                bat "kubectl apply -f k8s/ticket-app-deployment.yaml -n %K8S_NAMESPACE%"
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed. Check the logs!'
        }
    }
}
