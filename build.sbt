name := """iptables-api"""

version := "1.0"

scalaVersion := "2.11.5"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.9",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.9" % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.specs2" %% "specs2-core" % "3.0.1" % "test")
  
scalacOptions in Test ++= Seq("-Yrangepos")
