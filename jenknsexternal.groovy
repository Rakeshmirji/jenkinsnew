import groovy.json.*
import com.cloudbees.groovy.cps.NonCPS
import java.text.SimpleDateFormat
def configMap = [
   fathername : params.fathername,
]
def CWA_values_map = null
CWA_values_map = readJSON text: CWA_values
//def wrappedJsonData = [CWA_values: CWA_values_map]
println "CWA_values_mappppppphhhhhhhhhhhhhhhhhhhhpppppp is ${CWA_values_map}"
//println wrappedJsonData
println "CWA_values_mappppppppppppp is ${CWA_values_map}"

configMap = [
        CWA_values: CWA_values_map
]
def myString1 = params.describe
println myString1
// Define your variable
def myVariable = "Hello, PowerShell!"
def DescribeToExecute = ["DescribeToExecute": params.DescribeToExecute]
configMap << DescribeToExecute
// Execute the PowerShell script with the variable as an argument
println "CWA_values_rrrrrrrrrrrrrrrrrrrrrrr"
println "uuuuuuuuuuuuuuuuuuuuuuuuu is ${params.CWA_values_map}"
pipeline {
    agent any
    //  environment {
    //     MY_ARRAY = "${params.MY_ARRAY}"
    // }
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
                    def myString = params.MY_ARRAY//.split(',')
                    println myString

                    def myArray = myString.split(',')

                    for (String value : myArray) {
                        println "Value: $value, Type: ${value.getClass().getName()}"
                    }
                    //def MY_ARRAY = System.getenv('MY_ARRAY')
                    // Now you can access the array in your Groovy code
                    //println MY_ARRAY
                    def jsonConfigString = JsonOutput.toJson(configMap)
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