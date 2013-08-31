import AssemblyKeys._

name := "testuuba"

organization := "com.mpp"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.10.0"

resolvers += "Jubatus Repository for Maven" at "http://download.jubat.us/maven"

resolvers += "MessagePack Repository for Maven" at "http://msgpack.org/maven2"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "us.jubat" % "jubatus" % "0.4.0"
)

initialCommands := "import com.mpp.juba.sample._"

assemblySettings