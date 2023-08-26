pipeline {  
    agent any 
    tools{
	maven 'Maven'
	}
    stages {  
            stage ('Compile') {  
                  steps{
                    bat label: '', script: 'mvn compile'
                    echo "test successful";
                    
                } 
            }
            stage ('Build') {  
                  steps{
                    bat label: '', script: 'mvn clean'
                    bat label: '', script: 'mvn package'
                    echo "build successful";
                    
                } 
            }
             stage ('Test') {  
                  steps{
                    bat label: '', script: 'mvn test'
                    echo "test successful";
                } 
            }
            
        stage ('Deploy') {
            steps{
            deploy adapters: [tomcat9(credentialsId: 'tomcatCredential', path: '', url: 'http://localhost:8080/')], contextPath: 'jenkins', onFailure: false, war: '**/*.war'
             echo "Deploy successful";
            }
        }
        stage ('Monitor') { 
           steps{ 
             echo "Now you can monitor!";
           }
        }
    }
	 post {
        always {
            script {
               def projectName = JOB_NAME.tokenize('/').last()
                def buildNumber = currentBuild.number
                def buildStatus = currentBuild.result ?: 'UNKNOWN'

                def emailBody = """$projectName - Build # $buildNumber - $buildStatus:

Check console output at ${env.BUILD_URL} to view the results.

Note: This is an auto-generated email. Do not reply to this email.

Thanks,
Jenkins
"""

                emailext (
                    body: emailBody,
                    to:'ankeeta431997@gmail.com',
                    subject: "$projectName - Build # $buildNumber - $buildStatus"
                )
            }
        }
    }
}
