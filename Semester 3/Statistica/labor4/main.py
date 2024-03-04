#1.Beispiel - Generieren von zufalligen Werten ¨ der ZG: X ∼ 0 1 3 5
# 0.4 0.1 0.3 0.2 Simulation von zufalligen Werten f ¨ ur¨ X in Python:
#Man erstelle das Histogramm der relativen Haufigkeiten f ¨ ur 1000 zuf ¨ allige Werte von ¨ X. Auf demselben Bild
#zeichne man auch die Balken fur die theoretischen Wahrscheinlichkeiten.
print("UBUNG 1")
import numpy
from random import randrange
from matplotlib.pyplot import bar, show, hist, grid, legend, xticks

N=1000
x=[0 ,1 ,3 ,5]
P=[0.4 ,0.1 ,0.3 ,0.2]
rng = numpy.random.default_rng()
r=rng.choice(x, size=N , replace=True, p=P)

z, count = numpy.unique(r, return_counts=True)
d = dict([(z[i], count[i] / N) for i in range(0, 4)])
print(d)
bar(
    z,
    count / N,
    width=0.9,
    color="red",
    edgecolor="black",
    label=" relative Haufigkeiten ",
)
D = {x[i]: P[i] for i in range(len(x))}
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

#. 2Uber die Zufallsgroße ¨ X= Anzahl von Fehlern in den online Artikeln einer bestimmten Zeitung ist bekannt:
#in 25% der Artikeln sind keine Tippfehler, in 35% der Artikel ist ein Tippfehler, in 25% der Artikel sind zwei, in 10% drei und auf dem Rest vier Tippfehler.
#a. Man generiere zufallige Werte f ¨ ur¨ X.
#b.Man schatze anhand der Simulationen die Wahrscheinlichkeit, dass hochstens 1 Tippfehler in einem zuf ¨ allig ¨gewahlten Artikel auftaucht. ¨
#c. Wie viele Tippfehler sind durchschnittlich (im Mittel) in einem online Artikel dieser Zeitung zu erwarten, d.h. man verlangt die Schatzung von dem Erwartungswert ¨ E(X). Man berechne den theoretischen Erwartungswert.
print("UBUNG 2")
import numpy
from random import randrange
from matplotlib.pyplot import bar, show, hist, grid, legend, xticks

N = 1000
x = [0, 1, 2, 3, 4]
P = [0.25, 0.35, 0.25, 0.1, 0.05]
rng = numpy.random.default_rng()
r = rng.choice(x, size=N, replace=True, p=P)
print(r) #??

#Estimarea probabilității prin simulare
z, count = numpy.unique(r, return_counts=True)
d = dict([(z[i], count[i] / N) for i in range(0, 5)])
anzahlMinEinz = count[0] + count[1] #cel mult o eroare
print(anzahlMinEinz / N)
print(numpy.mean(r)) #zufallige werte fur x

#Calculul mediei (Erwartungswert)
sum = 0
for i in range(len(x)):
    sum += x[i] * P[i]
print(sum)
bar(
    z,
    count / N,
    width=0.9,
    color="red",
    edgecolor="black",
    label=" relative Haufigkeiten ",
)

D = {x[i]: P[i] for i in range(len(x))}
bar(
    D.keys(),
    D.values(),
    width=0.7,
    color="blue",
    edgecolor="black",
    label="theoretische Haufigkeiten",
)
legend(loc="upper right")
xticks(range(0, 7))
grid()
show()


#UBUNG3: Gegeben sind n, N ∈ N , p ∈ (0, 1).Die Zufallsgroße ¨ X hat binomiale Verteilung X ∼ Bino(n, p),
#a. Man generiere N (z.B. 500,1000,...) Werte der Zufallsgroße ¨ X mit binomialer Verteilung X ∼ Bino(n, p) mit n = 8, p = 0.5. Man benutze hierfur¨ scipy.stats.binom.rvs.
#b. Man erstelle das Histogramm der relativen Haufigkeiten der zuf ¨ alligen Werten von ¨ X. Auf demselben Bild zeichne man auch die Balken fur die theoretischen Wahrscheinlichkeiten, f ¨ ur diese benutze man ¨
print("UBUNG 3")
from scipy.stats import binom
import numpy
from matplotlib.pyplot import bar, show, hist, grid, legend, xticks

N=1000
n=8; p= 0.5
X = binom.rvs(n, p, size= N)
print("zufallige Werte fur X:")
print(X)

#crearea histogramelor
w=binom.pmf(5,n,p)
print("P( X =",5,f")={w:.6f}")

z, count = numpy.unique(X, return_counts=True)
d = dict([(z[i], count[i] / N) for i in range(0, len(z))])

bar(
    z,
    count / N,
    width=0.9,
    color="red",
    edgecolor="black",
    label=" relative Haufigkeiten ",
)

D = {k: binom.pmf(k, n, p) for k in range(0, n + 1)};

bar(
    D.keys(),
    D.values(),
    width=0.9,
    color="blue",
    edgecolor="black",
    label=" theoretische Haufigkeiten ",
)

legend(loc="upper right")
xticks(range(0, 8))
grid()
show()

#ubung4:  einem Computerpool sind 7 Rechner. Die Wahrscheinlichkeit, dass ein neuer Viruseinen Rechner angreift ist 0.4, unabhangig von anderen Rechnern. ¨
# Welche ist die Wahrscheinlichkeit, dass der Virus:
#a) hochstens 3 Rechner; ¨
#b) mindestens 4 Rechner;
#c) genau 4 Rechner angreift?
#Man gebe die Antworten anhand Simulationen (binom.rvs) und vergleiche diese mit den theoretischen Wahrscheinlichkeiten (hierfur benutze man ¨ binom.cdf, binom.pmf).
from scipy.stats import binom
import numpy
from matplotlib.pyplot import bar, show, hist, grid, legend, xticks
print("UBUNG 4")
N = 1000
n = 7
p = 0.4
X = binom.rvs(n, p, size=N)
z, count = numpy.unique(X, return_counts=True)

