#UBUNG1
print("UBUNG1")
import numpy as np
import random

X = np.array([4, 5, 6, 7, 8, 9, 10])
P = np.array([0.05, 0.1, 0.1, 0.35, 0.2, 0.1, 0.1])

N=10000

D = np.array(random.choices(X, weights=P, k=N))

#a
mean_X = np.mean(D)
var_X = np.var(D)

P_X_mme_7 = np.sum(D <= 7) / len(D)
P_X_mm_4 = np.sum(D > 4) / len(D)

print("\nSimulierte Werte:")
print("E(X):", mean_X)
print("V(X):", var_X)
print("P(X <= 7):", P_X_mme_7)
print("P(X > 4):", P_X_mm_4)

#b
mean_th = np.sum(X * P)
var_th = np.sum(X**2 * P) - mean_th**2
P_th_leq_7 = np.sum(P[X <= 7])
P_th_gt_4 = np.sum(P[X > 4])

print("\nTheoretische Werte:")
print("Erwartungswert E(X):", mean_th)
print("Varianz V(X):", var_th)
print("P(X <= 7):", P_th_leq_7)
print("P(X > 4):", P_th_gt_4)

#c
import matplotlib.pyplot as plt

d,c=np.unique(D,return_counts=True)
plt.bar(d,c/N,width=0.8, edgecolor='black', color='blue')
plt.title('relativen Häufigkeiten')
plt.show()


plt.bar(d,c,width=0.8, edgecolor='black', color='red')
plt.title('absolute Häufigkeiten')
plt.show()


#UBUNG 2
print("ubung2")
import numpy as np
from scipy.stats import uniform
import math

N = 1000

quader_min = -2
quader_max = 2

kugel_zentrum = np.array([0, 0, 0])
kugel_radius = 2

# a)
x = uniform.rvs(loc=quader_min, scale=quader_max-quader_min, size=N)
y = uniform.rvs(loc=quader_min, scale=quader_max-quader_min, size=N)
z = uniform.rvs(loc=quader_min, scale=quader_max-quader_min, size=N)
X = [math.dist([x[i],y[i],z[i]],[2,2,2]) for i in range(len(x))]
mean_X = np.mean(X)

print("a) Schätzung des Erwartungswerts von X:", mean_X)

# b)
# Schätzung der Wahrscheinlichkeit, dass ein zufällig gewählter Punkt im Quader sich auch im Inneren der Kugel befindet
points_inside_sphere = sum(np.array(X) <= kugel_radius)
probability_inside_sphere = points_inside_sphere / N

print("b) Wahrscheinlichkeit, dass ein Punkt im Quader in der Kugel liegt:", probability_inside_sphere)

# c)
# Berechnung der theoretischen Wahrscheinlichkeit
volume_quader = (quader_max - quader_min) ** 3
volume_sphere = (4/3) * np.pi * kugel_radius**3
theoretical_probability_inside_sphere = volume_sphere / volume_quader

print("c) Theoretische Wahrscheinlichkeit, dass ein Punkt im Quader in der Kugel liegt:", theoretical_probability_inside_sphere)

#UBUNG3
print("ubung3")
import numpy as np
from scipy.stats import uniform, expon

# a) Schätzung der Wahrscheinlichkeit, dass das Drucken mehr als 5 Sekunden dauert

p_D1 = 0.4
p_D2 = 0.6

T1_distribution = expon(scale=1/5)
T2_distribution = uniform(loc=4, scale=2) #[4, 4+2] liegt, also [4, 6].

T1 = T1_distribution.rvs(size=1000)
T2 = T2_distribution.rvs(size=1000)

T_mm_5 = (T1 > 5) | (T2 > 5)
P_mm_5 = np.mean(T_mm_5)

print("a) Wahrscheinlichkeit Drucken mehr als 5 Sekunden:", P_mm_5)

# b)

mean_T1 = np.mean(T1)
std_T1 = np.std(T1)

mean_T2 = np.mean(T2)
std_T2 = np.std(T2)

print("\nb)T1:")
print("   Erwartungswert:", mean_T1)
print("   Standardabweichung:", std_T1)

print("\n T2:")
print("   Erwartungswert:", mean_T2)
print("   Standardabweichung:", std_T2)

#UBUNG4
print("ubung4")
import numpy as np
from scipy.stats import uniform

N = 100000

B_values = uniform.rvs(loc=-1, scale=2, size=N)
C_values = uniform.rvs(loc=-1, scale=2, size=N)

# a)
real_roots_probability = np.mean(B_values**2 - 4*1*C_values >= 0)
print("a) Wahrscheinlichkeit beide Wurzeln reell:", real_roots_probability)


