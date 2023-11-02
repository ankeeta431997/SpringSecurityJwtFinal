pipeline {
    agent any
	environment{
		NEXUS_REGISTRY = "http://localhost:8081/repository/my-docker-reg/"
		NEXUS_CREDENTIALS = credentials ('nexus-credentials')
		NEXUS_USERNAME = "${NEXUS_CREDENTIALS_USR}"
		NEXUS_PASSWORD = "${NEXUS_CREDENTIALS_PSW}"
		NEXUS_URL = 'http://localhost:8081/#admin/repository'
		NEXUS_REPOSITORY ='my-docker-reg'
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
		    sh 'docker tag devopps-docker:latest $NEXUS_REGISTRY/devopps-docker:latest'
		    sh "docker login -u $NEXUS_USERNAME -p $NEXUS_PASSWORD $NEXUS_REGISTRY"
		    sh "docker push $NEXUS_REGISTRY/devopps-docker:latest"
                    }
       }  
    }
}
