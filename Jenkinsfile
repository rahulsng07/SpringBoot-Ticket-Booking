pipeline {
    agent any

    tools {
        jdk 'Java-17'         // Make sure this is installed in Jenkins
        maven 'Maven-3.9'     // Make sure this is installed in Jenkins
    }

    environment {
        DOCKER_IMAGE = "rahulsng07/ticket-booking:latest"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "🔄 Checking out source code from Git..."
                checkout scm
            }
        }

        stage('Build JAR') {
            steps {
                echo "⚡ Building Spring Boot JAR using Maven..."
                sh './mvnw clean package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Docker Build') {
            steps {
                echo "🐳 Building Docker image..."
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Docker Push') {
            steps {
                echo "🚀 Pushing Docker image to Docker Hub..."
                sh "docker push ${DOCKER_IMAGE}"
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                echo "📦 Deploying to Kubernetes..."
                sh "kubectl apply -f k8s/"
            }
        }
    }

    post {
        success {
            echo "✅ Pipeline completed successfully!"
        }
        failure {
            echo "❌ Pipeline failed! Check logs."
        }
    }
}
