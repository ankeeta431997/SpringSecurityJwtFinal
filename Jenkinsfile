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
                   sh 'docker build -t ankita/devopps-docker .'
                }
            }
        }
        stage('Push image to Hub'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'docker-hub', variable: 'dockerpwd')]) {
					sh 'docker login -u AnkitaU -p ${dockerpwd}'
					}
                   sh 'docker push ankitadocker/devopps-docker'
				   echo 'Image push successful'
                }
            }
        }
       
    }
}

