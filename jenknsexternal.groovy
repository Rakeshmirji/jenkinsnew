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
                script{
                    def jsonConfigString = JsonOutput.toJson(configMap)
                    // Convert JSON string to JSON Object
                    def jsonConfig = readJSON text: jsonConfigString
                    // Save config as JSON in correct dir
                    def configPath = "${env.WORKSPACE}/newone/automation1.json"
                    writeJSON(file: configPath, json: jsonConfig, pretty: 4)
                
                }
                bat 'python ./newone/pythonsamplecode.py'
                powershell -File "${env.WORKSPACE}/newone/Automation.ps1"
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