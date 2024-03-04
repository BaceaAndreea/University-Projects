#include "SortedBag.h"
#include "SortedBagIterator.h"

//erstellt einen leeren Sorted Bag, wo die Elemente basierend auf der Relation rel geordnet werden


//Komplexität:-Best Case Θ(1)
//            -Worst Case Θ(1)
//            -Average Case Θ(1)
//in allen fallsen gleich welchh es keine Operationen durchfuhrt
SortedBag::SortedBag(Relation r) {
	//TODO - Implementation
    this->len = 10;
    this->v=new TElem[len];
    //n ist 0 am anfang, also ist leer
    //einen leeren SortedBag
    this->n = 0;
    this->r = r; // die currente Relation, sie wird als Parameter
    //this->v2=new TElem[len];
}


//Komplexität:-Best Case Θ(1) (lange  von v kleiner als len ist)
//            -Worst Case Θ(n) (lange zu gross ist wird die schliefe durdchgefuhrt und in einen neuen array getan)
//            -Average Case Θ(1) (hangt von resize ab)
void SortedBag::resize(){
    TElem *vnou=new int[2*len];
    //wir uberschreiben alle elemente von den vorrigen array in den neuen array
    //Alle Elemente separat zu speichern (in der entsprechenden Reihenfolge)
    for (int i=0;i<n;i++)
        vnou[i]=v[i];
    //machen 2*capacity
    len=2*len;
    delete[] v;
    //v bekommt vnou mit den neuen lange(capacity)
    v=vnou;
}
//Komplexität:-Best Case O(1) (wenn e este cel mai mic si va fi inserat pe primu loc)
//            -Worst Case O(n) (daca e va fi inserat la sfarsit de to
//            -Average Case O(n/2,n) (de ex se va insera la jumatatea arrayului si asa va parcurge n/2 pasi sau chiar n)
void SortedBag::add(TComp e) {
	//TODO - Implementation
    //wenn lange = capacity dann resize
    if (n == len)
        resize();
    //wir fugen den tcomp am ende des array um
    if(n == 0) //wenn lange=0 il adaugam pe primu loc
        v[0]=e;
    else {
        int ct = size(); //contor bekommt size (array)
        //se compara daca e este mai mic ca primul element si daca da se insereaza pe prima pozitie
        if (r(e, v[0])) {
            for (int i = ct - 1; i >= 0; i--)
                v[i + 1] = v[i];
            v[0] = e;
            //daca e este mai mare ca ultimul element atunci se va adauga la sfarsit
        } else if (r(v[ct - 1], e))
            v[ct] = e;
        else {
            int i;
            //caut prin acest for unde mai exact ar trebui inserat e in SortgedBag
            for (i = 1; i < ct - 1; i++)
                if (r(e, v[i]))
                    break;
            //o sa se mute toate elemente la dreapta pt a face loc pentru e, si va fi inserat la positia buna
            for (int k = ct - 1; k >= i; k--)
                v[k + 1] = v[k];
            v[i] = e;
        }
    }
    // se va inkrementa n
    n++;

}

//Komplexität:-Best Case Θ(1) (wenn das erste Element e ist, wird die Schleife nur einmal gefuhrt)
//            -Worst Case O(n^2) (wenn er das letzte ist oder nicht existiert und geht n Schritte um e zu finden)
//O(n^2)letzte Element+die Verschiebung von Elemente
//            -Average Case O(n) oder O(n/2) (parcurge schlieife de n/2 ori pentru ca poate sa eiste pe fiecare pozitie din vector)
bool SortedBag::remove(TComp e) {
	//TODO - Implementation
	bool find=false;
    //wir beginnen von den position 0 um den Element zu finden, welches wir loschen wollen
    int i=0;
    //wenn wir noche elemente haben und wir schon ihn nicht gefunden bhaben
    //cat timp avem elemente de comparat si nu a fost gasit
    while(i<this->n && !find){
        if(this->v[i]==e){
            //wir haben den element gefunden
            find=true;
            //wir loschen den tcomp e, astfel impingem toate elementele la dreapta
            for(int j=i;j<n-1;j++){
                this->v[j]=this->v[j+1];
            }
            //wir decremtieren den n
            this->n--;
        }
        else{
            //wenn wir nichts gefunden haben, gehen wir weiter
            i++;
        }
    }
    return find;
}

//Komplexität:-Best Case Θ(1) (wer der das erste ist)
//            -Worst Case Θ(n)(wenn er der letzte ist oder nicht existiert)
//            -Average Case Θ(n/2) (in der mitte liegt)
bool SortedBag::search(TComp elem) const {
	//TODO - Implementation
	//wir gehen um den array, um elem zu finden
    for(int i=0;i<n;i++){
        //wir vergleichen jedes element von den array mit elem
        //wenn sie gleich sind dann returniert man true, andersfalls false
        if(this->v[i]==elem){
            return true;
        }
    }
    //wenn wir nichts gefunden haben
    return false;
}

//Komplexität:-Best Case Θ(1) (wenn er das erste ist und die Schleife nur einaml durchfuhrt)
//            -Worst Case Θ(n) (wenn er das letzte ist oder nicht existiert)
//            -Average Case Θ(n/2) (daca este sortata probabil elementul sortat este la mijloc)
int SortedBag::nrOccurrences(TComp elem) const {
	//TODO - Implementation
	//Wir initialisieren die Anzahl der Vorkommen mit 0, da wir davon ausgehen, dass das Element nicht in der sortiertes bag vorkommt
    int nrO=0;
    //wir gehen durch den array
    for (int i=0;i<n;i++){
        //wir vergleichen den cuurent element mit dem element von den man die nrO bestimmen will
        //wenn sie gleich sind, dann haben wir eine neue Occu und inkrementieren mit 1
        if(this->v[i]==elem){
            nrO+=1;
        }
    }
    //am ende wir returnieren nrO
    return nrO;
}

//Komplexität:-Best Case Θ(1)
//            -Worst Case Θ(1)
//            -Average Case Θ(1)
int SortedBag::size() const {
	//TODO - Implementation
    //n=lange des array
	return this->n;
}

//Komplexität:-Best Case Θ(1)
//            -Worst Case Θ(1)
//            -Average Case Θ(1)
bool SortedBag::isEmpty() const {
	//TODO - Implementation
	if(n==0)
        return true;
    return false;
}

//Komplexität:-Best Case Θ(1) (hangen von den iterator ab)
//            -Worst Case Θ(1)
//            -Average Case Θ(1)
SortedBagIterator SortedBag::iterator() const {
	return SortedBagIterator(*this);
}

//Komplexität:-Best Case Θ(1) (wenn die grosse des array null ist)
//            -Worst Case Θ(n) (n schritte um alle loschen)
//            -Average Case Θ(n) (wie bei worst case)
SortedBag::~SortedBag() {
	//TODO - Implementation
    delete[] v;

}
