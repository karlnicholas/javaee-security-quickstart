Java EE MVC Quickstart Maven Archetype
=========================================

Summary
-------
The project (will be) is a Maven archetype for Java EE MVC web application.
This project was inspired by kolorobot's Spring-Mvc-Quickstart-Archetype project.
This readme.md started with kolorobot's Spring-Mvc-Quickstart-Archetype project.

Generated project characteristics
-------------------------
* Java EE MVC web application for Wildly 9 environment
* JSF 2.2 with Bootstrap
* JPA 2.1
* H2DB (H2 Development Database) 
* JUnit/Arquillian/Drone/Graphene for testing
* Java EE Security with Database Module 

Installation
------------

To install the archetype in your local repository execute following commands:

```bash
    git clone https://github.com/knicholas/javaee-mvc-quickstart-archetype.git
    cd javaee-mvc-quickstart-archetype
    mvn clean install
```

Create a project
----------------

```bash
    mvn archetype:generate \
        -DarchetypeGroupId=javaee-mvc-archetypes \
        -DarchetypeArtifactId=javaee-mvc-quickstart \
        -DarchetypeVersion=1.0.0 \
        -DgroupId=my.groupid \
        -DartifactId=my-artifactId \
        -Dversion=version
```

Run the project
----------------

```bash
    mvn test wildfly:deploy
```

Test on the browser
-------------------

    http://localhost:8080/javaee-mvc

Note: Wildfly security-domain configuration is required to run the application. 
See src/main/webapp/WEB-INF/jboss-security-domain.xml

Creating a new project in Eclipse
----------------------------------

* Import archetype URI by `Import ... > Projects from Git > Clone URI`
* Install the archetype in local repository with `mvn install`
* Go to `Preferences > Maven > Archetypes` and `Add Local Catalog`
* Select the catalog from file (`archetype-catalog.xml`) 
* Create new Maven project and select the archetype (remember so select `Include snapshot archetypes`)

If you have any troubles with installation in Eclipse, you may want to have a look at this issue: #74

