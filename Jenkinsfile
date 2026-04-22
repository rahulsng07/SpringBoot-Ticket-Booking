# Pipline script for maven , sonarqube, nexus, Tomcat.
pipeline {
    agent any

    stages {
        stage('git-clone') {
            steps {
                git branch: 'main', url: 'https://github.com/rahulsng07/SpringBoot-Ticket-Booking.git'
            }
        }
        stage('maven') {
            steps {
                sh 'mvn package'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(installationName: 'sonarqube', credentialsId: 'Jenkins-sonarqube.token') {
                    sh 'mvn verify sonar:sonar'
                }
            }
        }
            stage('Nexus') { 
            steps {
                sh 'find . -name "*.war" -o -name "*.jar"'
                nexusArtifactUploader artifacts: [[
                    artifactId: 'ticket-booking-system', 
                    classifier: '', 
                    file: 'target/ticket-booking.war', 
                    type: 'jar'
                ]], 
                credentialsId: 'nexus-tokan', 
                groupId: 'com.online_ticket_booking_system', 
                nexusUrl: '13.233.239.189:8081', 
                nexusVersion: 'nexus3', 
                protocol: 'http', 
                repository: 'Ticket.Booking', 
                version: '3.3.2'
            }
        } 
        stage('Deploy to Tomcat'){
            steps{
                sshagent(['tomcat']){
                    sh 'scp -o StrictHostKeyChecking=no target/ticket-booking.war ubuntu@ip-172-31-9-199:~/apache-tomcat-11.0.21/webapps/'
                }
                
            }
        }
    }
}
