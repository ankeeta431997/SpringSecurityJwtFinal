pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
                echo 'test successful'
            }
                    stage('Pull Docker Image') {
            steps {
                script {
                    // Pull the Docker image
                    docker.image('your-image-name:tag').pull()
                }
            }
        }
        }
    }
}

