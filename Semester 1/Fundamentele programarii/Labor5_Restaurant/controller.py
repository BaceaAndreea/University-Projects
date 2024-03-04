from ui import *
from modelle import *


def start():
    """
        zeigt das meniu und wahlt eine Nummer, was er machen will
    """
    menu()
    operation(information_lesen("Antwort="))


def operation(antwort):
    """
        apeliert was der Benutzer gewahlt hat
    """
    if antwort == '1':
        bestellung_registrieren()
    elif antwort == '2':
        speisekarte_verwalten()
    elif antwort == '3':
        kunden_verwalten()
    else:
        falsch_operation()


def bestellung_registrieren():
    """
        ruft die Methode fur 1.
    """
    menu1()
    antwort=(information_lesen("Antwort="))
    if antwort == '1':
        registrieren_anlegung()
    elif antwort == '2':
        registrieren_suchen_name()
    elif antwort == '3':
        registrieren_suchen_adresse()
    else:
        falsch_operation()


def speisekarte_verwalten():
    """
       ruft die Operation fur die Speisekarte entwickeln
    """
    menu2()
    antwort=(information_lesen("Antwort="))
    if antwort == '1':
        speisekarte_hinzufugen()
    elif antwort == '2':
        speisekarte_anzeigen()
    elif antwort == '3':
        speisekarte_aktualisieren()
    elif antwort == '4':
        speisekarte_loschen()
    else:
        falsch_operation()


def kunden_verwalten():
    """
        ruft die Operation fur die Kunden entwickeln
    """
    menu2()
    antwort=(information_lesen("Antwort="))
    if antwort == '1':
        kunden_hinzufugen()
    elif antwort == '2':
        kunden_anzeigen()
    elif antwort == '3':
        kunden_aktualisieren()
    elif antwort == '4':
        kunden_loschen()
    else:
        falsch_operation()


def registrieren_anlegung():
    """
        macht einen neune Kunde mit hile von der benutzer und macht eine Bestellung
    """
    liste = []
    liste.append(kunden_informationen())
    kunden_repo = CustomerRepo('Kunden')
    kunden_repo.save(liste)
    bestellung_informationen(liste[0].Id)


def registrieren_suchen_name():
    """
        sucht einen kunde durch den Name und macht was er bestellt
    """
    menu11()
    name = information_lesen('Antwort=')
    name = name.upper()
    kunden_repo = CustomerRepo('Kunden')
    kunden = kunden_repo.load()
    ergebnis = filter(lambda n: name in n.Name.upper(), kunden)
    for el in ergebnis:
        print(el)
    menu13()
    k_id = information_lesen('ID_Kunde=')
    bestellung_informationen(k_id)


def registrieren_suchen_adresse():
    """
        sucht einen kunde durch den adresse und macht was er bestellt
    """
    menu12()
    adresse = information_lesen('Antwort=')
    adresse = adresse.upper()
    kunden_repo = CustomerRepo('Kunden')
    kunden = kunden_repo.load()
    ergebnis = filter(lambda n: adresse in n.Adresse.upper(), kunden)
    for el in ergebnis:
        print(el)
    menu13()
    k_id = information_lesen('ID_Kunde=')
    bestellung_informationen(k_id)


def bestellung_informationen(k_id):
    """
         macht eine neue bestellung mit mehrere informationen von dem benutzer
    """
    b_id = information_lesen('ID_Bestellung=')
    liste = []
    gericht_liste_id = []
    gericht_repo = CookedDishRepo('Essen')
    gericht_repo.read_file() #zeigt den menu auf den Bildschirm
    gerichte = gericht_repo.load() #gerichte enthalt alle Gerichte
    menu9()
    antwort = '1'
    while antwort == '1':
        liste.append(information_lesen("Gericht(Nummer)="))
        menu4()
        antwort = information_lesen("Antwort=")
    if antwort == '2':
        for el in liste:
            gericht_liste_id.append(gerichte[int(el) - 1].Id) #adauga in gericht_lista toate id urile de la mancare
    else:
        falsch_operation()

    liste = []
    getrank_liste_id = []
    getrank_repo = DrinkRepo('Getranke')
    getrank_repo.read_file()
    getranke = getrank_repo.load()
    menu10()
    antwort = '1'
    while antwort == '1':
        liste.append(information_lesen("Getrank(Nummer)="))
        menu4()
        antwort = information_lesen("Antwort=")
    if antwort == '2':
        for el in liste:
            getrank_liste_id.append(getranke[int(el) - 1].Id)
    else:
        falsch_operation()

    liste = []
    neue_bestellung = Bestellung(b_id, k_id, getrank_liste_id, gericht_liste_id, 0)
    berechnung = neue_bestellung.Berechung_der_kosten() #man berechnet wie viel Kosten alle
    neue_bestellung.Gesamtkosten = berechnung #bekommt die Summe
    liste.append(neue_bestellung)
    bestellung_repo = OrderRepo('Bestellungen')
    bestellung_repo.save(liste) #save the order
    neue_bestellung.Rechnung() #Kommt auf dem Bildschirm den Bon


