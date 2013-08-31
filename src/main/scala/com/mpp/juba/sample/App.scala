package com.mpp.juba.sample

import us.jubat.classifier.ClassifierClient;
import us.jubat.classifier.Datum;
import us.jubat.classifier.EstimateResult;
import us.jubat.classifier.TupleStringDatum;
import us.jubat.classifier.TupleStringDouble;
import us.jubat.classifier.TupleStringString;

object App {

  def main(args: Array[String]): Unit = {

    val host: String = "127.0.0.1"
    val port: Int = 9199
    val name: String = "test"

    val client = new ClassifierClient(host, port, 1.0)
    

  }

}