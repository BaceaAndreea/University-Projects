# In einer Urne sind 6 rote Kugeln, 4 blaue Kugeln und 6 grune Kugeln. Man zieht 3 Kugeln
# hintereinander ohne Zurucklegen. Man betrachtet die Ereignisse:
# A:\mindestens eine rote Kugel wurde entnommen"
# B:\alle entnommenen Kugeln haben dieselbe Farbe".
# eine Simulation des Experiments Aufgabe A1:
# eine Simulation des Experiments Aufgabe A1:
import random
kugeln = random . sample (['r','b','g'], counts =[6 ,4 , 6],k=3 )
N=10

for _ in range(N):
    countA=0
    if 'r' in kugeln: #mindestens eine rote kugeln
        countA += 1
    #print(countA)

    countB=0;
    if len(set(kugeln))==1: #alle kugeln haben dieselbe Farbe
        countB +=1
    #print(countB)

    countAB =0
    #A und B alle kugeln sind rot
    if 'r' in kugeln and len(set(kugeln))==1:
        countAB +=1;
    #print(countAB)

print("Anhand der Simulation P(A)", countA/N)
print("teoretisch P(A)", 1 - (10*9*8) / (16*15*14))

print("Anhand der Simulation P(B)", countB/N)
print("teoretisch P(B)",((6*5*4) + (6*5*4) + (4*3*2)) / (16*15*14))

print("Anhand der Simulation P(AB)", countAB/N)
print("teoretisch P(A und B)",(6*5*4) / (16*15*14))

#print("Anhand der Simulation P(B|A)", countAB/countA)
print("teoretisch P(B|A)",(6*5*4) / (16*15*14 - 10*9*8))


###############################sau asa se poate face
import random
k=1000000
sumRot=0
sumAlle=0
sumAlleRot=0
for i in range(k):
    kugeln = random . sample (['r','b','g'],counts =[6,4,6],k=3)
    if("r" in kugeln):
        sumRot+=1
    if(kugeln==['r','r','r'] or kugeln ==['b','b','b'] or kugeln ==['g','g','g']):
        sumAlle+=1
    if(kugeln==['r','r','r']):
        sumAlleRot+=1
#wenigstens eine rote kugeln
wer= sumRot/k
print("Wenigstens eine rot: " + str(wer))
print("Theoretisch: " + str(1-(10/16 * 9/15 * 8/14)))
#alle die selbe farbe
print("Wenigstens eine rot: " + str(sumAlle/k));
print("Theoretisch: " + str(2*(6/16 * 5/15 * 4/14)+(4/16 * 3/15 * 2/13)))
#alle rot
ar = sumAlleRot/k
print("Alle rot: " + str(ar));
print("Theoretisch"+str(6/16 * 5/15 * 4/14))
#alle rot/wenigstens eine rot
print(ar/wer)
print("Theoretisch"+str((6/16 * 5/15 * 4/14)/(1-(10/16 * 9/15 * 8/14))))

#A2 -  Beispiel Histogramm - Man zeichne ein Histogramm
# der relativen Haufigkeiten der Zahlen die beim N-maligen Wurfeln erhalten wurden
# auf demselben Histogramm zeichne man die theor Wkt fuer das Erhalten der Ziffern 1...6 beim Wurfeln
# was stellt das blau gezeichnete Histogramm dar?

#RELATIVE HAUFIGKEIT
import numpy
from random import randrange
from matplotlib.pyplot import bar, show, hist, grid, legend, xticks

N = 200
daten = [randrange(1, 7) for _ in range(N)]
print(daten)
z, count = numpy.unique(daten, return_counts=True)
d = dict([(z[i], count[i] / N) for i in range(0, 6)])
print(d)
bar(
    z,
    count / N,
    width=0.9,
    color="red",
    edgecolor="black",
    label=" relative Haufigkeiten ",
)
D = dict([(k, 1 / 6) for k in range(1, 7)])
bar(
    D.keys(),
    D.values(),
    width=0.7,
    color="blue",
    edgecolor="black",
    label="theoretische Haufigkeiten",
)
legend(loc="lower left")
xticks(range(0, 7))
grid()
show()

#ABOLSUTE HAUFIGKEIT
import numpy
from random import randrange
from matplotlib.pyplot import bar, show, hist, grid, legend, xticks

N = 200
daten = [randrange(1, 7) for _ in range(N)]
print(daten)
z, count = numpy.unique(daten, return_counts=True)
d = dict([(z[i], count[i]) for i in range(0, 6)])
print(d)
bar(
    z,
    count,
    width=0.9,
    color="red",
    edgecolor="black",
    label=" absolute Haufigkeiten ",
)
D = dict([(k, N/6) for k in range(1, 7)])
bar(
    D.keys(),
    D.values(),
    width=0.7,
    color="blue",
    edgecolor="black",
    label="absolute theoretische Haufigkeiten",
)
legend(loc="lower left")
xticks(range(0, 7))
grid()
show()


#UBUNG3:
#Drei Wurfel werden geworfen. Das Spiel gewinnt derjenige, der die Summe der drei aufgetauchten Zahlen vorhersagt.
#(1) Man simuliere dieses Spiel N-mal (=500, 1000...), man erstelle das Histogramm der relativen
#Haufigkeiten. Auf demselben Bild zeichne man auch die Balken fur die theoretischen Wahrscheinlichkeiten. Man vergleiche die theoretischen Ergebnisse mit den erhaltenen Werten aus den Simulationen.
import numpy
from random import randrange
from matplotlib.pyplot import bar, show, hist, grid, legend, xticks

