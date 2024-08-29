properties([pipelineTriggers([githubPush()])])
pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'demo-app:latest'
        EMAIL_TO = 'aijaz.ali@i-ways.net'
        gitBranch = 'main'
        gitRepoUrl = 'https://github.com/aijaz-iways/aijaz-springboot-sample.git'
    }
    stages {
        stage('Checkout Application') {
            steps {
                checkout([$class: 'GitSCM',
                branches: [[name: "$gitBranch"]],
                doGenerateSubmoduleConfigurations: false,
                submoduleCfg: [],
                userRemoteConfigs: [[url: "$gitRepoUrl", credentialsId: 'githubuser']]])
            }
        }
        stage('Build Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('build Application') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }
        stage('deployment') {
            steps {
                script {
                    sh "docker push ${DOCKER_IMAGE}"
                }
            }
        }
    }
    post {
        failure {
            emailext body: "Jenkins build url: ${BUILD_URL} \nPlease check the attached the logs \nRegards \ni-ways Devops Team",
            attachLog: 'true',
            from: 'jenkins@i-ways.net',
            subject: 'Failed build in Jenkins: $PROJECT_NAME - #$BUILD_NUMBER',
            to: "${EMAIL_TO}"
        }
        success {
            emailext body: "Jenkins build url: ${BUILD_URL}\nRegards \ni-ways Devops Team",
            to: "${EMAIL_TO}",
            subject: 'Jenkins build Successfull: $PROJECT_NAME - #$BUILD_NUMBER'
        }
    }
