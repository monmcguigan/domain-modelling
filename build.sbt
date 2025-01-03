lazy val `domain-modelling` =
  project
    .in(file("."))
    .settings(
      libraryDependencies ++= Seq(
        "io.github.iltotore" %% "iron"      % "2.6.0",
        "io.github.iltotore" %% "iron-cats" % "2.6.0",
        "org.typelevel"      %% "cats-core" % "2.8.0"
      )
    )
logLevel := Level.Error
