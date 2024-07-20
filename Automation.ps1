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

$json = (Get-Content "$here\Automation1.json" -Raw) | ConvertFrom-Json
$Global:jsondata = $json | ConvertTo-Json -Depth 100
Write-Host "The JSON is:"

Write-Host $Global:jsondata
# #### Read Values supplied in JSON file ######
# ### CWA Values ###

[string]$Global:StoreTypeX1 = $jsondata.CWA_values.Store_TypeX1
[string]$Global:StoreURLX1 = $jsondata.CWA_values.Store_URLX1
[string]$Global:StoreNameNSG = $jsondata.CWA_Values.Store_NameNSG
[string]$Global:StoreName = $jsondata.CWA_values.Store_NameX1
[string]$Global:StoreURLNSG = $jsondata.CWA_Values.ResourceName
[string]$Global:StoreTypeNSG = $jsondata.CWA_values.Store_TypeNSG
#[string]$Global:TestUserName = $json.CWA_values.Test_User_Name
#[string]$Global:OnpremPass = $json.CWA_Values.Test_User_Password.plainText
[string]$Global:StoreTypeX2 = $jsondata.CWA_values.Test_Suite_Name
[string]$Global:StoreTypeX1 = $jsondata.Test_Suite_Name

Write-Host "fffffffffffffffff"
Write-Host $StoreTypeX1
Write-Host "fffffffffffffffff"
Write-Host $StoreURLX1
Write-Host "fffffffffffffffff"
Write-Host $StoreNameNSG
Write-Host "fffffffffffffffff"
Write-Host $StoreName
Write-Host "fffffffffffffffff"
Write-Host $StoreURLNSG
Write-Host "fffffffffffffffff"
Write-Host $StoreTypeX2
Write-Host $StoreTypeX2
Write-Host $StoreTypeX2
Write-Host "fffffffffffffffff"