pipeline{
    agent any
    
    stages{
        stage('Building Image and dockerzing it'){
            agent {
                label 'montuUbuntu'
            }
            steps {
                sh 'mvn package -DskipTests'
            }

        } 

        stage('docker push'){
            agent {
                label 'montuUbuntu'
            }
            steps {
                sh 'docker build -t montud/library-admin:latest .'
                sh 'docker push montud/library-admin:latest'
            }
        }


        stage('Kubernates deploy'){
            
            agent{
                label 'microkube'
            }

          
            steps {
                sh 'microk8s kubectl apply -f deploy.yml'
            }
        }
    }
    


}