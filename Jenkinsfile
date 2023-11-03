pipeline {
    agent any
    
    environment {
        NEXUS_REGISTRY = 'http://192.168.1.16:8086/repository/my-docker-reg/'
        NEXUS_CREDENTIALS_ID = 'nexusrepo'
        DOCKER_IMAGE_NAME = 'ankitau/devopps-docker' 
    }
	
    stages {
        stage('Build Maven') {
            steps {
                sh 'mvn clean install'
                echo 'Maven build successful'
            }
        }
        
        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_IMAGE_NAME} ."
                }
            }
        }
        
        stage('Push Image to Nexus') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: NEXUS_CREDENTIALS_ID, usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')]) {
                        def nexusDockerCmd = "docker login -u ${NEXUS_USERNAME} -p ${NEXUS_PASSWORD} ${NEXUS_REGISTRY}"
                        sh nexusDockerCmd
                        sh "docker push ${DOCKER_IMAGE_NAME}"
                        sh "docker logout ${NEXUS_REGISTRY}"
                    }
                    echo 'Image push to Nexus successful'
                }
            }
        }
    }
}
