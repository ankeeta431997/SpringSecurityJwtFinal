pipeline {
    agent any
    
    environment {
        NEXUS_REGISTRY = 'http://192.168.1.16:8085/repository/my-docker-reg/'
        NEXUS_CREDENTIALS = credentials('nexusrepo1')
		NEXUS_USERNAME = "${NEXUS_CREDENTIALS_USR}"
        NEXUS_PASSWORD = "${NEXUS_CREDENTIALS_PSW}"
		
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
                    sh 'docker build -t ${DOCKER_IMAGE_NAME} .'
                }
            }
        }
	      stage('Push Image to Hub') {
            steps {
                script {
                    withDockerRegistry([credentialsId: 'dockerhubpwd2', url: '']) {
                        sh 'docker push ${DOCKER_IMAGE_NAME}'
                    }
                    echo 'Image push successful'
                }
            	}
	      }
        
        stage('Push Image to Nexus') {
            steps {
                script {
                    sh "docker login -u $NEXUS_USERNAME -p $NEXUS_PASSWORD $NEXUS_REGISTRY"
					echo 'Login Successful'
                    sh 'docker push ${DOCKER_IMAGE_NAME}'
		   // sh 'docker push ${NEXUS_REGISTRY}/${DOCKER_IMAGE_NAME}'
                    sh 'docker logout ${NEXUS_REGISTRY}'
                    echo 'Image push to Nexus successful'
                }
            }
        }
    }
}
