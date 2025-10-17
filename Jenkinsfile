pipeline {
    agent any

    tools {
        maven 'Maven_3.9'   // Use your configured Maven name in Jenkins
        jdk 'JDK21'         // Use your configured JDK name in Jenkins
    }

    environment {
        IMAGE_NAME = "rahulsng07/ticket-booking:latest"
        K8S_DIR = "k8s"
    }

    stages {
        stage('Checkout') {
            steps {
                echo '📥 Checking out source code...'
                checkout scm
            }
        }

        stage('Build JAR') {
            steps {
                echo '⚡ Building Spring Boot JAR using Maven...'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo '🐳 Building Docker image...'
                sh "docker build -t ${IMAGE_NAME} ."
            }
        }

        stage('Docker Push') {
            steps {
                echo '🚀 Pushing image to Docker Hub...'
                withCredentials([string(credentialsId: 'dockerhub-token', variable: 'DOCKER_PASS')]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u rahulsng07 --password-stdin
                        docker push ${IMAGE_NAME}
                    '''
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                echo '☸️ Deploying to Kubernetes...'
                sh "kubectl apply -f ${K8S_DIR}/"
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed — check logs for details.'
        }
    }
}
