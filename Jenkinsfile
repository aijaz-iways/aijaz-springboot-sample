pipeline {
    agent {
        docker {
            image 'maven:3.8.5-openjdk-17'
            label 'master'
            args '-v /~jenkins/stage_server:/~jenkins/stage_server'
        }
    }

    environment {
        DOCKER_IMAGE = "aijazalipwr/testingalirepos:demo-app"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/aijaz-iways/aijaz-springboot-sample.git'
            }
        }

        stage('Build Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def app = docker.build("${DOCKER_IMAGE}:${env.BUILD_ID}")
                }
            }
        }

    }

}
