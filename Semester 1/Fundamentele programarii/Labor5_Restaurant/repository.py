import pickle

#pickle schreibt objekte von einer klasse in einer datei
class DataRepo:
    def __init__(self, datei):
        self.datei = datei

    def save(self, liste):
        """
        speichert eine Liste von Objekten in einer Datei
        """
        picklefile = open(self.datei, 'ab')
        for el in liste:
            pickle.dump(el, picklefile)
        picklefile.close

    def load(self):
        """
        liest eine Liste von Objekten aus einer Datei
        """
        l = []
        picklefile = open(self.datei, 'rb')
        while True:
            try:
                l.append(pickle.load(picklefile))
            except EOFError:
                break
        picklefile.close()
        return l

    def read_file(self):
        """
        liest den Inhalt einer Datei und gibt ihn zuruck
        """
        picklefile = open(self.datei, 'rb')
        ok = 1
        while True:
            try:
                print(ok, pickle.load(picklefile))
            except EOFError:
                break
            ok += 1
        picklefile.close()

    def write_to_file(self, liste):
        """
        speichert eine Liste von Objekten in einer Datei und uberschreibt die Datei
        """
        picklefile = open(self.datei, 'wb')
        for el in liste:
            pickle.dump(el, picklefile)
        picklefile.close

    def convert_to_string(self, liste):
        pass

    def convert_from_string(self, string):
        pass


class CookedDishRepo(DataRepo):
    def __init__(self, datei):
        super().__init__(datei)


class DrinkRepo(DataRepo):
    def __init__(self, datei):
        super().__init__(datei)


class CustomerRepo(DataRepo):
    def __init__(self, datei):
        super().__init__(datei)


class OrderRepo(DataRepo):
    def __init__(self, datei):
        super().__init__(datei)


