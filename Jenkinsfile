pipeline {  
    agent any 
    
    stages {  
      
             stage ('Build') {  
                  steps{
                    bat label: '', script: 'clean install'
                    echo "test successful";
                } 
	     }          
       
    }

}
