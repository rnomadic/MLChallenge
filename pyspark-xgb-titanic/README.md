## PySpark ML and XGBoost tested on the Kaggle Titanic dataset

### Instruction to make pyspark with Anaconda distribution on OSX
conda create --name spark python=3 <br>
source activate spark <br>
conda install jupyter notebook  <br>
conda install -c conda-forge pyspark  <br>
conda install -c conda-forge findspark <br>

#### Spark installation guide

1. download spark tar and extract from below page
[Spark](https://www.apache.org/dyn/closer.lua/spark/spark-2.3.2/spark-2.3.2-bin-hadoop2.7.tgz)
2. $ tar -xzf spark-2.3.0-bin-hadoop2.7.tgz

3. $ mv spark-2.3.0-bin-hadoop2.7 /tmp/spark-2.3.0

4. Create a symbolic link (this will let you have multiple spark versions):

$ ln -s /tmp/spark-2.3.0 /tmp/spark̀

5. Open .bash_profile and update below 2 line <br>
export SPARK_HOME=/tmp/spark <br>
export PATH=$SPARK_HOME/bin:$PATH <br>

6. Install JAVA <br>
conda install -c cyclus java-jdk
