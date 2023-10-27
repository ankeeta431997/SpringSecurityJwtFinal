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
        }
        }
    


