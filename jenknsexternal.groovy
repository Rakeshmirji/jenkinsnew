import groovy.json.*
import com.cloudbees.groovy.cps.NonCPS
import java.text.SimpleDateFormat
def configMap = [
    fathername : params.fathername,
]
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                    checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Rakeshmirji/jenkinsnew.git']])
                    echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                bat 'python ./newone/pythonsamplecode.py'
                println "Testing ${configMap.fathername}"
                echo "Testing ${configMap.fathername}"
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                bat 'python ./pythonsamplecode.py'
            }
        }
    }
}