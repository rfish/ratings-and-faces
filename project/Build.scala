import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "ratings-and-faces"
    val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "com.google.code.morphia" % "morphia" % "0.99",
    "org.mongodb" % "mongo-java-driver" % "2.10.0",
    "com.google.code.morphia" % "morphia-logging-slf4j" % "0.99",
    "org.apache.httpcomponents" % "httpclient" % "4.1.3",
    "com.amazonaws" % "aws-java-sdk" % "1.3.22",
    "me.moocar" % "logback-gelf" % "0.9.6p2",
    "com.notnoop.apns" % "apns" % "0.1.6"
  )

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
    resolvers += "Maven repository" at "http://morphia.googlecode.com/svn/mavenrepo/",
    resolvers += "MongoDb Java Driver Repository" at "http://repo1.maven.org/maven2/org/mongodb/mongo-java-driver/"
  )
}
