# Gamification API Project


## Team

1. Diógenes Firmiano diogenesjf@gmail.com

## Changelog


There is severals Netbeans projects, so you need to install [Netbeans for Java EE and Java EE SDK with Glassfish 4.0.](https://netbeans.org/downloads/start.html?platform=windows&lang=en&option=javaee)

Once you have cloned the source code, you can import these two projects into NetBeans :

* GamificationLib
* GamificationAPI

You have to add a maven dependency of GamificationLib into GamificationAPI with compile scope. Normally, the dependency is present but can be problematic for a new import in Netbeans.

The GamificationLib project, which is JAR packaged, is here for remote access to our API via JNDI. You can use the JAR into some Java projects to call remotly Gamification API functionnalities.

We have done another project which use the Gamification library from a Play! application : [Call EJB from Play! Technical Proof of Concept](https://github.com/aperusset/Technical_POC). Do not hesitate to consult this project to get an overview of how remote calls are made from another Java environment with JNDI.

The two others NetBeans projects, GamificationClientEE and GamificationClientJS, are here to show how to connect remotly to our Gamification API from a Java Entreprise and Java Standard environnement.

## Run Instructions

First, clean and build GamificationLib. 

You have to create a database for the API. To create a database from Netbeans, use Services tab > Databases > Right Click on Java DB > Create Database. Enter these informations :

```
Database Name : gamification
User Name : gamification
Password (and confirmation) : gamificationpw
```

Il you want to enter another informations, please modify as you want the glassfish-resources.xml. You can also edit the persistence.xml configuration file as you wish.

Then clean, build and play GamificationAPI. Your browser is automatically opened at the Gamification API root webpage, which show our APIary documentation.

Now, let's start to use the API ! If you want to test the functionnalies, you can use [Postman](https://chrome.google.com/webstore/detail/postman-rest-client/fdmmgilgnpjigdojojpjoooidkmcomcm) extension for Google Chrome.


Enjoy and dont hesitate to comment our work !


