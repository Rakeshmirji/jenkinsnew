import groovy.json.*
import com.cloudbees.groovy.cps.NonCPS
import java.text.SimpleDateFormat
def configMap = [
   fathername : params.fathername,
]
def CWA_values_map = null
CWA_values_map = readJSON text: CWA_values
println "CWA_values_mappppppppppppp is ${CWA_values_map}"

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
                    def jsonConfigString = JsonOutput.toJson(CWA_values_map)
                    // Convert JSON string to JSON Object
                    def jsonConfig = readJSON text: jsonConfigString
                    // Save config as JSON in correct dir
                    def configPath = "${env.WORKSPACE}/automation1.json"
                    writeJSON(file: configPath, json: jsonConfig, pretty: 4)
                    powershellPath = "${env.WORKSPACE}/Automation.ps1"
                    result = powershell(powershellPath)
                    //CWA_values_map = readJSON text: CWA_values
                    //println("Cwa_Values_onprem is ----  ${params.CWA_values_map}")
                
                }
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