# b)EROARE
#positive_roots_probability = np.mean(np.logical_and(B_values**2 - 4*C_values >= 0, (-B_values - np.sqrt(B_values**2 - 4*1*C_values)) > 0))
#print("b) Wahrscheinlichkeit beide Wurzeln positiv:", positive_roots_probability)

#UBUNG5
print("UBUNNG 5") #Ziehungen mit Zur¨ucklegen
#In einer Urne sind 20 rote Kugeln, 15 blaue Kugeln, 5 gr¨une Kugeln und 10 schwarze Kugeln. Man
#simuliere N(= 200, 1000, . . .) Ziehungen mit Zur¨ucklegen und zeige (print) die relative H¨aufigkeit an mit
#welcher jede Farbe auftaucht. Man vergleiche die theoretischen Resultate mit den Ergebnissen aus den
#Simulationen. Man gebe die Ergebnisse der ersten 10 Ziehungen an!
import numpy as np

anzahl_rote_kugeln = 20
anzahl_blaue_kugeln = 15
anzahl_gruene_kugeln = 5
anzahl_schwarze_kugeln = 10

gesamtanzahl_kugeln = anzahl_rote_kugeln + anzahl_blaue_kugeln + anzahl_gruene_kugeln + anzahl_schwarze_kugeln

N = 1000

ziehungen = np.random.choice(['rot', 'blau', 'gruen', 'schwarz'], size=(N, 10),
                              p=[anzahl_rote_kugeln/gesamtanzahl_kugeln,
                                 anzahl_blaue_kugeln/gesamtanzahl_kugeln,
                                 anzahl_gruene_kugeln/gesamtanzahl_kugeln,
                                 anzahl_schwarze_kugeln/gesamtanzahl_kugeln])

print("Ergebnisse 10 Ziehungen:")
print(ziehungen[:10, :])



print("\nTheoretische relative Häufigkeiten:")
print("Rot:", anzahl_rote_kugeln / gesamtanzahl_kugeln)
print("Blau:", anzahl_blaue_kugeln / gesamtanzahl_kugeln)
print("Grün:", anzahl_gruene_kugeln / gesamtanzahl_kugeln)
print("Schwarz:", anzahl_schwarze_kugeln / gesamtanzahl_kugeln)

#relative Häufigkeit
relative_haeufigkeit_rot = np.sum(ziehungen == 'rot') / (N * 10)
relative_haeufigkeit_blau = np.sum(ziehungen == 'blau') / (N * 10)
relative_haeufigkeit_gruen = np.sum(ziehungen == 'gruen') / (N * 10)
relative_haeufigkeit_schwarz = np.sum(ziehungen == 'schwarz') / (N * 10)

print("\nSimulierte relative Häufigkeiten (nach", N, "Ziehungen):")
print("Rot:", relative_haeufigkeit_rot)
print("Blau:", relative_haeufigkeit_blau)
print("Grün:", relative_haeufigkeit_gruen)
print("Schwarz:", relative_haeufigkeit_schwarz)

#UBUNG6                OHNE ZURUKLEGEN
print("ubung 6")
#. Eine Urne enth¨alt 10 Kugeln mit der Ziffer 0, 20 Kugeln mit der Ziffer 1, 20 Kugeln mit der Ziffer
#2. Aus der Urne werden 3 Kugeln ohne Zur¨ucklegen gezogen. X sei das Produkt der 3 erhaltenen Zahlen.
#Man sch¨atze anhand Simulationen den Erwartungswert und die Varianz von X! Man erstelle anhand
#Simulationen das Histogramm der absoluten H¨aufigkeiten f¨ur die Werte von X! In ein zweites Bild zeichne
#man ein zweites Histogramm mit den (theoretischen) Wahrscheinlichkeiten der ZG X.

import numpy as np
import matplotlib.pyplot as plt

anzahl_0 = 10
anzahl_1 = 20
anzahl_2 = 20

gesamtanzahl = anzahl_0 + anzahl_1 + anzahl_2

N = 100000

ziehungen = np.random.choice([0, 1, 2], size=(N, 3), p=[anzahl_0 / gesamtanzahl,
                                                        anzahl_1 / gesamtanzahl,
                                                        anzahl_2 / gesamtanzahl])
produkte = np.product(ziehungen, axis=1)

erwartungswert_X = np.mean(produkte)
varianz_X = np.var(produkte)

print("Geschätzter Erwartungswert von X:", erwartungswert_X)
print("Geschätzte Varianz von X:", varianz_X)


p, c = np.unique(produkte, return_counts=True)
plt.bar(p, c, width=1, edgecolor='black', color='blue')
plt.title('Absolute Häufigkeiten (Simulation)')
plt.show()

