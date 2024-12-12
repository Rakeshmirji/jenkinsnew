# #
# # Automation.ps1
# #

# ### Get present working directory and parent working directory ###
# $here = Split-Path -Parent $MyInvocation.MyCommand.Path
# $hereParent = Split-Path -Path $here -Parent
# Write-Host "here = $here"
# Write-Host "hereParent = $hereParent"



# ### Convert Json to array list to get values #####
# # $json = (Get-Content "$here\Automation1.json" -Raw) | ConvertFrom-Json
# # $Global:jsondata = $json
# # #$json = (Get-Content "$here\Automation1.json" -Raw) | ConvertFrom-Json
# # $Global:jsondata = $json | Format-Table -AutoSize
# # write-host "the jsondata is $Global:jsondata"

# $json = (Get-Content "$here\automation1.json" -Raw) | ConvertFrom-Json
# $Global:jsondata = $json | ConvertTo-Json -Depth 100
# #$Global:jsondata = $json
# #### Read Values supplied in JSON file ######
# ### CWA Values ###
# write-host "the json is $jsondata"
# [string]$Global:StoreTypeX1 = $json.CWA_values.Store_TypeX1
# [string]$Global:StoreURLX1 = $json.CWA_values.Store_URLX1
# [string]$Global:StoreNameNSG = $json.CWA_Values.Store_NameNSG
# [string]$Global:StoreName = $json.CWA_values.Store_NameX1
# [string]$Global:StoreURLNSG = $json.CWA_Values.Store_URLNSG
# [string]$Global:StoreTypeNSG = $json.CWA_values.Store_TypeNSG
# #[string]$Global:TestUserName = $json.CWA_values.Test_User_Name
# #[string]$Global:OnpremPass = $json.CWA_Values.Test_User_Password.plainText
# [string]$Global:BuildBranchName = $json.CWA_values.BuildBranchName

# write-host "the StoreTypeX1 is $Global:StoreTypeX1"
# write-host "the StoreURLX1 is $Global:StoreURLX1"
# write-host "the StoreNameNSG is $Global:StoreNameNSG"
# write-host "the StoreName is $Global:StoreName"
# write-host "the StoreURLNSG is $Global:StoreURLNSG"
# write-host "the StoreTypeNSG is $Global:StoreTypeNSG"
# #write-host "the TestUserName is $Global:TestUserName"
# #write-host "the OnpremPass is $Global:OnpremPass"  
# write-host "the BuildBranchName is $Global:BuildBranchName"
# Automation.ps1

### Get present working directory and parent working directory ###
# Automation.ps1

### Get present working directory and parent working directory ###
# Automation.ps1

### Get present working directory and parent working directory ###
$here = Split-Path -Parent $MyInvocation.MyCommand.Path
$hereParent = Split-Path -Path $here -Parent
Write-Host "here = $here"
Write-Host "hereParent = $hereParent"

### Convert Json to array list to get values #####
$json = (Get-Content "$here\automation1.json" -Raw) | ConvertFrom-Json
$Global:jsondata = $json

#### Read Values supplied in JSON file ######
### CWA Values ###
Write-Host "the json is "
$json | ConvertTo-Json -Depth 10 | Write-Host
[string]$Global:StoreTypeX1 = $json.CWA_values.ResourceName
[string]$Global:StoreURLX1 = $json.CWA_values.app_name
[string]$Global:StoreNameNSG = $json.CWA_values.ddc_hostname
[string]$Global:StoreName = $json.CWA_values.pathToModule
[string]$Global:StoreURLNSG = $json.CWA_values.ClientMachineUsername
[string]$Global:StoreTypeNSG = $json.CWA_values.Store_TypeNSG
#[string]$Global:TestUserName = $json.CWA_values.Test_User_Name
#[string]$Global:OnpremPass = $json.CWA_values.Test_User_Password.plainText
[string]$Global:BuildBranchName = $json.CWA_values.BuildBranchName

Write-Host "the StoreTypeX1 is $Global:StoreTypeX1"
Write-Host "the StoreURLX1 is $Global:StoreURLX1"
Write-Host "the StoreNameNSG is $Global:StoreNameNSG"
Write-Host "the StoreName is $Global:StoreName"
Write-Host "the StoreURLNSG is $Global:StoreURLNSG"
Write-Host "the StoreTypeNSffffffffffffffffffffffffffffffG is $Global:StoreTypeNSG"
# Write-Host "the TestUserName is $Global:TestUserName"
# Write-Host "the OnpremPass is $Global:OnpremPass"
Write-Host "the BuildBranchName is $Global:BuildBranchName"
$myVariable = Get-Content -Path "variable.txt"

# Print the variable
Write-Host "The variable from Groovy is: $myVariable"

# Print the variable
Write-Host "The variable from Groovy is: $myString1"
$DescribeBlockToExecute = $json.DescribeToExecute
Write-Host "Describe block to execute $DescribeBlockToExecute"
[string]$global:App_Paint_Name = if ($null -eq $json.Apps.Paint.Name) { $true } Else { $json.Apps.Paint.Name }
[string]$global:App_Notepad_Name = if ($null -eq $json.Apps.Notepad.Name) { $true } Else { $json.Apps.Notepad.Name }
[string]$global:App_Calculator_Name = if ($null -eq $json.Apps.Calculator.Name) { $true } Else { $json.Apps.Calculator.Name }
[string]$global:App_Desktop1_Name = if ($null -eq $json.Apps.Desktop1.Name) { $true } Else { $json.Apps.Desktop1.Name }
[string]$global:App_Desktop2_Name = if ($null -eq $json.Apps.Desktop2.Name) { $true } Else { $json.Apps.Desktop2.Name }
[string]$global:App_Desktop3_Name = if ($null -eq $json.Apps.Desktop3.Name) { $true } Else { $json.Apps.Desktop3.Name }
[string]$global:App_Desktop4_Name = if ($null -eq $json.Apps.Desktop4.Name) { $true } Else { $json.Apps.Desktop4.Name }






$ClientMachineDomainName=$domainName
Write-Host "App_Paint_Name: $global:App_Paint_Name"
Write-Host "App_Notepad_Name: $global:App_Notepad_Name"
Write-Host "App_Calculator_Name: $global:App_Calculator_Name"
Write-Host "App_Desktop1_Name: $global:App_Desktop1_Name"
Write-Host "App_Desktop2_Name: $global:App_Desktop2_Name"
Write-Host "App_Desktop3_Name: $global:App_Desktop3_Name"
Write-Host "App_Desktop4_Name: $global:App_Desktop4_Name"
#for finding the version of OS