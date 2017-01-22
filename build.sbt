import sbt.Keys._
import Settings._

shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " }
//onLoad in Global := (onLoad in Global).value andThen (Command.process(s"", _))


lazy val main = (project in file("."))
  .settings(commonSettings, publicationSettings, readmeVersionSettings)
  .settings(
    name := "macros-main",
    version := "0.0.1",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "sourcecode" % "0.1.2"
    ),
    reColors := Seq("magenta")
  ).dependsOn(sub % "compile->compile;test->test")

lazy val sub = (project in file("./macros"))
  .settings(commonSettings)
  .settings(
    name := "macros-sub",
    version := "0.0.1",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value % "provided"
    )
  ).disablePlugins(RevolverPlugin)

lazy val isolated = (project in file("./logics"))
  .settings(commonSettings)
  .settings(
    name := "macros-isolated",
    version := "0.0.1",
    description := "Scope within which implementation resides. These definitions cannot be seen by main project.",
    libraryDependencies ++= Seq(
    )
  ).disablePlugins(RevolverPlugin)