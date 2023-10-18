pipeline {  
    agent any 
    
    stages {  
      
             stage ('Build') {  
                   steps{
            	        bat label: '', script: 'mvn clean'
            	        bat label: '', script: 'mvn package'
            	        echo "build successful";
		   } 
	     }          
       
    }

}
