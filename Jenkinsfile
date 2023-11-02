pipeline {
    agent any
	
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
                    sh 'docker build -t ankitau/devopps-docker .'
                }
            }
        }
        
      
       stage('Docker Login') {
            steps {
                echo 'Nexus Docker Repository Login'
                withCredentials([usernameColonPassword(credentialsId: 'dockerhubpwd2', variable: 'nexus')]) {
                       sh ' echo $PASS | docker login -u $ankitau --password-stdin $NEXUS_DOCKER_REPO'
                    }
                   
                }
            }
        

        stage('Docker Push') {
            steps {
                echo 'Pushing Imgaet to docker hub'
                sh 'docker push $NEXUS_DOCKER_REPO/fakeweb:$BUILD_NUMBER'
            }
        }
    }
}
