from numpy import loadtxt
from keras.models import Sequential
from keras.layers import Dense
from keras.layers import LeakyReLU
from tensorflow import keras
dataset = loadtxt('all_clusterers_md.csv', delimiter=',')

X = dataset[:,[0,1,2]]
y = dataset[:,3:15]

print(len(y[0]))

# node    size_per_device    complexity    cpu    tps    rtps    wtps    breadps    bwriteps    memory    txpckps    txkbps    rxpckps    rxkbps    duration    energy
model = Sequential()
model.add(Dense(36, input_dim=3, activation='linear'))
act = LeakyReLU(alpha=0.3)
model.add(act)
model.add(Dense(128, activation='linear'))
model.add(Dense(128, activation='linear'))
model.add(Dense(128, activation='linear'))
model.add(Dense(128, activation='linear'))
model.add(Dense(128, activation='linear'))
model.add(Dense(128, activation='linear'))
model.add(Dense(128, activation='linear'))
model.add(Dense(128, activation='linear'))
model.add(Dense(12, activation='linear'))

model.compile(loss='mean_squared_error', optimizer='adam', metrics=['accuracy'])
model.fit(X, y, epochs=2000, batch_size=200, verbose=0)

# dataset2 = loadtxt('x_test.csv', delimiter=',')
# x_test = dataset2[:,[0,1,2]]

# y_pred = model.predict(x_test)

# print(x_test, y_pred)

predictions = model.predict(X)

# evaluate the keras model
_, accuracy = model.evaluate(X, y, verbose=0)
print('Accuracy: %.2f' % (accuracy*100))

for i in range(5):
	print((X[i].tolist(), predictions[i], y[i]))

from sklearn.metrics import confusion_matrix, precision_score, recall_score, f1_score, cohen_kappa_score

# Precision 
precision_score(y, predictions)
# Recall
recall_score(y, predictions)
# F1 score
f1_score(y, predictions)
# Cohen's kappa
#cohen_kappa_score(predictions, y_pred)
