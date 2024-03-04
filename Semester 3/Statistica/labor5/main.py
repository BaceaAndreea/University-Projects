#UBUNG1
import matplotlib.pyplot as plt
from scipy.stats import norm
import numpy as np

mu = 199
sigma = 3
N = 1000

Daten = norm.rvs(mu, sigma, N)

print(Daten)
#a) Anhand 1000 simulierten Daten, welche ist im Mittel die abgef¨ullte Menge Tee in einer Packung?
mean_abgefullte_menge = np.mean(Daten)
print(f"Mittelwert: {mean_abgefullte_menge} g")

# Mit welcher Wahrscheinlichkeit werden in einer Packung weniger als 195 g Tee abgef¨ullt?
prob_less_than_195g = norm.cdf(195, mu, sigma)
print(f"Wahrscheinlichkeit 195 g Tee abgefüllt werden: {prob_less_than_195g:.4f}")

prob_between_195g_and_198g = norm.cdf(198, mu, sigma) - norm.cdf(195, mu, sigma)
print(f"Wahrscheinlichkeit zwischen 195 g und 198 g Tee abgefüllt werden: {prob_between_195g_and_198g:.4f}")

prob_more_than_195g = 1 - norm.cdf(195, mu, sigma)
print(f"Wahrscheinlichkeit mehr als 195 g Tee abgefüllt werden: {prob_more_than_195g:.4f}")

# c) Die generierten Daten der Stichprobe sollen in 16 Klassen (Intervallen) eingeteilt werden.
Hfg, Klasse = np.histogram(Daten, bins=16)
print("Anzahl der Daten in jeder Klasse:")

for i, count in enumerate(Hfg):
    lower_bound = Klasse[i]
    upper_bound = Klasse[i + 1]
    print(f"abs. Hfg. {count} - Klasse [{lower_bound:.4f}, {upper_bound:.4f}]")

# Histogramm der relativen Häufigkeiten
plt.hist(Daten, bins=16, density=True, edgecolor="black", label="rel. Hfg.")

# Dichtefunktion der N(µ, σ^2) Verteilung
x = np.linspace(min(Daten)-2, max(Daten)+2, 100)
density_line = norm.pdf(x, mu, sigma)

plt.plot(x, density_line, color="red", label="Dichtefunktion")

# Vergleich mit den theoretischen Wahrscheinlichkeiten
plt.title('Histogram und Dichtefunktion verglichen mit theoretischen Wahrscheinlichkeiten')
plt.xlabel('Menge Tee (g)')
plt.legend()

plt.show()

#UBUNG2
print("ubung2")
import matplotlib.pyplot as plt
from scipy.stats import expon
import numpy as np

alpha = 1/12
N = 1000

# (a) Man simuliere N = 1000 Daten f¨ur eine Stichprobe.
Daten = expon.rvs(loc=0, scale=1/alpha, size=N)

# (a) Welche ist die durchschnittliche Druckzeit f¨ur das Drucken eines Plakats?
mean_druckzeit = np.mean(Daten)
print(f"Durchschnittliche Druckzeit: {mean_druckzeit:.4f} Sekunden")

# (b) Man zeichne ein Histogramm mit 12 Klassen f¨ur die simulierten Daten
# und auf demselben Bild zeichne man die Dichtefunktion scipy.stats.expon.pdf(x,loc=0,scale=1/alpha).
plt.hist(Daten, bins=12, density=True, edgecolor="black", label="relative Hfg.")
x = np.linspace(0, max(Daten)+2, 100)
density_line = expon.pdf(x, loc=0, scale=1/alpha)
plt.plot(x, density_line, color="red", label="Dichtefunktion")
plt.title('Histogramm und Dichtefunktion')
plt.xlabel('Druckzeit (Sekunden)')
plt.legend()
plt.show()

# (c) Man sch¨atze danach die Wahrscheinlichkeiten P(T < 20), P(T > 10), P(10 < T < 30).
# Man vergleiche das Ergebnis mit den theoretischen Wahrscheinlichkeiten
prob_less_than_20 = expon.cdf(20, loc=0, scale=1/alpha)
prob_more_than_10 = 1 - expon.cdf(10, loc=0, scale=1/alpha)
prob_between_10_and_30 = expon.cdf(30, loc=0, scale=1/alpha) - expon.cdf(10, loc=0, scale=1/alpha)
print(f"Wahrscheinlichkeit T < 20: {prob_less_than_20:.4f}")
print(f"Wahrscheinlichkeit T > 10: {prob_more_than_10:.4f}")
print(f"Wahrscheinlichkeit 10 < T < 30: {prob_between_10_and_30:.4f}")

# (d) Die generierten Daten der Stichprobe wurden in 12 Klassen (Intervallen) eingeteilt.
# Man z¨ahle und gebe an wie viele Daten in jeder Klasse sind,
# und man zeichne auf einem neuen Bild das entsprechende Histogramm der absoluten H¨aufigkeiten.
absolute_Hfg, Klasse = np.histogram(Daten, bins=12)
print("Anzahl der Daten in jeder Klasse:")
for i, count in enumerate(absolute_Hfg):
    lower_bound = Klasse[i]
    upper_bound = Klasse[i + 1]
    print(f"abs. Hfg. {count} - Klasse [{lower_bound:.4f}, {upper_bound:.4f}]")
