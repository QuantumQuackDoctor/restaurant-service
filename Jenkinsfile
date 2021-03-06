pipeline {
    agent any
    environment {
        AWS_REGION='us-east-2'
    }
    stages {
        stage('git') {
            steps {
                git branch: 'dev', url: 'https://github.com/QuantumQuackDoctor/restaurant-service.git'
            }
        }
        stage('test') {
            steps {
                sh "mvn clean test"
            }
        }
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh "mvn sonar:sonar"    
                }    
            }    
        }
        stage('Quality Gate') {
            steps {
                waitForQualityGate abortPipeline= true
            }   
        }
        stage('package') {
            steps {
                sh "mvn clean package"
            }
        }
        stage('ECR Push') {
            steps{
                script {
                    withCredentials([string(credentialsId: '143004d8-0a84-4e71-836d-24e128adc8bb', variable: 'AWS_ID')]) {
                        sh "aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${AWS_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com"
                        sh "docker build -t restaurant-service ."
                        sh "docker tag restaurant-service:latest ${AWS_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/restaurant-service:latest"
                        sh "docker push ${AWS_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/restaurant-service:latest"
                    }
                }
            }
        }
    }
    post {
        success {
            script {
                sh 'docker image prune -f -a'
            }
        }
    }
}