# Gamification API Project

This project have been realized for the OSF module, which take part of Master of Science in Engineering at HES-SO.

The goal is to implement a Gamification API with Java EE and the architectural style REST. Additionaly, we added remote access through JNDI.

[Repository of our professor.](https://github.com/wasadigi/Teaching-MSE-OpenSourceFrameworks)

## Team

1. Alexandre Perusset alexandre.perusset@master.hes-so.ch
2. Gaël Jobin gael.jobin@master.hes-so.ch
3. Thomas Moegli thomas.moegli@master.hes-so.ch

## Changelog

Since the first presentation, we've done some modifications and improvement (due to our Technical POC project and professor comments), listed below :

* Integration of application notion and security checks for the whole entities (only with application id, an application has no secret for the moment).
* Add remote access to the services.
* Split into two main projects : a jar packaged library and the web application.
* The APIary document has been updated, completed and is now more comprehensive.

## Codes and projects structure

To get a working copy of the current version of our code, simply clone it with Git:

```
git clone https://github.com/aperusset/Gamification.git
```

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

## API Documentation

Currently, we have three differents documentations:

1. [APIary documentation](http://docs.alexandreperusset.apiary.io)
2. Visual analysis ([Domain model](https://github.com/aperusset/Gamification/blob/master/Domain_Model.png), [REST diagram](https://github.com/aperusset/Gamification/blob/master/REST_Model.png))
3. Comments in the code :-)

Enjoy and dont hesitate to comment our work !

## Known bugs

When you request our API to give you a JSON payload to big (for example, when you have more than 2000 users for one application and try to retrieve the leaderboards), Glassfish will return a HTTP 500 Internal Error.
Here's a piece of the stacktrace:

```
StandardWrapperValve[ch.heigvd.gamification.services.exposed.RESTAPI]:
Servlet.service() for servlet
ch.heigvd.gamification.services.exposed.RESTAPI threw exception
java.lang.NullPointerException
    at
org.glassfish.pfl.dynamic.copyobject.impl.ClassCopierBase.copy(ClassCopierBase.java:131)
    at
org.glassfish.pfl.dynamic.copyobject.impl.ClassCopierOrdinaryImpl$ClassFieldCopierUnsafeImpl$17.copy(ClassCopierOrdinaryImpl.java:810)
    at
org.glassfish.pfl.dynamic.copyobject.impl.ClassCopierOrdinaryImpl$ClassFieldCopierUnsafeImpl.copy(ClassCopierOrdinaryImpl.java:1008)
    at
org.glassfish.pfl.dynamic.copyobject.impl.ClassCopierOrdinaryImpl.doCopy(ClassCopierOrdinaryImpl.java:1128)
    at
org.glassfish.pfl.dynamic.copyobject.impl.ClassCopierBase.copy(ClassCopierBase.java:129)
    at
org.glassfish.pfl.dynamic.copyobject.impl.ClassCopierOrdinaryImpl$ClassFieldCopierUnsafeImpl$17.copy(ClassCopierOrdinaryImpl.java:810)
    at
org.glassfish.pfl.dynamic.copyobject.impl.ClassCopierOrdinaryImpl$ClassFieldCopierUnsafeImpl.copy(ClassCopierOrdinaryImpl.java:1008)
    at
org.glassfish.pfl.dynamic.copyobject.impl.ClassCopierOrdinaryImpl.doCopy(ClassCopierOrdinaryImpl.java:1128)
...
```

The "solution" found is to increase the stack size of the JVM for glassfish (-Xss512m for example). No more exception.

But if you set the stack size too low (-Xss160k), it will appear another exception, different from the previous one.

```
WARNING:   StandardWrapperValve[ch.heigvd.gamification.services.exposed.RESTAPI]: Servlet.service() for servlet ch.heigvd.gamification.services.exposed.RESTAPI threw exception
java.lang.StackOverflowError
	at java.security.AccessController.doPrivileged(Native Method)
	at org.jvnet.hk2.osgiadapter.OSGiModuleImpl$4.loadClass(OSGiModuleImpl.java:430)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:356)
	at com.sun.enterprise.v3.server.APIClassLoaderServiceImpl$APIClassLoader.loadClass(APIClassLoaderServiceImpl.java:238)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:410)
...

```
