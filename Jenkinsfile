pipeline {
    agent {
        docker {
            image 'maven:3.9.2-amazoncorretto-17'
            label 'master'
            args '-v /~jenkins/stage_server:/~jenkins/stage_server'
        }
    }

    environment {
        DOCKER_IMAGE = "aijazalipwr/testingalirepos:demo"
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Pull code from GitHub (or any Git repository)
                git branch: 'main', url: 'https://github.com/aijaz-iways/aijaz-springboot-sample.git'
            }
        }

        stage('Build Maven') {
            steps {
                sh '''
                mvn clean package --no-transfer-progress -DskipTests
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image using the Dockerfile
                    def app = docker.build("${DOCKER_IMAGE}:${env.BUILD_ID}")
                }
            }
        }

    }

}
