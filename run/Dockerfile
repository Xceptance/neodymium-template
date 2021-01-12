# ###
# Dockerfile to run Neodymium automated tests in local Docker environment in Chrome
#
# BUILD and RUN with Docker Compose
# > docker-compose up
#
# BUILD
# > docker build -t neodymium-docker-run --pull -f Dockerfile .
# 
# RUN
# > docker run -i -t --rm -v "$(pwd)"/../:/var/run/neodymium-docker-run neodymium-docker-run .*#.*Chrome
# 
FROM maven:3.6.3-jdk-11

ARG http_proxy=""
ARG https_proxy=""

# Maven Opts for SSL
RUN \
  MAVEN_OPTS="-Dmaven.wagon.http.ssl.insecure=true \
    -Dmaven.wagon.http.ssl.allowall=true \
    -Dmaven.wagon.http.ssl.ignore.validity.dates=true"; \ 
  echo "MAVEN_OPTS=\"${MAVEN_OPTS}\"" > ~/.mavenrc; \ 
  echo "export MAVEN_OPTS=\"${MAVEN_OPTS}\"" >> /etc/profile

# Dependencies
RUN \
  apt-get update && apt-get --yes install libdbus-glib-1-2
# Browser
# - Google Chrome latest available stable version will be installed always
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
  && echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list \
  && apt-get update -qqy \
  && apt-get -qqy install google-chrome-stable \
  && rm /etc/apt/sources.list.d/google-chrome.list \
  && rm -rf /var/lib/apt/lists/* /var/cache/apt/*

# Chrome Webdriver
# - The Chrome Webdriver version that matches the installed Chrome browser is automatically determined.
#   Author: Robby Klehm, Xceptance (c) 2020-2021
RUN mkdir /usr/local/webdriver
RUN CHROME_VERSION=$(/opt/google/chrome/chrome --version) \
    # determine the installed Chrome major version
    && C="${CHROME_VERSION%%.*}" && CHROME_MAJOR_VERSION="${C##* }" \
    && echo "+ Determined installed Chrome major version: ${CHROME_MAJOR_VERSION}" \
    # query the Chrome Webdriver version with the major Chrome version
    && CHROME_WEBDRIVER_QUERY_URL="https://chromedriver.storage.googleapis.com?delimiter=/&prefix=${CHROME_MAJOR_VERSION}" \
    && wget --no-verbose -O /tmp/chrome_major_version_drivers.xml "${CHROME_WEBDRIVER_QUERY_URL}" \
    # list of matching Chrome Webdriver versions
    && QUERY="$(cat /tmp/chrome_major_version_drivers.xml)" \
    # determine the latest machting Chrome Webdriver out of the XML based result set
    && Q="${QUERY#*CommonPrefixes><Prefix>}" && CHROME_WEBDRIVER_VERSION="${Q%%<\/Prefix>*}" \
    && echo "+ Determined matching Chrome Webdriver: ${CHROME_WEBDRIVER_VERSION}" \
    # download the determined Chrome Webdriver
    && CHROME_WEBDRIVER_URL=$(echo "https://chromedriver.storage.googleapis.com/${CHROME_WEBDRIVER_VERSION%/}/chromedriver_linux64.zip") \
    && cd /usr/local/webdriver \
    && wget --no-verbose -O /tmp/chromedriver.zip "${CHROME_WEBDRIVER_URL}" \
    # finally install the downloaded Chrome Webdriver
    && unzip -q /tmp/chromedriver.zip -d /usr/local/webdriver \
    && chmod +x /usr/local/webdriver/chromedriver \
    && rm /tmp/chromedriver.zip

# make Bash the default shell
RUN rm /bin/sh && ln -sf /bin/bash /bin/sh

# Maven should install all once into the container for reuse to reduce test run time
COPY files/pom.xml /tmp
RUN cd /tmp && mvn --batch-mode -q install allure:install -DskipTests=true

# Assuming current directory is neodymium-template/run requires to run with 
# -v ../:/var/run/neodymium-docker-run
WORKDIR /var/run/neodymium-docker-run
#
# runDocker.sh on Windows may be checked out with modified linebreak. This has to be corrected on each start on Windows
# to not run into this error: standard_init_linux.go:211: exec user process caused "no such file or directory"
ENTRYPOINT ["sh", "-c", "cp run/runDocker.sh /tmp && \
  cat /tmp/runDocker.sh | tr -d '\r' > runDocker.sh; \
  chmod 755 runDocker.sh; \
  ./runDocker.sh \"$0\"; \
  rm -f ./runDocker.sh"]
