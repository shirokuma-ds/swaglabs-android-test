# Android Automation for Swaglabs App

## How to Execute the Test

1. ### Phone and App Configuration

    Go to **configs** which is inside the **resources** directory. Open 
   [driver_configs.json](src/test/resources/configs/driver_config.json)
    file and changes the following fields:
    - **deviceName**: Change this to your device name
    - **udid**: Change this to your device udid
    - **fullReset**: If you don't have the APK, change the value to **true**
    - **noReset**: If you don't have the APK, change the value to **false**

2. ### Execute the Test

    To execute the first test please go to the following class or click here [SauceDemoTest](src/test/kotlin/org/shirokuma/tests/SauceDemoTest.kt)

## Viewing the automation test report

After test execution finished, if you are using IntelliJ to run this project. <br>
1. Open Maven Plugins
2. Open **allure**
3. Execute the **allure:serve**
4. This will open the report in your local browser


Alternatively if you are not using IntelliJ, run the following command in the terminal within the project directory
```shell
mvn allure:serve
```

## Project Structures

### 1. Bases
> Base parent classes

### 2. Configs
> Project Configs that read config from the resource json file

### 3. Drivers
> Contains DriverManager that manage the driver within the project

### 4. Listeners
> Contains 2 classes:
> - StepsListener: Class that will be executed every step
> - TestListener: Class that will be executed every test
> <br>Both of the classes able to be identified by allure by registering it via **META-INF** inside the **resources** directory

### 5. Page
> Page classes are class that represent a Screen/Page in the app, it contains all the elements declaration

### 6. Steps
> Step classes contains all the steps/actions a user can do within the screen

### 7. Test Data
> Contains Test Data classes that are used for the tests

### 8. Tests
> All the Tests of the classes written based on TestNg structure

### 9. Users
> Contains User classes

### 10. Utils
> Contains utilities for the automation