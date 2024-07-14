def pipelineParams = [
    string(name: 'REPO_URL', defaultValue: 'https://github.com/Rakeshmirji/jenkinsnew.git', description: 'Repository URL'),
    string(name: 'BRANCH', defaultValue: 'main', description: 'Branch to checkout')
]

pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                git {
                    checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Rakeshmirji/jenkinsnew.git']])
                    echo 'Building..'
                }
                
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}