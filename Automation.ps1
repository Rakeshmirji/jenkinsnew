#
# Automation.ps1
#

### Get present working directory and parent working directory ###
$here = Split-Path -Parent $MyInvocation.MyCommand.Path
$hereParent = Split-Path -Path $here -Parent
Write-Host "here = $here"
Write-Host "hereParent = $hereParent"



### Convert Json to array list to get values #####
# $json = (Get-Content "$here\Automation1.json" -Raw) | ConvertFrom-Json
# $Global:jsondata = $json
# #$json = (Get-Content "$here\Automation1.json" -Raw) | ConvertFrom-Json
# $Global:jsondata = $json | Format-Table -AutoSize
# write-host "the jsondata is $Global:jsondata"

$json = (Get-Content "$here\automation1.json" -Raw) | ConvertFrom-Json
$Global:jsondata = $json | ConvertTo-Json -Depth 100
#$Global:jsondata = $json
#### Read Values supplied in JSON file ######
### CWA Values ###
write-host "the json is $jsondata"
[string]$Global:StoreTypeX1 = $json.CWA_values.Store_TypeX1
[string]$Global:StoreURLX1 = $json.CWA_values.Store_URLX1
[string]$Global:StoreNameNSG = $json.CWA_Values.Store_NameNSG
[string]$Global:StoreName = $json.CWA_values.Store_NameX1
[string]$Global:StoreURLNSG = $json.CWA_Values.Store_URLNSG
[string]$Global:StoreTypeNSG = $json.CWA_values.Store_TypeNSG
#[string]$Global:TestUserName = $json.CWA_values.Test_User_Name
#[string]$Global:OnpremPass = $json.CWA_Values.Test_User_Password.plainText
[string]$Global:BuildBranchName = $json.CWA_values.BuildBranchName

write-host "the StoreTypeX1 is $Global:StoreTypeX1"
write-host "the StoreURLX1 is $Global:StoreURLX1"
write-host "the StoreNameNSG is $Global:StoreNameNSG"
write-host "the StoreName is $Global:StoreName"
write-host "the StoreURLNSG is $Global:StoreURLNSG"
write-host "the StoreTypeNSG is $Global:StoreTypeNSG"
#write-host "the TestUserName is $Global:TestUserName"
#write-host "the OnpremPass is $Global:OnpremPass"  
write-host "the BuildBranchName is $Global:BuildBranchName"
