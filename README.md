# Gamification API Project

This project have been realized for the OSF module, which take part of Master of Science in Engineering at HES-SO.

The goal is to implement a Gamification API with Java EE and the architectural style REST. Additionaly, we added remote access through JNDI.

[Repository of our professor.](https://github.com/wasadigi/Teaching-MSE-OpenSourceFrameworks)

## Team

1. Alexandre Perusset alexandre.perusset@master.hes-so.ch
2. Gaël Jobin gael.jobin@master.hes-so.ch
3. Thomas Moegli thomas.moegli@master.hes-so.ch

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

First, clean and build GamificationLib. Then clean, build and play GamificationAPI. Your browser is automatically opened at the Gamification API root webpage, which show our APIary documentation.

Now, let's start to use the API ! If you want to test the functionnalies, you can use [Postman](https://chrome.google.com/webstore/detail/postman-rest-client/fdmmgilgnpjigdojojpjoooidkmcomcm) extension for Google Chrome.

The two others NetBeans projects, GamificationClientEE and GamificationClientJS, are here to show how to connect remotly to our Gamification API from a Java Entreprise and Java Standard environnement.

## API Documentation

Currently, we have three differents documentations:

1. [APIary documentation](http://docs.alexandreperusset.apiary.io)
2. Visual analysis ([Class diagram](https://github.com/aperusset/Gamification/blob/master/Domain_Model.png), [REST diagram](https://github.com/aperusset/Gamification/blob/master/REST_Model.png))
3. Comments in the code :-)

Enjoy and dont hesitate to comment our work !