theoretical_probabilities = {k: binom.pmf(k, n, p) for k in range(0, n + 1)}

anzahlMaxDrei = count[0] + count[1] + count[2] + count[3]
tanzahlMaxDrei = 0
for k, v in theoretical_probabilities.items():
    if k <= 3:
        tanzahlMaxDrei += v
print("Relativ hochstens 3 Rechner", anzahlMaxDrei / N)
print("P( X <=", 3, f")={tanzahlMaxDrei:.6f}")

anzahlMinVier = count[4] + count[5] + count[6] + count[7]
tanzahlMinVier = 0
for k, v in theoretical_probabilities.items():
    if k >= 4:
        tanzahlMinVier += v
print("Relativ mindesten 4 Rechner", anzahlMinVier / N)
print("P( X >=", 4, f")={tanzahlMinVier:.6f}")

anzahlVier = count[4]
print("Relativ mindesten 4 Rechner", anzahlVier / N)
print("P( X =", 4, f")={binom.pmf(4,n,p):.6f}")

#UBUNG 5: Ein Zufallsgenerator generiert Zufallszahlen fur die Verteilung ¨ Unid(5), d.h
#Sei X die Anzahl der generierten Zahlen, bevor die erste 5 auftaucht.
#A.Man generiere N (z.B. 500,1000,...) zufallige Werte f ¨ ur¨ X und zeichne das Histogramm der relativen Haufigkeiten. ¨
#B. Man schatze zus ¨ atzlich ¨ P(X ≤ 3), P(X > 3) und den Erwartungswert E(X).
from scipy.stats import binom
import numpy
from matplotlib.pyplot import bar, show, hist, grid, legend, xticks
print("ubung5")
def Unid(y):
    N = 10000

    x_values = []
    for _ in range(N):
        x = 0
        while numpy.random.randint(1, 6) != y:
            x += 1
        x_values.append(x)
    return x_values

X = Unid(5)
print(numpy.mean(X))

# Histogramm der relativen Häufigkeiten
z, count = numpy.unique(X, return_counts=True)
d = dict([(z[i], count[i] / len(X)) for i in range(0, len(z))])

bar(
    z,
    count / len(X),
    width=0.9,
    color="red",
    edgecolor="black",
    label="relative Haufigkeiten",
)

legend(loc="upper right")
xticks(range(0, max(X) + 1))
grid()
show()
# Schätzung von P(X ≤ 3) und P(X > 3)
p_x_leq_3 = len([x for x in X if x <= 3]) / len(X)
p_x_gt_3 = len([x for x in X if x > 3]) / len(X)

print(f"P(X <= 3): {p_x_leq_3:.4f}")
print(f"P(X > 3): {p_x_gt_3:.4f}")

# Schätzung des Erwartungswerts E(X)
expected_value = numpy.mean(X)
print(f"Erwartungswert E(X): {expected_value:.4f}")


#ubung 6 Eine Urne enthalt 5 Kugeln mit der Ziffer 1, 6 Kugeln mit der Ziffer 2, 9 Kugeln mit der Ziffer 3. Aus der ¨
#Urne werden 2 Kugeln ohne Zurucklegen gezogen. ¨ X sei die Summe der beiden Kugeln.
#a.Man generiere N (z.B. 500,1000,...) zufallige Werte f ¨ ur¨ X und zeichne das Histogramm der relativen Haufigkeiten. ¨
#Auf demselben Bild zeichne man auch die Balken fur die theoretischen Wahrscheinlichkeiten. ¨
#b.Man schatze zus ¨ atzlich den Erwartungswert ¨ E(X) und berechne den theoretischen Erwartungswert von X
import numpy
from matplotlib.pyplot import bar, show, grid, legend, xticks

def ziehe_kugeln():
    urne = [1] * 5 + [2] * 6 + [3] * 9
    return numpy.random.choice(urne, size=2, replace=False)

N = 1000
X = [numpy.sum(ziehe_kugeln()) for _ in range(N)]

# Histogramm der relativen Häufigkeiten
z, count = numpy.unique(X, return_counts=True)
d = dict([(z[i], count[i] / N) for i in range(0, len(z))])

bar(
    z,
    count / N,
    width=0.9,
    color="red",
    edgecolor="black",
    label="relative Haufigkeiten",
)

# Theoretische Wahrscheinlichkeiten
urne = [1] * 5 + [2] * 6 + [3] * 9
D = {k: (urne.count(k) / len(urne)) * ((urne.count(k) - 1) / (len(urne) - 1)) for k in set(urne)}
bar(
    D.keys(),
    D.values(),
    width=0.9,
    color="blue",
    edgecolor="black",
    label="theoretische Haufigkeiten",
)

legend(loc="upper right")
xticks(range(2, 8))
grid()
show()

# Schätzung des Erwartungswerts E(X)
expected_value = numpy.mean(X)
print(f"Erwartungswert E(X) durch Simulation: {expected_value:.4f}")

# Theoretischer Erwartungswert - DA EROARE
#theoretical_expected_value = sum(val * D[val] for val in D.keys())
#print(f"Theoretischer Erwartungswert von X: {theoretical_expected_value:.4f}")



