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
                    sh 'docker build -t devopps-docker:latest .'
                }
            }
        }
        
      
       stage('Docker Login') {
            steps {
                echo 'Nexus Docker Repository Login'
                withCredentials([usernamePassword(credentialsId: 'dockerhubpwd2', passwordVariable: 'test', usernameVariable: 'nexus')]) {
                       sh ' echo $PASS | docker login -u $ankitau --password-Unoveo@12 $NEXUS_DOCKER_REPO'
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
