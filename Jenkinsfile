pipeline {
  agent any

  environment {
    DOCKER_REGISTRY = "docker.io/${DOCKER_USER}"
    APP_NAME = "ticket-booking"
    K8S_NAMESPACE = "ticket-system"
    DEPLOYMENT_NAME = "ticket-app"
    CONTAINER_NAME = "ticket-app"
    DOCKER_CRED = 'docker-hub-cred'
    KUBECONFIG_CRED = 'kubeconfig-cred'
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
        script {
          GIT_SHORT = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
          TAG = "${env.BUILD_NUMBER}-${GIT_SHORT}"
          IMAGE = "${env.DOCKER_REGISTRY}/${env.APP_NAME}:${TAG}"
          echo "Image will be: ${IMAGE}"
        }
      }
    }

    stage('Build JAR') {
      steps {
        sh './mvnw clean package -DskipTests'
      }
    }

    stage('Docker Build') {
      steps {
        sh "docker build -t ${IMAGE} ."
      }
    }

    stage('Docker Push') {
      steps {
        withCredentials([usernamePassword(credentialsId: DOCKER_CRED, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
          sh '''
            echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
            docker push ${IMAGE}
            docker logout
          '''
        }
      }
    }

    stage('Deploy to Kubernetes') {
      steps {
        withCredentials([file(credentialsId: KUBECONFIG_CRED, variable: 'KUBECONFIG_FILE')]) {
          sh '''
            export KUBECONFIG="$KUBECONFIG_FILE"
            kubectl apply -f k8s/namespace.yaml || true
            kubectl apply -f k8s/
            kubectl -n ${K8S_NAMESPACE} set image deployment/${DEPLOYMENT_NAME} ${CONTAINER_NAME}=${IMAGE} --record
            kubectl -n ${K8S_NAMESPACE} rollout status deployment/${DEPLOYMENT_NAME} --timeout=120s
          '''
        }
      }
    }
  }

  post {
    success {
      echo "Deployment successful: ${IMAGE}"
    }
    failure {
      echo "Build or deploy failed."
    }
  }
}
