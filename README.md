# JBoss 7 Datasource Monitoring Plugin

## Overview

![images_community/download/attachments/120128668/icon.png](images_community/download/attachments/120128668/icon.png)

Starting "Jboss 7" or "Jboss EAP 6", Jboss has stopped the support for exposing connection pool statistics via JMX beans. If you want to use the dynaTrace JMX based measure to monitor your datasource
usage/statistics you wont be able to do so.This plugin will help you capture the datasource statistics in "Jboss 7" or "Jboss EAP 6" using the new REST based Jboss management console. Once you capture
the measure you can chart on the data.

Here is a screen shot of a dashboard, which monitors the active Connection on individual data-sources.

![images_community/download/attachments/120128668/DataSource.JPG](images_community/download/attachments/120128668/DataSource.JPG)

## Plugin Details

| Name | JBoss 7 Datasource Monitoring Plugin
| :--- | :----
| Author | Muthu Madialagan
| License | [dynaTrace BSD](dynaTraceBSD.txt)
| Support | [Not Supported ](https://community.compuwareapm.com/community/display/DL/Support+Levels#SupportLevels-Community) If you have any questions or suggestions for these plugins, please add a comment to this page, use our [forum](https://community.dynatrace.com/community/pages/viewpage.action?pageId=46628918), or drop us an email at [apmcommunity@compuware.com](mailto:apmcommunity@compuware.com)!
| Release History | 2013-05-08 Initial Release
| Download | [com.jboss7.ds.statistics_1.0.0.jar](com.jboss7.ds.statistics_1.0.0.jar)


## Configuration

Name

Value

datasourceName

Name of the datasource

DSMeasure

The datasource measure/statistics that you want to capture.  
**_NOTE:_** This value is case-sensitive 

jbossusername

Jboss admin username

jbosspassword

Jboss admin password

managementPort

Jboss management console port

## Installation

Import the Plugin into the dynaTrace Server. For details how to do this please refer to the [dynaTrace documentation](https://community.compuwareapm.com/community.dynatrace.com/community/display/DOCDT50/Manage+and+Develop+Plugins#ManageandDevelopPlugins-ManageandDevelopPlugins).

