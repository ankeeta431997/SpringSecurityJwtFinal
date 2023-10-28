pipeline {
    agent any
	
    stages{
        stage('Build Maven'){
            steps{
                sh 'mvn clean install'
                echo 'test successful'
            }
        }
        stage('Build docker image'){
            steps{
                script{
                   sh 'docker build -t ankitau/devopps-docker .'
                }
            }
        }
        stage('Push image to Hub') {
            steps {
                script {
                  withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
			}
                    sh 'docker push ankitau/devopps-docker' 
                    echo 'Image push successful'
                }
            }
	}
       
    }
}

