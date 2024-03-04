import random
from random import sample
import math
from math import factorial, perm, comb
import itertools
from itertools import permutations, combinations

#2.a Man erstelle eine Liste mit den Permutationen von ABC.
print(list(permutations("ABC")))

#b.Welches ist die gesamte Anzahl der Permutationen von ABC?
print(perm(3,3))

#c.Man generiere eine zufallige Permutation von MATHE.
print(sample("MATHE", len("MATHE")))

#d. Man generiere eine zufallige Variation mit 3 Buchstaben aus dem String ¨ MATHE.
print(sample("MATHE", 3))

#e. Generiere alle Variationen je 3 Buchstaben aus dem String MATHE
variations = [''.join(p) for p in permutations("MATHE", 3)]
print(variations)

#f. Welches ist die gesamte Anzahl der Variationen je 3 Buchstaben von MATHE?
print(len(variations))

#g. Generiere alle Kombinationen je 3 Buchstaben aus dem String (MATHE)
combinations = [''.join(c) for c in combinations("MATHE", 3)]
print(combinations)

#h. Welches ist die gesamte Anzahl der Kombinationen je 3 Buchstaben von MATHE?
print(len(combinations))

