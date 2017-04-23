# DBDeployer (spring-db-deployer)

## 1. Introduction:

If you are tired of managing the proper executions of SQL scripts in your projects then this tool might be helpful to you.

There are lot of changes happening while project's development phase and there is high possibility that you might mess up the script's execution.
Ex. You missed a SQL script to execute and later dependent script failed to execute because of the missing script. 
Such accidents can be avoided by using DBDeployer.

As of now, this project is **_under development_** but it is tested for PostgrSQL and partially for MySQL. Future target DBs are Oracle
and MSSql. 

**_WARNING:_** To be used at your own risk as this is under development. The original author won't be responsible in case of any loss.

## 2. How it works:

This tool creates a table called **_CHANGELOG_** in the DB schema. Everytime a script is executed, details of the script is logged in the 
**CHANGELOG** table. Once the script details are logged, that script won't be executed again, unless the script details are deleted
from the table. There is a pre-requisite for file to have a unique sequence (See point #3). The scripts are executed based on these sequences.

## 3. Pre-requisite:

#### i. SQL Script placement:
There is a requirement to place you SQL scripts in a pre-defines folder sructure, as shown below.
```
  <Root Folder>
   |
   -- DDL
   |    |_ <DDL_Scripts>
   |    
   -- DML
   |    |-- <DML_Scripts>
   |    
   -- PFT
        |-- <Prodecure, Functions or Triggers files>
```     


#### ii. SQL File name:
The file name should have a specific file name format, as shown below.
```
<unique_number>_<statement_type>_<operation>_<file_name>.sql
```
   - **unique_number**: THis number should be unique throughout all the scripts and also should be unique between the three folders
   stated above (DDL, DML, PFT). (To be strictly followed)
   - **statement_type**: Here mention the type of statement. Ex. DDL or DML.
   - **operation**: The type of operation being performed by script Ex. Create, Alter, Delete, etc.
   - **file_name>**: Additional description to be given for the script.
   
   ```
    1_DDL_CREATE_CUSTOMER.sql
    3_DML_INSERT_CUSTOMERDATA.sql
    5_PFT_FUNCTION_GETCUSTOMERNAME.sql
   ```
   
## 4. Configuration:

Find a file ```application.properties``` in the resources folder and fill the DB properties required by your project.
```
db.driver=<JDBC Driver class>
db.url=<DB URL>
db.username=<DB username>
db.password=<DB password>
```
You can leave the rest configuration to their default values

Find another properties file ```dbdeployer.properties``` under ```resources/deployer``` path.
Update the property ```deployer.path``` with the location of your SQL scripts. According to the folder structure mentioned above,
you have to give the absolute path till the root folder of this structure.
Ex. ```deployer.path=C:/test/DBScript``` where DBScript is the root folder.

## 5. Execution

There are two ways:
1. Build a jar file using maven build. Execute the jar from command-line.
2. Run the ```DbDeployerApplication.java``` from eclipse or any other IDE. Right Click > Run As > Java Application.

