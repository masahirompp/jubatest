package com.mpp.juba.sample

import us.jubat.classifier.ClassifierClient
import us.jubat.classifier.Datum
import us.jubat.classifier.EstimateResult
import us.jubat.classifier.TupleStringDatum
import us.jubat.classifier.TupleStringDouble
import us.jubat.classifier.TupleStringString
import jubaba.Configuration
import jubaba.classifier.ClassificationResult
import jubaba.classifier.Classifier
import com.github.tototoshi.csv.CSVReader
import java.io.File
import scala.collection.Iterator
import jubaba.Configuration.ConfigurationBuilder
import scala.collection.Iterator
import scala.collection.immutable.List
import jubaba.classifier.ClassificationFeatures

object App {

  //const
  val host: String = "127.0.0.1"
  val port: Int = 9199
  val name: String = "jubatus-test"
  val csvTrain: String = "/Users/mpphome/Downloads/profile_train.csv"
  val csvEstimate: String = "/Users/mpphome/Downloads/profile_estimate.csv"
  val label: String = "カップ数"

  def main(args: Array[String]): Unit = {

    //init
    val conf = new ConfigurationBuilder().host(host).port(port).build()
    val classifier = new Classifier(conf, name)

    //train
    val trainData = CSVReader.open(new File(csvTrain)).allWithHeaders
    trainData.foreach(map => training(classifier.newFeatures(), map(label), map.filterKeys(k => k != label)))

    //estiate
    val estimateData = CSVReader.open(new File(csvEstimate)).allWithHeaders
    estimateData.foreach(map => {
      val result = estimate(classifier.newFeatures(), map.filterKeys(k => k != label))
      println("ans:" + map(label))
      println("推量値:" + result.maximumScoredLabel())
      println(map.toString)
      println(result.toString())
      //formatResult(result, map(label))
    })
  }

  def training(ft: ClassificationFeatures, label: String, samples: Map[String, Any]): Unit = {
    ft.label(label)
    samples.foreach(p => addSample(ft, p._1, p._2))
    ft.train()
  }

  def estimate(ft: ClassificationFeatures, samples: Map[String, Any]): ClassificationResult = {
    samples.foreach(p => addSample(ft, p._1, p._2))
    ft.classify()
  }

  def addSample(ft: ClassificationFeatures, k: String, v: Any) = {
    v match {
      case v: Double => ft.add(k, v)
      case v: String => ft.add(k, v)
      case _ =>
    }
  }

  def formatResult(result: ClassificationResult, ans: String) = {
    println("======================")
    println("答え：" + ans)
    println("推量値：" + result.maximumScoredLabel())
    println(result.toString())

  }
}