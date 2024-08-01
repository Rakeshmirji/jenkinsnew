import groovy.json.*
import com.cloudbees.groovy.cps.NonCPS
import java.text.SimpleDateFormat

def flow_properties_map = readJSON text: flow_properties
def CWA_values_map = readJSON text: CWA_values
def app_config_map = readJSON text: app_config
def auto_update_values_map = readJSON text: auto_update_values
def onpremvalues_map = readJSON text: onpremvalues
def grafeas_values_map = readJSON text: grafeas
def slack_map = readJSON text: slack_value
//def CrashDump_values_map = readJSON text: CrashDump_values
def SnapshotRevert = params.RevertSnapshot
def SnapshotRevertJob = params.SnapshotJobName
def suiteName = params.Test_Suite_Name
def customPortal_value_map
try {
    customPortal_value_map = readJSON text: customPortal
}
catch (MissingPropertyException e) {
    customPortal_value_map = ''
}
  
withCredentials([usernamePassword(credentialsId: 'username_password_svcacct_rfwin', usernameVariable: 'USERNAME',passwordVariable: 'PASSWORD')])
{
	CWA_values_map["downloaduser"] = "${USERNAME}"
	CWA_values_map["downloadpword"] = "${PASSWORD}"
}  
  
params.each { paramKey, paramVal ->
    if (paramKey.contains('username') || paramKey.contains('password') || paramKey.contains('secret')) {
        flow_properties_map[paramKey] = paramVal
    }

    if (paramKey.contains('BuildBranchName') || paramKey.contains('databasepword') || paramKey.contains('JenkinsPword') || paramKey.contains('Cosmoso_Db_Masterkey') || paramKey.contains('JenkinsToken') || paramKey.contains('UploadJsonDataToCosmosDB') || paramKey.contains('CrashDumpLocation') || paramKey.contains('BranchName') || paramKey.contains('BuildNum') || paramKey.contains('JenkinsBuildNoDownloadToken') || paramKey.contains('subtestingsuite') ||  paramKey.contains('Desktop_Name') || paramKey.contains('Apps') || paramKey.contains('ws_url') || paramKey.contains('idp_type') || paramKey.contains('vdx_title')  || paramKey.contains('app_title') || paramKey.contains('newinstaller')) {
        CWA_values_map[paramKey] = paramVal
    }

    if (paramKey.contains('DeliveryPeriod') || paramKey.contains('ReleaseChannel') || paramKey.contains('Stream') || paramKey.contains('VersionBumpUp') || paramKey.contains('CWAUpdatePreviousVersion')) {
        auto_update_values_map[paramKey] = paramVal
    }

    if (paramKey.contains('gServerURL') || paramKey.contains('gUserName') || paramKey.contains('gPassword') || paramKey.contains('Userpermission') || paramKey.contains('PipelinePath')) {
        grafeas_values_map[paramKey] = paramVal
    }

    if (paramKey.contains('SlackURL')) {
        slack_map[paramKey] = paramVal
    }
}

CWA_values_map['runcount'] = 1
// def myString1 = params.describe_bocks
// println myString1
def configMap = [
    flow_properties: flow_properties_map,
    CWA_values: CWA_values_map,
    app_config: app_config_map,
    auto_update_values: auto_update_values_map,
    onpremvalues: onpremvalues_map,
    grafeas: grafeas_values_map,
    customPortal: customPortal_value_map,
    slack_value:slack_map
    //CrashDump_values: CrashDump_values_map
]

// Convert map object to JSON String
def jsonConfigString = JsonOutput.toJson(configMap)

// Convert JSON string to JSON Object
def jsonConfig = readJSON text: jsonConfigString