def speisekarte_hinzufugen():
    """
        der Benutzer wahlt was er Hinzufuge wurde
    """
    menu3()
    typ = information_lesen("Antwort=")
    if typ == '1':
        gericht_hinzufugen()
    elif typ == '2':
        getrank_hinzufugen()
    else:
        falsch_operation()


def gericht_hinzufugen():
    """
        speichert das neue gericht welches der benutzer will
    """
    liste = []
    antwort = '1'
    while antwort == '1':
        liste.append(gericht_informationen())
        menu4()
        antwort = information_lesen("Antwort=")
    if antwort == '2':
        gericht_repo = CookedDishRepo('Essen')
        gericht_repo.save(liste)
    else:
        falsch_operation()


def getrank_hinzufugen():
    """
        speichert alle getranke welches der Benutzer tastet
    """
    liste = []
    antwort = '1'
    while antwort == '1':
        liste.append(getrank_informationen())
        menu4()
        antwort = information_lesen("Antwort=")
    if antwort == '2':
        getrank_repo = DrinkRepo('Getranke')
        getrank_repo.save(liste)
    else:
        falsch_operation()


def kunden_hinzufugen():
    """
        speichert alle neue Kunde welcher der Benutzer tastet
    """
    liste = []
    antwort = '1'
    while antwort == '1':
        liste.append(kunden_informationen())
        menu4()
        antwort = information_lesen("Antwort=")
    if antwort == '2':
        kunden_repo = CustomerRepo('Kunden')
        kunden_repo.save(liste)
    else:
        falsch_operation()


def gericht_informationen():
    """
        macht einen neunen gericht mit Hilfe von den benutzer
    """
    id_gericht = information_lesen('ID_Gericht(Name)=')
    portiongrosse = information_lesen('Portion Grosse(Gramm)=')
    preis = information_lesen('Preis(RON)=')
    zubereitsungszeit = information_lesen('Zubereitsungszeit(Minuten)=')
    neuer_gericht = Gekochter(id_gericht, portiongrosse, preis, zubereitsungszeit)
    return neuer_gericht


def getrank_informationen():
    """
        macht einen neunen getrank mit hilfe von Benutzer
    """
    id_getrank = information_lesen('ID_Getrank(Name)=')
    portiongrosse = information_lesen('Portion Grosse(Mililiter)=')
    preis = information_lesen('Preis(RON)=')
    alkoholgehalt = information_lesen('Alkoholgehalt(Prozent)=')
    neuer_getrank = Getrank(id_getrank, portiongrosse, preis, alkoholgehalt)
    return neuer_getrank


def kunden_informationen():
    """
        fugt neue kunde herein
    """
    id_kunde = information_lesen("ID_Kunde(Einzigartige Nummer)=")
    name_kunde = information_lesen("Name(Vorname und Name)=")
    adresse_kunde = information_lesen("Adresse(Stadt und Nummer)=")
    neuer_kunde = Kunde(id_kunde, name_kunde, adresse_kunde)
    return neuer_kunde


def speisekarte_anzeigen():
    """
        zeigt die Speisekarte von Nummer 2
    """
    gericht_repo = CookedDishRepo('Essen')
    gericht_repo.read_file()
    print('\n')
    getrank_repo = DrinkRepo('Getranke')
    getrank_repo.read_file()


def kunden_anzeigen():
    """
        zeigt das menu von kunden entwickeln
    """
    kunden_repo = CustomerRepo('Kunden')
    kunden_repo.read_file()


