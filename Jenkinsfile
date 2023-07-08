pipeline{
    agent any
    
    stages{
        stage('Building Image and dockerzing it'){
            agent {
                label 'montuUbuntu'
            }
            steps {
                sh 'mvn package'
                stash includes: 'target/*.jar', name: 'jarartifact' 
            }

            post{
                success{
                    archiveArtifacts artifacts: 'target/*.jar'
                    
                }        
            }
        } 

        stage('docker push'){
            agent {
                label 'montuUbuntu'
            }
            steps {
                unstash 'jarartifact'
                sh 'docker build -t montud/library-admin:latest .'
                sh 'docker push montud/library-admin:latest'
            }
        }


        stage('Kubernates deploy'){
            steps {
                bat 'C:\\Users\\coolm\\kubectl.exe apply -f deploy.ydml'
            }
        }
    }
    


}