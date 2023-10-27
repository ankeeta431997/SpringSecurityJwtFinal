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
        stage('Push image to Hub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'docker-hub', variable: 'dockerpwd')]) {
                        sh "echo ${dockerpwd} | docker login -u AnkitaU --password-stdin"
                    }
                    sh 'docker push ankita/devopps-docker' 
                    echo 'Image push successful'
                }
            }
	}
       
    }
}

