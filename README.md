# Introduction
This repository is supposed to be used as template for test automation projects using [Neodymium Library](https://github.com/Xceptance/neodymium-library). It contains both a pure Java and a Cucumber approach as well. A test automation template based on best practice libraries and added missing functionalities to aid test automation done by Xceptance.

# Getting familiar
Please perform the Hello World tutorial first to get a first insight and feeling for test automation with Neodymium.
Afterwards you are ready to deep dive into more complex scenarios. 
Our [Neodymium-example](https://github.com/Xceptance/neodymium-example) project demonstrates all the cool stuff that's possible with Neodymium.
Furthermore, we set up a [Neodymium Wiki](https://github.com/Xceptance/neodymium-library/wiki) to explain different concepts and use cases. 

# Hello World
## Get your own copy
1. Fork this project
2. Rename the project (Open the `pom.xml` and adjust the name tag)
3. Import the project into an IDE of your choice. (It should be able to handle Maven project in order to sort all the dependencies for you)

## Set up
4. Set up WebDrivers and Browsers
   1. Open `config\neodymium.properties`
   2. Set the property `neodymium.webDriver.chrome.pathToDriverServer`
   3. Set the property `neodymium.webDriver.chrome.pathToBrowser` if you want don't want to use the default Chrome
5. Change the URL (or try with [https://www.xceptance.com/en/](https://www.xceptance.com/en/) (already set) for demo purposes)
   1. Open `config\neodymium.properties`
   2. Set `neodymium.url.protocol` to the protocol, i.e. `https`
   3. Set `neodymium.url.host` to the host, i.e. `www.xceptance.com`
   4. Set `neodymium.url.path` to the path, i.e. `en`
   5. Set `neodymium.url.port` to the port with a colon in front, i.e. `:443`
   
   Instead of these steps, the property `neodymium.url` could also simply be changed to the desired URL. However, since the [Owner](http://owner.aeonbits.org/) framework allows reading and especially mutating properties among others, it is more helpful to separate the URL in its respective parts.


## Execution
6. Run the `template.neodymium.tests.smoke.HomePageTest.java` from the Neodymium package as JUnit test

## Validate the World with Neodymium 
7. Uncomment `homepage.validateStructure();` within the `HomePageTest.java`
8. Adjust the validation within the `template.pageObjects.pages.HomePage.java` to match the site that is going to be tested (only if you changed it)
9. Run the `template.neodymium.tests.smoke.HomePageTest.java` again

## Validate the World with Cucumber
9. Run the `template.cucumber.tests.RunAllTests.java` from the Cucumber package as JUnit test

# Taking ownership 
If you want to adopt the template for your own project your certainly would like to change the template folder to something project specific.
Please perform the following steps to rename it:
1. Rename the `template` package to a name of your choice
2. Update the `pom.xml` (especially the Surefire configuration that states which tests should be executed)
3. Update the `@CucumberOptions` within `template.cucumber.tests.RunAllTests.java` to have the new path in features and glue

# Remove unneeded code approach
After you have decided whether you want to go the pure Java or the Cucumber way. You can simply delete the folder of the unused approach (either `src/test/java/template/neodymium` or `src/test/java/template/cucumber`).
If you like you can also clean up the Surefire configuration in the `pom.xml` by removing the now unused `<include>` path.

```XML
<includes>
    <!-- Neodymium (pure Java) test cases -->
    <include>template/neodymium/tests/**/*Test.java</include>
    <!-- Cucumber test cases -->
    <include>template/cucumber/tests/RunAllFeaturesTest.java</include>
</includes>
```

In case you want to use both approaches you are free to do so and take advantage of booth of their strengths.

## License
MIT
