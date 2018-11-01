

package Quartic

import ml.dmlc.xgboost4j.scala.spark.XGBoostEstimator
import org.apache.spark.ml.{Pipeline, PipelineModel}
import org.apache.spark.ml.feature.{StringIndexer, VectorAssembler}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{DoubleType, StringType, StructField, StructType}

object RunApp {

  def main(args: Array[String]): Unit = {

    // start the spark session
    val spark  = SparkSession.builder()
      .appName("Spark XGBOOST Quartic Training")
      .master("local[*]")
      .getOrCreate()

    // path where we have the training data
    val filePath = "file:///your_path/train.csv"
    val modelPath = "your_path"
    
    val schema = StructType(
      Array(StructField("FeaA", DoubleType),
        StructField("FeaB", DoubleType),
        StructField("FeaC", DoubleType),
        StructField("FeaD", DoubleType),
        StructField("FeaE", DoubleType),
        StructField("Label", IntType)
      ))

    // read the raw data
    val df_raw = spark
      .read
      .option("header", "true")
      .schema(schema)
      .csv(filePath)

    // create the feature vector
    val vectorAssembler = new VectorAssembler()
      .setInputCols(Array("FeaA", "FeaB", "FeaC", "FeaD", "FeaE"))
      .setOutputCol("features")

    // use the estimator to create the model
    val xgbEstimator = new XGBoostEstimator(Map[String, Any]("num_rounds" -> 100))
      .setFeaturesCol("features")
      .setLabelCol("Label")

    // create the pipeline with the steps
    val pipeline = new Pipeline().setStages(Array(vectorAssembler, xgbEstimator))

    // create the model following the pipeline steps
    val cvModel = pipeline.fit(df)

    // save the model
    cvModel.write.overwrite.save(modelPath)
  }
}