N = 200
daten1 = [randrange(1, 7) for _ in range(N)]
daten2 = [randrange(1, 7) for _ in range(N)]
daten3 = [randrange(1, 7) for _ in range(N)]
sums = [0]*N
for i in range(N):
    sums[i]=daten1[i]+daten2[i]+daten3[i]
z, count = numpy.unique(sums, return_counts=True)
print(z[count==max(count)],max(count))
#[11] 27(afieaza) indică că suma cu valoarea 11 are cea mai mare frecvență în simularea cu zaruri, apărând de 27 de ori în cele N (în acest caz, 200) de aruncări de zaruri.
bar(
    z,
    count / N,
    width=0.9,
    color="red",
    edgecolor="black",
    label="relative Haufigkeiten",
)

#(2) Auf welche Zahl (oder Zahlen) muss man wetten, um die gr¨oßten Gewinnchancen zu haben?
alleFaelle=0
alleSums=[]
for i in range(1,7):
    for j in range(1,7):
        for k in range(1,7):
            alleSums.append(i+j+k);
            alleFaelle+=1
x,c = numpy.unique(alleSums, return_counts=True)
print(x[c==max(c)],max(c))

#(3) Welches ist die theoretische Wahrscheinlichkeit, dass diese Zahl (oder Zahlen) auftaucht? Man
#vergleiche das theoretische Resultat mit den erhaltenen Ergebnissen der Simulationen.
bar(x,c/alleFaelle,width=0.7,color="blue", edgecolor="black", label="theoretische Wahrscheinlichkeit")
legend(loc="lower left")
xticks(range(3, 19))
grid()
show()

#ubung5
#Welche ist die Wahrscheinlichkeit, dass in einer Gruppe von 5 Personen genau zwei Personen
#Geburtstag im selben Monat haben und die anderen drei Personen verschiedene Geburtstage haben?
#a) Man l¨ose die Aufgabe anhand Simulationen. b) Man gebe die theoretische Wahrscheinlichkeit an.
#Annahme: die Wahrscheinlichkeit, dass eine zuf¨allig gew¨ahlte Person Geburtstag in einem bestimmten Monat hat ist 1/12 .
import random
from math import comb

# a) Simulation
group_size = 5
simulations = 100000

count_success = 0

for _ in range(simulations):
    birthdays = [random.randint(1, 12) for _ in range(group_size)]
    if len(birthdays) == len(set(birthdays)) + 1:
        count_success += 1

simulated_probability = count_success / simulations
print(f"Simulierte Wahrscheinlichkeit: {simulated_probability:.4f}")

# b) Theoretische Berechnung
total_outcomes = 12 ** group_size  # Gesamtanzahl der möglichen Geburtstagskombinationen
favorable_outcomes = 12 * comb(group_size - 1, 1) * 11 ** (group_size - 2)  # Möglichkeiten für genau zwei Personen im selben Monat

theoretical_prob = favorable_outcomes / total_outcomes
print(f"Theoretische Wahrscheinlichkeit: {theoretical_prob:.4f}")


#ubung6
#Man schatze anhand Simulationen die Wahrscheinlichkeit, dass beim zweimaligen Werfen eines
#W¨urfels die Summe der Zahlen mindestens 7 ist (Summe ≥ 7),
#a) unter der Bedingung, dass beim ersten Wurf eine 4 erhalten wurde;
#b) unter der Bedingung, dass beim zweiten Wurf eine gerade Zahl erhalten wurde.
#c) Welche sind die theoretischen Wahrscheinlichkeiten bei a), bzw. b) ?

simulations = 100000
count_success_a = 0

for _ in range(simulations):
    first_throw = random.randint(1, 6)

    if first_throw != 4:
        continue

    second_throw = random.randint(1, 6)
    total_sum = first_throw + second_throw

    if total_sum >= 7:
        count_success_a += 1

simulated_probability_a = count_success_a / simulations
theoretical_probability_a = 1/3  # Da nur drei mögliche Ergebnisse (4, 5, 6) für den zweiten Wurf

print(f"Simulierte Wahrscheinlichkeit (unter Bedingung a): {simulated_probability_a:.4f}")
print(f"Theoretische Wahrscheinlichkeit (unter Bedingung a): {theoretical_probability_a:.4f}")

# b) Simulation und theoretische Wahrscheinlichkeit unter Bedingung b
count_success_b = 0

for _ in range(simulations):
    first_throw = random.randint(1, 6)
    second_throw = random.randint(1, 6)

    if second_throw % 2 != 0:
        continue

    total_sum = first_throw + second_throw

    if total_sum >= 7:
        count_success_b += 1

simulated_probability_b = count_success_b / simulations
theoretical_probability_b = 1/2  # Da die Hälfte der Zahlen gerade ist

print(f"\nSimulierte Wahrscheinlichkeit (unter Bedingung b): {simulated_probability_b:.4f}")
print(f"Theoretische Wahrscheinlichkeit (unter Bedingung b): {theoretical_probability_b:.4f}")




