#I am so sorry...
import pandas as pd
import numpy as np
import math
from scipy import spatial
from sklearn import preprocessing
import keras
from keras.models import Sequential
from keras.layers import Dense, Dropout, Activation
from keras.optimizers import SGD
from keras.utils import to_categorical
from keras.models import load_model
import fileinput

def get_direction(x1, y1, x2, y2):
    
    deg = math.degrees(np.arctan2(x2-x1,y2-y1))
    
    if(np.arctan2(x2-x1,y2-y1) < 0):
        deg+=360
    
    if(deg >= 22.5 and deg < 67.5):
        return 2
    elif(deg >= 67.5 and deg < 112.5):
        return 3
    elif(deg >= 112.5 and deg < 157.5):
        return 4
    elif(deg >= 157.5 and deg < 202.5):
        return 5
    elif(deg >= 202.5 and deg < 247.5):
        return 6
    elif(deg >= 247.5 and deg < 292.5):
        return 7
    elif(deg >= 292.5 and deg < 337.5):
        return 8
    elif(deg >= 337.5 or deg < 22.5):
        return 1

def ProcessRawData(df):
    df['stroke'] = list(pd.qcut(df['timestamp'], len(df)//30, labels=np.arange(0, len(df)//30)))
    
    distX = df.loc[:, 'x'].diff()
    distX[0] = 0
    distY = df.loc[:, 'y'].diff()
    distY[0] = 0
    distTime = df.loc[:, 'timestamp'].diff()
    distTime[0] = 0
    df['distance'] = np.sqrt(np.square(distX) + np.square(distY))
    df['velocity'] = df['distance']/distTime
    df.loc[0, 'velocity'] = 0
    
    velocityList = ['Velocity_Total', 'Velocity_Min', 'Velocity_Max', 'Velocity_STD', 'Velocity_Mean', 'Velocity_Median']
    columns = velocityList
    TrainingData = pd.DataFrame(columns=columns, index=list(np.arange(1, 9)))
    
    df['direction'] = -1
    for x in df['stroke'].unique():
        direction = get_direction(int(df[df['stroke'] == x].head(1)['x']),
            int(df[df['stroke'] == x].head(1)['y']),
            int(df[df['stroke'] == x].tail(1)['x']),
            int(df[df['stroke'] == x].tail(1)['y'])
           )
        df.loc[df['stroke'] == x, 'direction'] = direction
    
    timeStampKeyList = []
    for x in range(0, len(df)):
        if(x == 0):
            continue
        if(df.loc[x, 'keyDown'] == 1 and df.loc[x-1, 'keyDown'] == 1):
            timeStampKeyList.append(df.loc[x, 'timestamp'] - df.loc[x-1, 'timestamp'])
    timeStampKeyList = np.array(timeStampKeyList)
    
    timeStampClickList = []
    for x in range(0, len(df)):
        if(x == 0):
            continue
        if(df.loc[x, 'mouseDown'] == 256):
            timeStampClickList.append(df.loc[x, 'timestamp'] - df.loc[x-1, 'timestamp'])
    timeStampClickList = np.array(timeStampClickList)
    
    
    for x in df['direction'].unique():
        TrainingData.loc[x, 'Velocity_Total'] = df[df['direction'] == x]['velocity'].sum()
        TrainingData.loc[x, 'Velocity_Min'] = df[df['direction'] == x]['velocity'].min()
        TrainingData.loc[x, 'Velocity_Max'] = df[df['direction'] == x]['velocity'].max()
        TrainingData.loc[x, 'Velocity_STD'] = df[df['direction'] == x]['velocity'].std()
        TrainingData.loc[x, 'Velocity_Mean'] = df[df['direction'] == x]['velocity'].mean()
        TrainingData.loc[x, 'Velocity_Median'] = df[df['direction'] == x]['velocity'].median()
    
    X = np.array(TrainingData).flatten()
    
    MouseData = np.array([timeStampClickList.sum(),
                     timeStampClickList.min(),
                     timeStampClickList.max(),
                     timeStampClickList.std(),
                     timeStampClickList.mean(),
                     np.median(timeStampClickList)])

    KeyboardData = np.array([timeStampKeyList.sum(),
                     timeStampKeyList.min(),
                     timeStampKeyList.max(),
                     timeStampKeyList.std(),
                     timeStampKeyList.mean(),
                     np.median(timeStampKeyList)])
    
    return(np.concatenate((X, MouseData, KeyboardData)))
    
