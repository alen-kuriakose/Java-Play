name := """play-java-hello-world-tutorial"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml"

scalaVersion := "2.13.10"

//libraryDependencies += guice
//libraryDependencies ++= Seq(
//  javaJdbc
//)
libraryDependencies ++= Seq(
  "org.springframework" % "spring-jdbc" % "4.1.1.RELEASE",
  javaJdbc,
  guice,
  "org.hibernate" % "hibernate-core" % "5.4.32.Final",
  "mysql" % "mysql-connector-java" % "8.0.31",
  "org.springframework" % "spring-jdbc" % "4.1.1.RELEASE",
  "org.simplejavamail" % "simple-java-mail" % "5.0.0",
  "com.typesafe.play" %% "play-mailer" % "8.0.1",
  "org.apache.commons" % "commons-email" % "1.5",
  "org.apache.kafka" % "kafka-clients" % "2.8.0",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.12.1",
  "org.quartz-scheduler" % "quartz" % "2.3.2",
  "com.typesafe.akka" %% "akka-stream-kafka" % "2.0.4",
  "com.typesafe.akka" %% "akka-actor" % "2.5.23",
  "com.typesafe.akka" %% "akka-stream" % "2.5.23"
//  "org.springframework.kafka" % "spring-kafka" % "2.7.0.RELEASE",
//  "org.springframework" % "spring-context" % "5.3.4.RELEASE"

)
