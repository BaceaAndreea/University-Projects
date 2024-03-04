"""
enthalt alle Funktionen, die fur lesen von der Tastatur und auf den Bildschirm schreiben benutzt werden
"""


def menu():
    print('1. Eine neue Bestellung registrieren' + '\n' +
          '2. Speisekarte entwickeln' + '\n' +
          '3. Kunden entwickeln' + '\n' + '\n' +
          'Welche Operation wollen weiter sie durchfuhren?')


def falsch_operation():
    print('Operation existiert nicht')


def menu1():
    print('1. Eine neue Bestellung registrieren durch Anlegung' + '\n' +
          '2. Eine neue Bestellung registrieren durch Suchen- durch Name' + '\n' +
          '3. Eine neue Bestellung registrieren durch Suchen- durch Adresse' + '\n' + '\n' +
          'Welche Operation wollen sie durchfuhren?')


def menu2():
    print('1. Hinzufugen' + '\n' +
          '2. Anzeigen' + '\n' +
          '3. Aktualisieren' + '\n' +
          '4. Loschen' + '\n' + '\n' +
          'Welche Operation wollen sie durchfuhren?')


def information_lesen(nachricht):
    return input(nachricht)


def menu3():
    print('1. Gericht' + '\n' +
          '2. Getrank' + '\n' + '\n' +
          'Was wollen sie hinzufugen?')


def menu4():
    print('1. Weiter gehen' + '\n' +
          '2. Stoppen' + '\n' + '\n' +
          'Welche Operation wollen sie durchfuhren?')


def menu5():
    print('1. Gericht' + '\n' +
          '2. Getrank' + '\n' + '\n' +
          'Was wollen sie aktualisieren?')


def menu6():
    print('Wleches Nummer wollen sie aktualisieren?')


def menu7():
    print('1. Gericht' + '\n' +
          '2. Getrank' + '\n' + '\n' +
          'Was wollen sie loschen?')


def menu8():
    print('Welches Nummer wollen sie loschen?')


def menu9():
    print('Welche Gerichte wollen sie bestellen?')


def menu10():
    print('Welche Getranke wollen sie bestellen?')


def menu11():
    print('Welche Name suchen sie?')


def menu12():
    print('Welche Adresse suchen sie?')


def menu13():
    print('Welche Kunde wahlen sie?')
