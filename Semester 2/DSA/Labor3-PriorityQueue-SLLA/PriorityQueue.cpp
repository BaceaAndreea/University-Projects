#include "PriorityQueue.h"
#include <exception>
using namespace std;

//HPF Prinzip - zugriff auf dem Element mit dem hochsten prioritat
//constructor
//Komplexitat: alle Faellen theta(nr_elem)= nr_elem.
PriorityQueue::PriorityQueue(Relation r) {
    this->size=0;
    this->cap=6;
    this->elems=new Node[cap];
    for(int i=0; i < this->cap-1; i++){
        elems[i].next= i+1;
    }
    elems[this->cap].next = -1; //arata prin -1 ca este ultimul element
    this->head=-1; //ein leeres slla
    this->firstEmpty=0;
    this->r=r;
}

//Komplexitat: allen Fallen theta(n)-(n=anzahl elem care trebuie mutate) jedes Element im alten Array muss in das neue Array kopiert werden, erfordert n Operationen.
void PriorityQueue::resize(){
    this->cap *=2;
    Node* newElems = new Node[this->cap];
    //ein neues array die cap doppelt hat

    for(int i= this->size; i< this->cap-1; i++){
        newElems[i].next = i+1;
        //eine sll von freien Speicherbereichen erstellen
    }
    newElems[this->cap- 1 ].next=-1; //der lezte next =-1 sa aratam ca e sf
    this->firstEmpty= this->size;

    for(int i=0; i< this->size; i++){
        newElems[i]= this->elems[i];
    }
    delete[] this->elems; //dealokieren
    this->elems=newElems;
}

//komplexitat:theta(1)
void PriorityQueue::insertFirst(int new_elem){
    elems[new_elem].next = head;
    head = new_elem;
}


//Komplexitat:  best theta(1) -sa adauge pe prima positie
//              worst-case: theta(n)-daca introduce pe ukltima pos
//              O(nr_elems)nu stim exact cate elem trebuie sa parcurgem
void PriorityQueue::insertOnPosition(int new_elem){
    int currentNode = head;
    int nextNode = elems[head].next;

    //cat timp nu este la sf si next node are prioritatea mai mare decat new_elem
    while (nextNode != -1 && r(elems[nextNode].e.second, elems[new_elem].e.second)) {
        currentNode = nextNode;
        nextNode = elems[currentNode].next;
    }
    //s-o gasit positia exacta dupa prioritate
    elems[new_elem].next = nextNode;
    elems[currentNode].next = new_elem;
}

//komplexitat: best-case theta(1)
            // worst_case theta(n) //theta(2n)) pentru ca ii si resize si pushu
            //average case: O(n)
void PriorityQueue::push(TElem e, TPriority p) {
    Element elem = make_pair(e, p); //erstellt ein neues parr von elem, prior
    if (size == cap) {
        resize();
    }

    int new_elem = firstEmpty; //cautam unde este loc liber pentru push
    firstEmpty = elems[firstEmpty].next; // Update the firstEmpty value

    elems[new_elem].e = elem; //adaug elem pe pos nrew_elem

    //daca noul element are o prioritate mai mare
    if (isEmpty() || r(p, elems[head].e.second)) {
        insertFirst(new_elem);
    } else {
        insertOnPosition(new_elem);
    }

    size++;
}

//komplexitat: allen fallen theta(1)
//nu depinde de cat de mare e q, returneaza doar primul element
Element PriorityQueue::top() const {
    if (size == 0){ //wenn es leer ist
        throw exception();
    }
    Element top= elems[head].e; //elementul cu cea mai mare prioritate
    return top;

}

//Komplexitat: theta(1) -sterge tot timpu numa headu
//komplexitatea depinde de numărul de articole din q, care poate fi variabilă.
Element PriorityQueue::pop() {
    if (size == 0){ //daca este gol
        throw exception();
    }
    int pos_null= head;
    Element pop= elems[head].e;
    head=elems[head].next; //head primeste urmatorul Nod
    elems[pos_null].next=firstEmpty; //l am sters
    firstEmpty=pos_null; //sa folosim indexul pentru urmatoarea care va fi adaugata
    size--;
    return pop;

}

//komplexitat theta(1) pentru ca doar verifica, daca q-ul este gol sau nu
bool PriorityQueue::isEmpty() const {
    return (size == 0);

}

//Komplexitat: Best-case: theta(1): daca toate au relatia diferita
//             Worst_case theta(n) :chiar daca e pratic 2n pentru ca parcurge doua stive
//             Average-case O(n)
PriorityQueue PriorityQueue::interclasare(PriorityQueue &other) {

    if (this->r != other.r) { //wenn sie die Relation verschieden haben, die relation bestimmt der element mit dem hochsten Priorit
        throw exception();
    }

    PriorityQueue g1(this->r); //facem o nou q cu aceasi relatie si la sfarsit aici se va face interclasarea

    PriorityQueue copie1(this->r);
    PriorityQueue copie2(other.r); //am facut doua q de priopritate in care salvam prioritatiile de la q principal

    while (!this->isEmpty()) { //cat timp q principal nu mi gol atunci il adaugam la copie
        Element e = this->pop(); //mutam toate elementele originale din q principal la alea temporare cu tot cu elem si prioritate (cea mai mare)
        copie1.push(e.first, e.second);
    }
    while (!other.isEmpty()) { //cat timp other g nu mi gol punem toate elementele cu prioritate in copie 2
        Element e = other.pop(); //mutam elementul cu prioritatea cea mai mare
        copie2.push(e.first, e.second);
    }

    //aici facem practic metoda de merge 
    while (!copie1.isEmpty()) {
        Element e = copie1.pop();
        g1.push(e.first, e.second);
    }

    while (!copie2.isEmpty()) {
        Element e = copie2.pop();
        g1.push(e.first, e.second);
    }

    return g1;
};

//Komplexitat: theta(nr_elem) mereu
PriorityQueue::~PriorityQueue() {
    while(size>0){
        pop();
    }
    // Gib allokierten Speicherplatz für Elemente frei
    delete[] elems;
};
