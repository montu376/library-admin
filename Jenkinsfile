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
                sh 'docker build -t 192.168.0.121:5000/montud/library-admin:latest .'
                sh 'docker push 192.168.0.121:5000/montud/library-admin:latest'
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