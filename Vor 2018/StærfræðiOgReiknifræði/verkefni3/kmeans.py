"""
   The k-means function clusters a dataset using the 
   k-means algorithm, covered in the book in section 4.3.

   Specifically, it implements the following:

   Randomly assign data points to centroids
   Intialize centroids as mean of initially assigned data points
   Repeat until convergence:
     Assign each vector to its nearest centroid (partition_data)
     Update each centroid to be the average of its assigned vectors (update_centroids)
   
   Parameters:
     data - The vectors to cluster, represented as an array of N n-vectors
            (N column vectors of length n).
     k    - The number of clusters

   Returns:
     centroids - The cluster centroids represented as an array of k column vectors of length n.
     labels    - An array of N cluster assignments in the same order as data, so that
                 the 1st data point's cluster assignment is labels[i]. The labels themselves
                 index into the centroids list, i.e. centroids[labels[1]] returns the
                 first data point's centroid.
     losses    - An array containing the value of J at each iteration.

usage:
# A simple 2D dataset with 2 clearly separated clusters
X = np.array([[1,1],[2,1],[1.5,3], [5,5],[6,5],[5.5,7]])
k = 2
centroids, labels, losses = kmeans(data, k)
for i in range(k):
    print("label=", i)
    print(data[labels==i])

import matplotlib.pyplot as plt
plt.scatter(X[:,0], X[:,1], c=labels)
plt.show()

    This is a Python port of kmeans.jl from http://stanford.edu/class/ee103/julia_files/kmeans.jl
"""

import numpy as np

def kmeans(data, k):
    epsilon = 1e-6
    losses = []
    N,n = data.shape
    centroids, labels = initialize_centroids(data, k)
    while True:
        labels = partition_data(data, centroids)
        centroids = update_centroids(data, labels, k, centroids)
        losses.append(loss(data,centroids,labels))
        if len(losses) >= 2 and abs(losses[-1] - losses[-2]) <= epsilon:
            break
    return centroids, labels, np.array(losses)

def partition_data(data, centroids):
    # Assigns each data point to the closest centroid
    N = data.shape[0]
    labels = np.zeros(N, dtype=np.int)
    for i in range(N):
       # calculate the euclidean distance from the ith data point to each centroid
       centroid_distances = pairwise_distance(data[i,:], centroids)
       # find the centroid closest to the ith data point
       labels[i] = np.argmin(centroid_distances)
    return labels

def update_centroids(data, labels, k, old_centroids):
    # Calculates each centroid as the mean of it's data points
    centroids = np.zeros(shape=(k, data.shape[1]))
    for i in range(k):
       #get the data points assigned to the centroid
       centroid_pts = data[labels == i] #[np.nonzero(labels == i), :]
       # if the centroid has assigned data points, set it to be their mean
       if centroid_pts.shape[0] > 0:
           centroids[i,:]=np.average(centroid_pts, axis=0)
       else:
           # otherwise, leave it alone
           centroids[i,:]=old_centroids[i,:]
    return centroids

def loss(data, centroids, labels):
   # Calculates the loss (J) of the given clustering.
   N = data.shape[0]
   sos_dist = 0.0
   for i in range(N):
       #print("label=",labels[i])
       sos_dist += np.linalg.norm(data[i,:] - centroids[labels[i],:])**2
   return sos_dist*(1/N)

def initialize_centroids(data, k):
    # Picks the starting centroids by randomly assigning data points to each centroid.
    N,n = data.shape
    labels = np.random.randint(k, size=N)
    centroids = update_centroids(data, labels, k, np.zeros(shape=(k,n)))
    return centroids, labels

def pairwise_distance(vector, vectors):
    # Calculates the Eudclidean distance between vector and each vectors in 'vectors', returning an array.
    dist = np.zeros(vectors.shape[0])
    for i in range(vectors.shape[0]):
        dist[i] = np.linalg.norm(vector - vectors[i,:])
    return dist