def speisekarte_aktualisieren():
    """
        was der benutzer aktualisieren mochte
    """
    menu5()
    typ = information_lesen("Antwort=")
    if typ == '1':
        gericht_aktualisieren()
    elif typ == '2':
        getrank_aktualisieren()
    else:
        falsch_operation()


def gericht_aktualisieren():
    """
        man aktualisier den gericht mit Hilfe von der Benutzer
    """
    gericht_repo = CookedDishRepo('Essen')
    gericht_repo.read_file()
    menu6()
    antwort = information_lesen('Antwort=')
    gerichte = gericht_repo.load() #ia din fisier toate objekte si le returneaza ca listaa
    id_gericht = information_lesen('ID_Gericht(Name)=')
    portiongrosse = information_lesen('Portion Grosse(Gramm)=')
    preis = information_lesen('Preis(RON)=')
    zubereitsungszeit = information_lesen('Zubereitsungszeit(Minuten)=')
    neuer_gericht = Gekochter(id_gericht, portiongrosse, preis, zubereitsungszeit)
    gerichte[int(antwort) - 1] = neuer_gericht
    gericht_repo.write_to_file(gerichte)
    gericht_repo.read_file()


def getrank_aktualisieren():
    """
        wir aktualisieren getranke mit hile von Benutzer
    """
    getrank_repo = DrinkRepo('Getranke')
    getrank_repo.read_file()
    menu6()
    antwort = information_lesen('Antwort=')
    getranke = getrank_repo.load()
    id_getrank = information_lesen('ID_Getrank(Name)=')
    portiongrosse = information_lesen('Portion Grosse(Mililiter)=')
    preis = information_lesen('Preis(RON)=')
    alkoholgehalt = information_lesen('Alkoholgehalt(Prozent)=')
    neuer_getrank = Getrank(id_getrank, portiongrosse, preis, alkoholgehalt)
    getranke[int(antwort) - 1] = neuer_getrank
    getrank_repo.write_to_file(getranke)
    getrank_repo.read_file()


def kunden_aktualisieren():
    """
        wir aktualisieren kunden mit hilfe von Benutzer
    """
    kunden_repo = CustomerRepo('Kunden')
    kunden_repo.read_file()
    menu6()
    antwort = information_lesen('Antwort=')
    kunden = kunden_repo.load()
    id_kunde = information_lesen("ID_Kunde(Einzigartige Nummer)=")
    name_kunde = information_lesen("Name(Vorname und Name)=")
    adresse_kunde = information_lesen("Adresse(Stadt und Nummer)=")
    neuer_kunde = Kunde(id_kunde, name_kunde, adresse_kunde)
    kunden[int(antwort) - 1] = neuer_kunde
    kunden_repo.write_to_file(kunden)
    kunden_repo.read_file()


def speisekarte_loschen():
    """
        der benutzer wahlt was er loschen wolle und sperichert eine neue version von der Speisekarte
    """
    menu7()
    typ = information_lesen("Antwort=")
    if typ == '1':
        gericht_loschen()
    elif typ == '2':
        getrank_loschen()
    else:
        falsch_operation()


def gericht_loschen():
    """
        wir loschen einen gericht an
    """
    gericht_repo = CookedDishRepo('Essen')
    gericht_repo.read_file()
    menu8()
    antwort = information_lesen('Antwort=')
    gerichte = gericht_repo.load()
    gericht_repo.write_to_file(filter(lambda n: (gerichte.index(n) + 1) != int(antwort), gerichte))
    #filter lasst in der liste alles was true ist
    gericht_repo.read_file()


def getrank_loschen():
    """
        lost welches getrank der benutzer will
    """
    getrank_repo = DrinkRepo('Getranke')
    getrank_repo.read_file()
    menu8()
    antwort = information_lesen('Antwort=')
    getranke = getrank_repo.load()
    getrank_repo.write_to_file(filter(lambda n: (getranke.index(n) + 1) != int(antwort), getranke))
    getrank_repo.read_file()


def kunden_loschen():
    """
        loscht welches kunde der Benutzer wolle
    """
    kunden_repo = CustomerRepo('Kunden')
    kunden_repo.read_file()
    menu8()
    antwort = information_lesen('Antwort=')
    kunden = kunden_repo.load()
    kunden_repo.write_to_file((filter(lambda n: (kunden.index(n) + 1) != int(antwort), kunden)))
    kunden_repo.read_file()
