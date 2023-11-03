pipeline {
    agent any
    environment {
        NEXUS_REGISTRY = "http://localhost:8085/repository/my-docker-reg/"
        NEXUS_CREDENTIALS = credentials('nexusrepo')
        NEXUS_URL = 'http://localhost:8081/#admin/repository'
        NEXUS_REPOSITORY = 'my-docker-reg'
    }

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
                    sh 'docker build -t devopps-docker:latest .'
                }
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexusrepo', usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')]) {
                    sh "docker login -u $NEXUS_USERNAME -p $NEXUS_PASSWORD $NEXUS_REGISTRY"
                    echo "Login Successful"
                    sh "docker push $NEXUS_REGISTRY/devopps-docker:latest"
                }
            }
        }
    }
}