theoretische_wahrscheinlichkeiten = [(anzahl_0 / gesamtanzahl) ** 3,
                                     (anzahl_1 / gesamtanzahl) ** 3,
                                     (anzahl_2 / gesamtanzahl) ** 3]

plt.bar([0, 1, 2], theoretische_wahrscheinlichkeiten, width=1, edgecolor='black', color='red')
plt.title('Theoretische Wahrscheinlichkeiten von X')
plt.xlabel('Wert von X')
plt.ylabel('Wahrscheinlichkeit')
plt.show()


#UBUNG 7         OHNE ZURUCKLEGUNG
print('UBUNG 7')
import numpy as np
from itertools import permutations

# Definiere die Anzahl der Kugeln jeder Farbe
anzahl_blaue = 3
anzahl_rote = 3
anzahl_weise = 4

# Definiere die Gewinne
gewinn_alle_gleiche_farbe = 5
gewinn_unterschiedliche_farben = 2
verlust = 1

# Simulationsparameter
N = 10000

# Simuliere N Spiele
simulierte_gewinne_verluste = []
for _ in range(N):
    # Ziehe nacheinander 3 Kugeln ohne Zurücklegen
    gezogene_kugeln = np.random.choice(['blau', 'rot', 'weiss'], size=3, replace=True)

    # Berechne den Gewinn oder Verlust für das aktuelle Spiel
    if len(set(gezogene_kugeln)) == 1:
        gewinn_verlust = gewinn_alle_gleiche_farbe
    elif len(set(gezogene_kugeln)) == 3:
        gewinn_verlust = gewinn_unterschiedliche_farben
    else:
        gewinn_verlust = -verlust

    simulierte_gewinne_verluste.append(gewinn_verlust)

# Berechne den durchschnittlichen Gewinn oder Verlust pro Spiel aus den Simulationen
durchschnittlicher_gewinn_verlust_simuliert = np.mean(simulierte_gewinne_verluste)

# Theoretische Wahrscheinlichkeiten
# Berechne alle möglichen Kombinationen der 3 gezogenen Kugeln
alle_kombinationen = set(permutations(['blau']*anzahl_blaue + ['rot']*anzahl_rote + ['weiss']*anzahl_weise, 3))

# Berechne die theoretischen Wahrscheinlichkeiten für jedes Szenario
p_alle_gleiche_farbe = sum(1 for kombination in alle_kombinationen if len(set(kombination)) == 1) / len(alle_kombinationen)
p_unterschiedliche_farben = sum(1 for kombination in alle_kombinationen if len(set(kombination)) == 3) / len(alle_kombinationen)
p_verlust = 1 - p_alle_gleiche_farbe - p_unterschiedliche_farben

# Berechne den theoretischen durchschnittlichen Gewinn oder Verlust pro Spiel
durchschnittlicher_gewinn_verlust_theoretisch = (p_alle_gleiche_farbe * gewinn_alle_gleiche_farbe +
                                                 p_unterschiedliche_farben * gewinn_unterschiedliche_farben +
                                                 p_verlust * (-verlust))

# Ergebnisse ausgeben
print("Durchschnittlicher Gewinn/Verlust (simuliert):", durchschnittlicher_gewinn_verlust_simuliert)
print("Durchschnittlicher Gewinn/Verlust (theoretisch):", durchschnittlicher_gewinn_verlust_theoretisch)


#UBUNG 9
print("UBUNG 9")
#a) Man generiere alle Permutationen vom String mutig. Wie viele solche Permutationen gibt es?
#b) Man generiere zwei zuf¨allige Permutationen vom String mutig.
#c) Man generiere alle Variationen mit vier Buchstaben aus dem String mutig. Wie viele solche Variationengibt es?
#d) Man generiere alle Kombinationen mit zwei Buchstaben aus dem String mutig. Wie viele solche Kombinationen gibt es?

print("ubung 9")
from itertools import permutations

string = "mutig"

# a)
all_permutations = list(permutations(string))
num_permutations = len(all_permutations)

print("a) Anzahl der Permutationen:", num_permutations)

import random

# b)
random_permutations = random.sample(all_permutations, 2)

print("b) Zufällige Permutationen:", random_permutations)

# c)
variations_four_letters = list(permutations(string, 4))
num_variations_four_letters = len(variations_four_letters)

print("c) Anzahl der Variationen mit vier Buchstaben:", num_variations_four_letters)


from itertools import combinations

# d)
combinations_two_letters = list(combinations(string, 2))
num_combinations_two_letters = len(combinations_two_letters)

print("d) Anzahl der Kombinationen mit zwei Buchstaben:", num_combinations_two_letters)

