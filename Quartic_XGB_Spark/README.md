### Training
The training is done on a XGBoost classifier using a ML pipeline in Spark. 
The classifier will be saved as an output and will be used in a Spark Structured Streaming realtime app to predict new test data.

Step 1: Starting the spark session

I am creating a spark app that will run locally and will use as many threads as there are cores using local[*] :

Step 2: Defining a schema

Next I define a schema of the data that I read from the csv. This is usually a better practice than letting spark 
to infer the schema because it consumes less resources and we have total control over the fields.

Step 3: Reading the data

Reading the csv into a DataFrame.

Step 4: Assembling the columns into a feature vector

Transformer to assemble the columns used in the classification by the XGBoost Estimatorinto a vector:

Step 5: Adding the XGBoost estimator

Defining the Estimator that will produce the model. The settings of the estimator can be defined in a map. 
I can also set up the features and the label columns:

Step 6: Building the pipeline and the classifier

After I created all the individual steps, I can define the actual pipeline and the order of the operations:

