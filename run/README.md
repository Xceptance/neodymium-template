# Neodymium Test Case Execution in Docker
This is about running Neodymium test cases without the need to prepare your machine with all the required but just one prerequisite: Docker. You can do so by running the test cases in Docker keeping your local machine setup untouched. Next to this use one might get an idea how to integrate a Neodymium test suite with a Docker based Continuous Integration system. And last but not least this Docker based execution approach can be used by application developers when running Neodymium regression tests against their locally modified development application instance. As an developer you might detect aftereffects of your changes in the quickest possible way ever when running certain regression tests right before any commit.

# Approach
The Neodymium test cases run from within a Docker container where the Google Chrome browser is executed in the headless mode. You can't watch it executing the test cases, except the text output. At the end of the test run it provides you the local location to the generated test execution report in the output. The report is written into a local sub directory of the Neodymium test project. Then you can watch the report by opening this local location with your web browser.

Using this approach on a local machine is done in a few steps.

## Prerequisite
The local machine must be prepared to execute test cases using Docker:
* The Neodymium test project (starting with neodymium-template) is assumed to be available in a local folder that is referred to as the *neodymium-template directory*.
* Docker Desktop as well as Docker Compose must be installed (see: https://www.docker.com/).

## Run Preparation
This is an optional step. In case you don't want all but only certain test cases to be executed, here is the option to take control. On the test project level more control options (e.g. a parameter to specify the system under test) can be added.
1. Open testcase-selection.env, read there how to define a test case for execution.

All available test cases will be executed by default.

## Run the Test Case
There is nothing more to set up or configure.
###### Linux and MacOS
Execute `neodymium-template> docker-compose up`

###### Windows with at least Docker Desktop Community **2.3.0.3**
1. Open Docker Dashboard Settings FILE SHARING, add the neodymium-template directory, then Apply and Restart
2. Open Git Bash from directory `neodymium-template`, then execute `docker-compose up`

**Note** The first run will take some more time, because the Docker container builds automatically, installs the latest Google Chrome browser and the Chrome Webdriver. Be patient. From the second run on the test execution starts up quickly.

Now watch the terminal output and wait that the execution ends up with the information about the URL where the test report can be opened in a browser, like this:

    neodymium-docker-run_1  | #
    neodymium-docker-run_1  | # VIEW RUN RESULTS #
    neodymium-docker-run_1  | + Open this location in a local running browser to view the detailed run report.
    neodymium-docker-run_1  |   file:///<neodymium-template directory>/target/site/allure-maven-plugin/index.html
    neodymium-docker-run_1  | ##

Open the *local file* URL to **<neodymium-template directory>/target/site/allure-maven-plugin/index.html** and watch the report.

**Note** One may use Firefox, because the report needs to execute Javascript, which is not permitted in other browsers from a local source. If you see the *Loading...* and nothing happens in the report, you have to configure Firefox additionally. Type *about:config* into the URL, agree to the warning, then search for the *privacy.file_unique_origin* configuration and set it from true to *false*. The report should load afterwards.

Repeat runs as often as needed. Just specify another test case if required and run again `docker-compose up`. Old test results are automatically cleaned up on each new run. Reload the report and the latest result will be displayed.
