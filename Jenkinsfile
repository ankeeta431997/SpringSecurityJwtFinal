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
        stage('Push image to Hub'){
            steps{
                steps {
                script {
                    withDockerRegistry([credentialsId: 'dockerhubpwd', url: '']) {
                        sh 'docker push ankitau/devopps-docker'
                    }
                    echo 'Image push successful'
                }
            }
        }
       
    }
    }
    }
