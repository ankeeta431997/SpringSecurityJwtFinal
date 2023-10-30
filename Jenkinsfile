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
        
        stage('Push Image to Hub') {
            steps {
                script {
                    withDockerRegistry([credentialsId: 'dockerhubpwd2', url: '']) {
                        sh 'docker push ankitau/devopps-docker'
                    }
                    echo 'Image push successful'
                }
            }
        }
    }
}
