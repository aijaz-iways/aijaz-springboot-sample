pipeline {
    agent { node { label 'master' } }

    environment {
        DOCKER_IMAGE = "aijazalipwr/testingalirepos:demo-app"
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
                sh 'mvn clean package -DskipTests'
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
