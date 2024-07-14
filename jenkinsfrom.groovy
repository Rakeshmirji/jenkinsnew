pipeline {
    agent any

    parameters {
        string(name: 'name', defaultValue: 'World', description: 'Your name')
        string(name: 'address', defaultValue: 'Earth', description: 'Your address')
    }

    stages {
        stage('checkout') {
            steps {
                checkout scm(
                    Git(
                        branches: [[name: '*/main']],
                        extensions: [],
                        userRemoteConfigs: [[
                            url: 'https://github.com/Rakeshmirji/jenkinsnew.git'
                        ]]
                    )
                )
            }
        } 
        stage('build') {
            steps {
                bat 'python pythonsamplecode.py'
                echo "hello how are you ${params.name} and address ${params.address}"
            }
        }
    }
}