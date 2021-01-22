#!/bin/sh

#
# 2021-01-08 Robby Klehm, Xceptance
# This script is used at neodymium-template in order to execute test runs on a 
# local machine environment within a Docker
#
# README.md provides a documentation
#

#
echo "##"
echo "# runDocker - Neodymium Test Case Execution in Docker" 

# check prerequistes
if ! which mvn > /dev/null 2>&1; then
  echo "- There is no maven installed. Apache Maven-3 is required to run."
  echo "#"
  exit 1
fi

maven_version="$(mvn --version)"
if [[ ! "${maven_version}" =~ "Apache Maven 3" ]]; then
  echo "- Apache Maven 3 is required to run. Recheck following:"
  mvn --version
  echo "#"
  exit 1
fi

#
echo "#"
echo "# DETECTED ENVIRONMENT AND PARAMETERS #"

#
# 1. parameter is the test case selector
if [[ "$1" != "" ]]; then
  # prepare the Maven -Dtest parameter
  # refers to: https://maven.apache.org/surefire/maven-surefire-plugin/examples/inclusion-exclusion.html
  if [[ "${1}" == "${1#*#}" ]]; then
    # pur test case without a method (no method separator #)
    test_case_selector="-Dtest=%regex[.*${1/\#*/}.*#.*]"
  else
    test_case_selector="-Dtest=%regex[.*${1/\#*/}.*#.*${1#*#}.*]"
  fi
else
  echo "- Usage:"
  echo "  REQUIRED PARAMETERS"
  echo "  1. test case selector"
  echo "#"
  exit 1
fi
echo "+ using Selector: ${test_case_selector}"

#
# 2. provide message for Windows users running against localhost
# 
# I'd suggest to provide the SUT URL with another parameter, if you plan to run tests against different SUTs.
#
if ping -w 3 -c 3 host.docker.internal 2> /dev/null; then
  echo "\! Docker is running on Windows."
  echo "\! - If your tests run against localhost, you must connect to a special DNS from with Docker."
  echo "\! - Instead of localhost use host.docker.internal in your testware configuration."
fi

#
# force docker image webdriver over dev-neodymium.properties
webdriver="-Dneodymium.webDriver.chrome.pathToDriverServer=/usr/local/webdriver/chromedriver"

# prepare results location
mkdir -p ./allure-results
mkdir -p ./target/allure-results

#
chrome_version="$(/opt/google/chrome/chrome --version)"
chromedriver_version="$(/usr/local/webdriver/chromedriver --version)"
echo "+ using Chrome: ${chrome_version}"
echo "+ using Chrome Webdriver: ${chromedriver_version}"

#
# Chrome Webdriver major version check
chrome_version_major="${chrome_version%%.*}"
chrome_version_major="${chrome_version_major##* }"
chromedriver_version_major="${chromedriver_version%%.*}"
chromedriver_version_major="${chromedriver_version_major##* }"

if [[ "${chrome_version_major}" != "${chromedriver_version_major}" ]]; then
  echo "- The Chrome Webdriver \"${chromedriver_version_major}\" does not match to Chrome "${chrome_version_major}"."
  echo "  An update of the Docker image is required."
  echo "#"
  exit 1
fi

#
# clean up old reports
mvn --batch-mode clean

# execute selected tests and datasets, browsers and write the Allure report
echo "+ start: $(date)"
# must run headless, have to switch it temporary, because browserprofile parameters can not be set
# in the command line like -Dbrowserprofile.Chrome_1024x768.headless=true
if [ -e config/browser.properties ]; then
  if [ -e config/dev-browser.properties ]; then
    echo "! The configuration file config/dev-browser.properties is already available."
    echo "! As you take yourself care for the Neodymium configuration this script will"
    echo "! not touch it."
    echo "! Please take yourself care, that the browser is running in headless mode."
    echo "! Else you might see errors like: Chrome failed to start: exited abnormally."
  else
    # add headless configurations for each chrome browser temporary with config/dev-browser.properties
    echo "+ Adding headless chrome browser configuration temporary..."
    echo "# temporary configuration for runDocker.sh, will be removed after test execution" > config/dev-browser.properties
    grep -E "\.browser\s+=\s+chrome" config/browser.properties | \
      sed 's/.browser.*/.headless = true/' >> config/dev-browser.properties
    grep -E "\.browser\s+=\s+chrome" config/browser.properties | \
      sed 's/.browser.*/.arguments = --no-sandbox/' >> config/dev-browser.properties
    cat config/dev-browser.properties
    echo "+ This configuration will be removed after test execution."
    if [ -e config/dev-browser.properties ]; then
      DEV_BROWSER_PROPERTIES="remove"
    fi
  fi
else
  echo "- The file config/browser.properties should be available."
  echo "  The browsers have to be configured to run in headless mode."
  echo "#"
  exit 1
fi

#
mvn --batch-mode "${test_case_selector}" ${webdriver} -DfailIfNoTests=false test allure:report

#
if [[ "${DEV_BROWSER_PROPERTIES}" == "remove" ]]; then
  echo "+ Removing temporary headless browser configuration."
  rm config/dev-browser.properties
fi
echo "+ end: $(date)"

# adjust ownership, because written as root from within docker
chown -R $(stat --printf "%u:%g" pom.xml) target/
echo "#"
echo "# VIEW RUN RESULTS #"
echo "# Open this location in a local running browser to view the detailed run report."
echo "# file:///<neodymium-template directory>/target/site/allure-maven-plugin/index.html"
echo "##"
#
