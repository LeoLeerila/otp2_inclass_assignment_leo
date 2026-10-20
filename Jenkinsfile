pipeline {
    agent any

    tools {
        maven 'maven'
    }

    environment {
        //PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"
        DOCKERHUB_CREDENTIALS_ID = 'Docker_pat'
        DOCKERHUB_REPO = 'leoleerila/otp1'
        DOCKER_IMAGE_TAG = 'v1'
    }

    stages {
        stage ('check'){
            steps{
                git branch: 'main', url: 'https://github.com/LeoLeerila/otp1_inclass1_assignment_leo.git'
            }
        }
        stage ('build'){
            steps{
                sh 'mvn clean install'
            }
        }

        stage('test') {
            steps{
                sh 'mvn test'
            }
        }
        stage('jacoco'){
            steps{
                jacoco()
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }
    }
}