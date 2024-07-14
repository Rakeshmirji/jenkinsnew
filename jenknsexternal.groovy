def pipelineParams = [
    string(name: 'REPO_URL', defaultValue: 'https://github.com/Rakeshmirji/jenkinsnew.git', description: 'Repository URL'),
    string(name: 'BRANCH', defaultValue: 'main', description: 'Branch to checkout')
]

pipeline {
    agent any

    parameters(pipelineParams)

    stages {
        stage('Checkout') {
            steps {
                git {
                    url params.REPO_URL
                    branch params.BRANCH
                }
            }
        }
    }
}