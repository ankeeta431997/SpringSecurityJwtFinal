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
		withCredentials([usernamePassword(credentialsId: 'dockerhub_pwd',   passwordVariable: 'PASSWORD')]) {
		    sh 'docker login -u ankitau -p $PASSWORD'
	        sh 'docker push ankitau/devops-docker' 
		    echo 'Image push successful'    
            	}
		}	
	}
    }
}

