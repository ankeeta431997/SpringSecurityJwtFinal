pipeline {
    agent any
    
    environment {
        NEXUS_REGISTRY = 'http://192.168.1.16:8085/repository/my-docker-reg/'
        NEXUS_CREDENTIALS = credentials('nexusrepo')
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
                     sh "echo ${NEXUS_CREDENTIALS_PSW} | docker login -u ${NEXUS_USERNAME} --password-stdin $NEXUS_REGISTRY"
						echo "Login Successful"
                    
                        sh "docker push ${DOCKER_IMAGE_NAME}"
                        sh "docker logout ${NEXUS_REGISTRY}"
                    
                    echo 'Image push to Nexus successful'
                }
            }
        }
    }
}
