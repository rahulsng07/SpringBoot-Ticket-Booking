pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "rahulsng07/ticket-app:latest"
        K8S_NAMESPACE = "ticket-system"
        KUBE_CONFIG = "~/.kube/config"
    }

    tools {
        maven 'Maven'   // Make sure your Jenkins Maven installation is named "Maven"
        jdk 'Java 21'   // Make sure your Jenkins JDK installation is named "Java 21"
    }

    stages {
        stage('Checkout SCM') {
            steps {
                git(
                    url: 'https://github.com/rahulsng07/SpringBoot-Ticket-Booking.git',
                    branch: 'main',
                    credentialsId: 'github-pat' // Your GitHub PAT credentials ID
                )
            }
        }

        stage('Build JAR') {
            steps {
                echo "Building Spring Boot JAR..."
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo "Building Docker image..."
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Docker Push') {
            steps {
                echo "Pushing Docker image to Docker Hub..."
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin"
                    sh "docker push ${DOCKER_IMAGE}"
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                echo "Deploying to Kubernetes..."
                sh "kubectl --kubeconfig=${KUBE_CONFIG} apply -f k8s/"
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
