pipeline {
    agent any

    tools {
    jdk 'Java-17'
    maven 'Maven-3.9'
    }

    environment {
        DOCKER_IMAGE = "rahulsng07/ticket-booking:${env.BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo '🔨 Building JAR using Maven...'
                sh './mvnw clean package -DskipTests'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }

        stage('Docker Build') {
            steps {
                echo '🐳 Building Docker image...'
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Docker Push') {
            steps {
                echo '📤 Pushing Docker image to Docker Hub...'
                withCredentials([usernamePassword(credentialsId: 'docker-hub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker push ${DOCKER_IMAGE}
                    '''
                }
            }
        }

        stage('Deploy to Kubernetes') {
            when {
                expression { fileExists('k8s') }
            }
            steps {
                echo '🚀 Deploying to Kubernetes...'
                withCredentials([file(credentialsId: 'kubeconfig-id', variable: 'KUBECONFIG')]) {
                    sh 'kubectl --kubeconfig=$KUBECONFIG apply -f k8s/'
                }
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed. Please check logs!'
        }
    }
}

