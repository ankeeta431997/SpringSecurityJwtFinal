pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
                echo 'test successful'
            }
            }
        stage('Build docker image') {
            steps {
                script{
                    sh 'docker build -t ankita/devopps-docker .'
                }
            }
            }
         stage('push image to hub') {
            steps {
                script{
                    withCredentials([string(credentialsId: 'docker-hub', variable: 'dockerpwd')]) {
                     sh 'docker login -u AnkitaU -p${dockerpwd}'
                     sh 'docker push AnkitaU/devopps-docker'
}
                }
            }
            }
        }
        }
    


