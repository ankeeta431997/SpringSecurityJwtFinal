pipeline {  
    agent any 
    
    stages {  
      
             stage ('Build') {  
                   steps{
            	       sh label: '', script: 'mvn clean install'
                    echo "test successful";
		   } 
	     }          
       
    }

}
