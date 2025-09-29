pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "ticket-app:latest"
        K8S_NAMESPACE = "ticket-system"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/rahulsng07/SpringBoot-Ticket-Booking.git'
            }
        }

        stage('Build JAR') {
            steps {
                echo 'Building Spring Boot JAR...'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo "Building Docker image ${DOCKER_IMAGE}..."
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Docker Push') {
            steps {
                echo "Pushing Docker image ${DOCKER_IMAGE}..."
                // Make sure your Docker is logged in if using a registry
                sh "docker push ${DOCKER_IMAGE}"
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                echo "Deploying to Kubernetes namespace ${K8S_NAMESPACE}..."
                sh "kubectl apply -f k8s/ -n ${K8S_NAMESPACE}"
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
