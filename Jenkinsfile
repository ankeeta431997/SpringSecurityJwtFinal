pipeline {
    agent any
    
    environment {
        NEXUS_REGISTRY = 'http://192.168.1.16:8085/my-docker-reg/'
        NEXUS_CREDENTIALS = credentials('nexusrepo')
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
	      // stage('Push Image to Hub') {
       //      steps {
       //          script {
       //              withDockerRegistry([credentialsId: 'dockerhubpwd2', url: '']) {
       //                  sh 'docker push ${DOCKER_IMAGE_NAME}'
       //              }
       //              echo 'Image push successful'
       //          }
       //      	}
	      // }
        
        stage('Push Image to Nexus') {
            steps {
                script {
		    sh "echo ${NEXUS_PASSWORD} | docker login -u ${NEXUS_USERNAME} --password-stdin $NEXUS_REGISTRY"
                   // sh "docker login -u $NEXUS_USERNAME -p $NEXUS_PASSWORD $NEXUS_REGISTRY"
					echo 'Login Successful'
			// sh "docker tag ${DOCKER_IMAGE_NAME} securityimage/${DOCKER_IMAGE_NAME}"
   //         		 sh "docker push securityimage/${DOCKER_IMAGE_NAME}"
                  sh "docker tag ${DOCKER_IMAGE_NAME} ${NEXUS_REGISTRY}${DOCKER_IMAGE_NAME}"
           	 sh "docker push ${NEXUS_REGISTRY}${DOCKER_IMAGE_NAME}"
                   // sh 'docker logout ${NEXUS_REGISTRY}'
                    echo 'Image push to Nexus successful'
                }
            }
        }
    }
}
