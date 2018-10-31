

## Realtime prediction using Spark Streaming, and XGBoost in Scala

### Training
The training is done on a XGBoost classifier using a ML pipeline in Spark. 
The classifier will be saved as an output and will be used in a Spark Structured Streaming realtime app to predict new test data.

#### Step 1: Starting the spark session

I am creating a spark app that will run locally and will use as many threads as there are cores using local[*] :

#### Step 2: Defining a schema

Next I define a schema of the data that I read from the csv. This is usually a better practice than letting spark 
to infer the schema because it consumes less resources and we have total control over the fields.

#### Step 3: Reading the data

Reading the csv into a DataFrame.

#### Step 4: Assembling the columns into a feature vector

Transformer to assemble the columns used in the classification by the XGBoost Estimatorinto a vector:

#### Step 5: Adding the XGBoost estimator

Defining the Estimator that will produce the model. The settings of the estimator can be defined in a map. 
I can also set up the features and the label columns:

#### Step 6: Building the pipeline and the classifier

After I created all the individual steps, I can define the actual pipeline and the order of the operations:

#### Step 7: Saving the model

The input DataFrame will be transformed multiple times and in the end will produce the model trained with our data. Model will be saved in order to be used in the testing phase.

### Prediction

Spark Structured Streaming is used to stream the data from a file. 

#### Step 1: Creating the input read stream
Once again creating the spark session and define a schema for the data and input streaming DataFrame, df. The input path has to be a directory where I store the csv file. It can contain one or more files that have the same schema.

#### Step 2: Loading the XGBoost model

In the object XGBoostModel I load the pre trained model that will be applied for each new batch of rows I read in the stream.

#### Step 3: Defining custom ML sink

In order to be able to apply the classifier to new data I need to create a new sink (the interface between the stream and the output, which in my case is the XGBoost model). For this I need a custom sink ( MLSink ), an abstract sink provider ( MLSinkProvider ) and an implementation of the provider ( XGBoostMLSinkProvider ).

#### Step 4: Writing the data in my custom sink

The last step is to define a query that writes the data in my custom sink. A checkpoint location also has to be defined so that the application “remembers” the latest rows read in the stream in case of a failure. If I run the program each new batch of data will be displayed on the console containing also the predicted labels.