pipeline {
    stages {
            stage('Test Execution') {
                steps {
                    script {
                        def myString = params.describe_bocks
                        println myString
                        def myArray = myString.split(',')
                    
                        for (int i = 0; i < myArray.size(); i++) {
                                def value = myArray[i]
                                println "Value: $value, Type: ${value.getClass().getName()}"
                                script {
                                    try {
                                        // Save config as JSON in correct dir
                                        if (i == 1) { 
                                            CWA_values_map['downloadBuilds'] = true
                                        } else {
                                            CWA_values_map['downloadBuilds'] = false
                                        }
                                        // def jsonConfigString = JsonOutput.toJson(configMap)
                                        // // Convert JSON string to JSON Object
                                        // def jsonConfig = readJSON text: jsonConfigString
                                        // // Save config as JSON in correct dir
                                        // def configPath = "${env.WORKSPACE}/automation1.json"
                                        // writeJSON(file: configPath, json: jsonConfig, pretty: 4)
                                        nextDescribeToExecute = ["DescribeToExecute": myArray[i]]
                                        configMap << nextDescribeToExecute                  
                                        
                                        
                                        
                                        // CWA_values_map['subtestingsuite'] = 'stress-before-restart'
                                        // configMap['CWA_values'] = CWA_values_map
                                        jsonConfigString = JsonOutput.toJson(configMap)
                                        jsonConfig = readJSON text: jsonConfigString
                                        configPath = "${env.WORKSPACE}/Automation/CWA_Automation/CWA_Automation/flows/config/Automation.json"
                                        writeJSON(file: configPath, json: jsonConfig, pretty: 4)
                                        if (i == 1) {
                                            downloadLibraries = "${env.WORKSPACE}/Automation/CWA_Automation/CWA_Automation/Common/AutomationLibs.ps1"
                                            downloadLibrariesResult = powershell(returnStatus: true, script: downloadLibraries)
                                            //downloadInstallCWA = "${env.WORKSPACE}/Automation/CWA_Automation/CWA_Automation/Common/Uninstall-Download-Install.ps1"
                                            //downloadInstallCWAResult = powershell(returnStatus: true, script: downloadInstallCWA)
                                        }
                                        powershellPath = "${env.WORKSPACE}/Automation/CWA_Automation/CWA_Automation/flows/Automation.ps1"
                                        result = powershell(returnStatus: true, script: powershellPath)

                                        // nextDescribeToExecute = ["DescribeToExecute": myArray[i]]
                                        // configMap << nextDescribeToExecute

                                        jsonConfigString = JsonOutput.toJson(configMap)
                                        jsonConfig = readJSON text: jsonConfigString
                                        // Save config as JSON in correct dir
                                        writeJSON(file: configPath, json: jsonConfig, pretty: 4)
                                        bat 'shutdown -r -f'
                                        // if (result != 0) {
                                        //     stage1Passed = false
                                        // }
                                        // if (result == 0) {
                                        //     stage1Passed = true
                                        //     bat 'shutdown -r -f'
                                        // }
                                    } catch (Exception e) {
                                        println(e.toString())
                                        println 'Failed to execute describe block'
                                    }
                                }
                                // Check for node is shutdown
                                echo "Waiting for VM to reboot ${env.NODE_NAME}"
                                def count = 1
                                while (count <= 50) {
                                    sleep(5)
                                    if (!(nodesByLabel("${env.NODE_NAME}").size() > 0)) {
                                        break
                                    }
                                    count++
                                }

                                echo "Waiting for VM to connect"
                                count = 1
                                while (count <= 100) {
                                    sleep(5)
                                    if (nodesByLabel(node_name).size() > 0) {
                                        break
                                    }
                                    count++
                                }
                        }
                            archiveArtifacts artifacts: "Automation/CWA_Automation/CWA_Automation/flows/reports/*.*"
                        }
                    }
                }
                
            }
            post {
                always {
                    script {
                        if (SnapshotRevert.equalsIgnoreCase("Yes")) {
                            if ("${env.NODE_NAME}".contains("Azure")) {
                                println "Revert Snapshot VM in Azure"
                                try{
                                    println "NODE_NAME -- ${env.NODE_NAME}"
                                    build job: "BVT_JOBS/${SnapshotRevertJob}",
                                    parameters: [string(name: 'vmname', value: "${env.NODE_NAME}"), string(name: "vmin", value: "Azure")],
                                    wait: true
                                }catch(Exception e){
                                    println(e.toString())
                                    println "Failed to submit Snapshot job"
                                }
                            } else {
                                println "Revert Snapshot in Xencenter"
                                try{
                                    println "NODE_NAME -- ${env.NODE_NAME}"
                                    build job: "BVT_JOBS/${SnapshotRevertJob}",
                                    parameters: [string(name: 'vmname', value: "${env.NODE_NAME}"), string(name: "vmin", value: "Xencenter")],
                                    wait: true
                                }catch(Exception e){
                                    println(e.toString())
                                    println "Failed to submit Snapshot job"
                                }
                            }
                            echo "Waiting for VM to disconnect"
                            def count = 1
                            while (count <= 50) {
                                sleep(5)
                                if (!(nodesByLabel("${env.NODE_NAME}").size() > 0)) {
                                    break
                                }
                                count++
                            }

                            echo "Waiting for VM to connect"
                            count = 1
                            while (count <= 100) {
                                sleep(5)
                                if (nodesByLabel("${env.NODE_NAME}").size() > 0) {
                                    break
                                }
                                count++
                            }
                        } else {
                            println "Revert Snapshot is set to No"
                        }
                    } 
                }
            }
    }

   