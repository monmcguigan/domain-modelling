import sbt.*
import sbt.Keys.*
import sbt.plugins.JvmPlugin

object BuildPlugin extends AutoPlugin {
  override def trigger = allRequirements

  override def requires = JvmPlugin

  override lazy val projectSettings = Seq(
    scalaVersion := "3.3.4",
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding",
      "utf-8",
      "-explaintypes",
      "-feature",
      "-language:existentials",
      "-language:experimental.macros",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-unchecked"
    ),
    libraryDependencies ++= Seq(
      "org.scalatest"     %% "scalatest"       % "3.2.12"   % Test,
      "org.scalatestplus" %% "scalacheck-1-16" % "3.2.12.0" % Test
    )
  )
}
