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
                   sh 'docker build -t ankitau/devops-docker .'
                }
            }
        }
        stage('Push image to Hub') {
            steps {
                script {
                  withCredentials([string(credentialsId: 'ankitau', variable: 'dockerhub_pwd')]) {
			}
                    sh 'docker push ankitau/devops-docker' 
                    echo 'Image push successful'
                }
            }
	}
       
    }
}

