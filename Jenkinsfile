pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "rahulsng07/ticket-app:latest"
        K8S_NAMESPACE = "ticket-system"
        KUBE_CONFIG = "~/.kube/config"
    }

    tools {
        maven 'Maven-3.9'   // use the exact Maven name from Jenkins
        jdk 'Java-17'       // use the exact JDK name from Jenkins
    }

    stages {
        stage('Checkout SCM') {
            steps {
                git(
                    url: 'https://github.com/rahulsng07/SpringBoot-Ticket-Booking.git',
                    branch: 'main',
                    credentialsId: 'github-pat'
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
