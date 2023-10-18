pipeline {  
    agent any 
    
    stages {  
      
             stage ('Build') {  
                   steps{
            	       bat label: '', script: 'mvn clean install'
                    echo "test successful";
		   } 
	     }          
       
    }

}