plt.hist(Daten, bins=12, density=False, edgecolor="black", label="absolute Hfg.")
plt.title('Histogramm der absoluten H¨aufigkeiten')
plt.xlabel('Druckzeit (Sekunden)')
plt.show()

# (e) Auf einem anderen Bild zeichne man auf dem Intervall [0, 10] die Dichtefunktion der Exp(1) Verteilung.
x_interval = np.linspace(0, 10, 100)
density_line_exp1 = expon.pdf(x_interval, loc=0, scale=1)
plt.plot(x_interval, density_line_exp1, color="blue", label="Exp(1) Dichtefunktion")
plt.title('Dichtefunktion der Exp(1) Verteilung')
plt.xlabel('Druckzeit (Sekunden)')
plt.legend()
plt.show()

#UBUNG3
print("UBUNG3")
import numpy as np

num_simulations = 10000
winnings = []

for _ in range(num_simulations):
    # Zufällige Auswahl von Geburtstagen für 6 Personen
    birthdays = np.random.randint(1, 13, size=6)

    # Überprüfen, ob mindestens zwei Personen den gleichen Geburtstag haben
    if len(birthdays) != len(set(birthdays)):
        winnings.append(6)  # Professor X gewinnt die Wette
    else:
        winnings.append(-6)  # Professor X verliert die Wette

average_winnings = np.mean(winnings)
win_probability = len([w for w in winnings if w > 0]) / num_simulations

# Ergebnisse ausgeben
print(f"Durchschnittlicher Gewinn oder Verlust: {average_winnings} €")
print(f"Wahrscheinlichkeit, die Wette zu gewinnen: {win_probability:.4f}")

#UBUNG4
print("UBUNG4")
import numpy as np

# a) Theoretische Wahrscheinlichkeitsverteilung von X
p_white = 4 / 10
p_black = 6 / 10

prob_X_0 = p_white
prob_X_1 = p_black * p_white #DIE ERSTE KUGEL SCHWARZ IST UND DIE ZWEITE WEISS
prob_X_2 = (p_black ** 2) * p_white
prob_X_3 = p_black ** 3
print(f"Theoretische Wahrscheinlichkeitsverteilung von X:")
print(f"P(X = 0) = {prob_X_0:.4f}")
print(f"P(X = 1) = {prob_X_1:.4f}")
print(f"P(X = 2) = {prob_X_2:.4f}")
print(f"P(X = 3) = {prob_X_3:.4f}")


# b) Theoretische Wahrscheinlichkeitsverteilung von Y
# Der Spieler erh¨alt 30 Punkte, wenn er drei schwarze Kugeln gezogen hat. Er erh¨alt 25 Punkte, wenn er
#zwei schwarze Kugeln zieht. In allen anderen F¨allen verliert er 5 Punkte
points_Y_0 = -5
points_Y_1 = 25
points_Y_2 = 30

prob_Y_0 = prob_X_0
prob_Y_1 = prob_X_1
prob_Y_2 = prob_X_2
prob_Y_3 = prob_X_3

# Theoretischer Erwartungswert von Y
E_Y = (prob_Y_0 * points_Y_0) + (prob_Y_1 * points_Y_1) + (prob_Y_2 * points_Y_2) + (prob_Y_3 * points_Y_2)

print(f"Theoretischer Erwartungswert von Y: {E_Y} Punkte")

# Simulationen für X
N_simulations = 10000
simulated_X_values = np.random.choice([0, 1, 2, 3], size=N_simulations, p=[prob_X_0, prob_X_1, prob_X_2, prob_X_3])

# Simulationen für Y
simulated_Y_values = np.where(simulated_X_values == 3, 30, np.where(simulated_X_values == 2, 25, -5))

# Mittlere Punktezahl des Spielers
average_points = np.mean(simulated_Y_values)

# Ergebnisse ausgeben
print(f"\nMittlere Punktezahl des Spielers (Simulation): {average_points} Punkte")

# Vergleich mit dem theoretischen Ergebnis
print(f"Theoretischer Erwartungswert von Y: {E_Y} Punkte (Theorie)")

#ubung5
print("\nUBUNG5")
import numpy as np
from scipy.stats import uniform

# Simulation von 1000 zufälligen Punkten aus dem Quader
N = 1000
x_values = uniform.rvs(loc=-1, scale=2, size=N)
y_values = uniform.rvs(loc=-1, scale=2, size=N)
z_values = uniform.rvs(loc=-1, scale=2, size=N)

# Berechnung der Distanz jedes Punktes zum Ursprung
distances = np.sqrt(x_values**2 + y_values**2 + z_values**2)

# Schätzung des Erwartungswerts und der Varianz von D
mean_distance = np.mean(distances)
variance_distance = np.var(distances)

# Ergebnisse ausgeben
print(f"Erwartungswert von D: {mean_distance:.4f}")
print(f"Varianz von D: {variance_distance:.4f}")

