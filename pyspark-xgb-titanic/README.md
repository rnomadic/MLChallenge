## PySpark ML and XGBoost tested on the Kaggle Titanic dataset

### Instruction to make pyspark with Anaconda distribution and 
conda create --name spark python=3
source activate spark
conda install jupyter notebook 
conda install -c conda-forge pyspark 
conda install -c conda-forge findspark 

#### Spark installation guide

First download spark tar and extract from below page
[Spark](https://www.apache.org/dyn/closer.lua/spark/spark-2.3.2/spark-2.3.2-bin-hadoop2.7.tgz)
$ tar -xzf spark-2.3.0-bin-hadoop2.7.tgz
Copy to /tmp
$ mv spark-2.3.0-bin-hadoop2.7 /tmp/spark-2.3.0
Create a symbolic link (this will let you have multiple spark versions):

$ ln -s /tmp/spark-2.3.0 /tmp/spark̀

Open .bash_profile and update below 2 line
export SPARK_HOME=/tmp/spark
export PATH=$SPARK_HOME/bin:$PATH

Install JAVA
conda install -c cyclus java-jdk
