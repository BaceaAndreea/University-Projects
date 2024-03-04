#) a) Man schatze durch wiederholte Simulationen die Wahrscheinlichkeit von dem Ereignis ¨
#A: in einer Gruppe von k = 23 Personen mindestens zwei Personen haben den gleichen Geburtstag.
#Annahme: Das Jahr hat n = 365 Tage.
import numpy
iterationen = 1
count=0
n=100000
while iterationen<n:
    v = numpy.random.randint(1,366,23)
    s = set(v)
    if len(s) < 23:
        count +=1
    iterationen+=1
print("Simulationen", count/n)

#b.Man berechne (in Python) die theoretische Wahrscheinlichkeit P(A) ?
dif=1
for i in range (23):
    dif *=(365-i)/365
print("Theoretische Wahrscheinlichkeit ist: ", 1-dif)


#2.Man mochte die Wahrscheinlichkeit schatzen, dass ein zufallig ¨
#gewahlter Punkt im Quadrat ¨ [0, 1] × [0, 1] sich auch in dem
#eingeschriebenen Kreis befindet (siehe Bild).

#a.Man simuliere N zufallige Punkte im Quadrat und man zahle wie viele im Kreisinneren sind; sei ¨ k diese
#Zahl. Man zeichne auf demselben Bild die zufalligen Punkte mit verschiedenen Farben: diejenigen die im bzw. ¨
#die außhalb des Kreisinneren sind. Hinweis: fur die euklidische Distanz zwischen zwei Punkten kann man ¨
#math.dist benutzen

#(2b) Welches ist die Wahrscheinlichkeit, dass der Punkt im Kreisinneren ist? [Der theoretische Wert ist π 4, bzw.1 die Approximation ist k N .]
#(2c) Anhand von (2a) und (2b) gebe man verschiedene Approximationen von π an. [Hinweis: π ≈ 4 ·k/ N]

import numpy
import matplotlib.pyplot as plt
fig = plt.figure()
plt.axis("square")
plt.axis((0, 1, 0, 1))
X=numpy.random.random(25)
Y=numpy.random.random(25)
cerc = plt.Circle((0.5, 0.5), radius=0.5, fill=False, color='green')
fig.gca().add_patch(cerc)
count=0
for i in range(25):
    if (X[i]-0.5)**2+(Y[i]-0.5)**2<=0.5**2:
        plt.scatter(X[i],Y[i],color = "red")
        count+=1
    else:
        plt.scatter(X[i],Y[i],color = "black")
plt.show()

import numpy
import matplotlib.pyplot as plt
X=numpy.random.random(2500000)
Y=numpy.random.random(2500000)
count=0
for i in range(2500000):
    if (X[i]-0.5)**2+(Y[i]-0.5)**2<=0.5**2:#math.dist!!!!!1
        count+=1
print("pi:", 4*count/2500000)


# Teil 2c
# Testen Sie verschiedene Werte für N, veerschiedene aproximationen  +
for N in [100, 1000, 10000, 100000, 1000000]:
    X = numpy.random.random(N)
    Y = numpy.random.random(N)
    count = sum((X-0.5)**2 + (Y-0.5)**2 <= 0.5**2)
    approximation = 4 * count / N
    print(f"N = {N}, Approximation von π: {approximation}")


#ubung 3
#Für Übung 3 (1) und (2) sollten Sie die Wahrscheinlichkeiten für genau einen bzw. zwei stumpfe Winkel in
# A berechnen, wenn ein Punkt A zufällig innerhalb eines Quadrates gewählt wird.
import numpy
import matplotlib.pyplot as plt
import math

n = 2500000
X = numpy.random.random(n)
Y = numpy.random.random(n)
count1stumpf = 0
count2stumpf = 0

for i in range(n):
    stumpf_count = 0
    point = (X[i], Y[i])

    if math.dist(point, (0, 0))**2 + math.dist(point, (0, 1))**2 < 1:
        stumpf_count += 1
    if math.dist(point, (0, 0))**2 + math.dist(point, (1, 0))**2 < 1:
        stumpf_count += 1
    if math.dist(point, (1, 1))**2 + math.dist(point, (0, 1))**2 < 1:
        stumpf_count += 1
    if math.dist(point, (1, 1))**2 + math.dist(point, (1, 0))**2 < 1:
        stumpf_count += 1

    if stumpf_count == 1:
        count1stumpf += 1
    elif stumpf_count == 2:
        count2stumpf += 1

print("Wahrscheinlichkeit für genau einen stumpfen Winkel:", count1stumpf / n)
print("Wahrscheinlichkeit für genau zwei stumpfe Winkel:", count2stumpf / n)
