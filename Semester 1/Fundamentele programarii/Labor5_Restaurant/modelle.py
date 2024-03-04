from repository import *
import functools


class Identifizierbar:
    """
        Identifizierbar (nicht zur Instanziierung gedacht)
        Attribute: id
    """
    def __init__(self, id):
        self.Id = id


class Gericht(Identifizierbar):
    """
        Gericht (erbt von Identifiable, soll nicht instanziiert werden)
        Attribute: Portionsgröße (350g), Preis
    """
    def __init__(self, id, gram, preis):
        super().__init__(id)
        self.Portiongrosse = gram
        self.Preis = preis


class Gekochter(Gericht):
    """
        GekochterGericht (erbt Gericht)
        Zubereitungszeit
    """
    def __init__(self, id, gram, preis, zeit):
        super().__init__(id, gram, preis)
        self.Zubereitungszeit = zeit

    def __str__(self):
        return self.Id + "-> Portiongrosse:" + self.Portiongrosse + "g Preis:" + self.Preis + " RON Zubereitungszeit:" + self.Zubereitungszeit + " Minuten"


class Getrank(Gericht):
    """
        Getränk (erbt Gericht)
        Attribute: Alkoholgehalt
    """
    def __init__(self, id, gram, preis, alkohol):
        super().__init__(id, gram, preis)
        self.Alkoholgehalt = alkohol

    def __str__(self):
        return self.Id + "-> Portiongrosse:" + self.Portiongrosse + "ml Preis:" + self.Preis + " RON Alkoholgehalt:" + self.Alkoholgehalt + "%"


class Kunde(Identifizierbar):
    """
        Kunde (erbt Identifizierbar)
        Attribute: Name, Adresse
    """
    def __init__(self, id, name, adresse):
        super().__init__(id)
        self.Name = name
        self.Adresse = adresse

    def __str__(self):
        return self.Id + "-> Name:" + self.Name + " Adresse:" + self.Adresse


class Bestellung(Identifizierbar):
    """
        Bestellung (erbt Identifizierbar):
        Attribute: Kunden-ID, Liste der IDs für Gerichte, Liste der IDs für Getränke, Gesamtkosten
    """
    def __init__(self, id, kundenId, listeIdGetranke, listeIdGerichte, gesamtkosten):
        super().__init__(id)
        self.Kunden_id = kundenId
        self.Liste_id_gerichte = listeIdGerichte
        self.Liste_id_getranke = listeIdGetranke
        self.Gesamtkosten = gesamtkosten

    def Berechung_der_kosten(self):
        """
            mit hilfe von reduce berechnet er alle nummere von einer liste, wobei dort alle preise von gerichte
            sind die der benutzer erstellt hatte
        """
        liste = []
        gericht_repo = CookedDishRepo('Essen')
        gerichte = gericht_repo.load()
        for el in self.Liste_id_gerichte:
            for gericht in gerichte:
                if el == gericht.Id:
                    liste.append(gericht.Preis)
        berechnung_gericht = functools.reduce(lambda a, b: int(a) + int(b), liste)
        liste = []
        getrank_repo = DrinkRepo('Getranke')
        getranke = getrank_repo.load()
        for el in self.Liste_id_getranke:
            for getrank in getranke:
                if el == getrank.Id:
                    liste.append(getrank.Preis)
        berechnung_getrank = functools.reduce(lambda a, b: int(a) + int(b), liste)
        return int(berechnung_getrank) + int(berechnung_gericht)

    def Rechnung(self):
        """

        """
        print('ID_Bestellung:')
        print(self.Id)
        print('\nID_Kunde:')
        print(self.Kunden_id)
        print('\nListe_ID_Getranke:')
        list(map(print, self.Liste_id_getranke))
        print('\nListe_ID_Gerichte:')
        list(map(print, self.Liste_id_gerichte))
        print('\nGesamtkosten:')
        print(self.Gesamtkosten)

    def Erzeugung_rechunug(self):
        pass
