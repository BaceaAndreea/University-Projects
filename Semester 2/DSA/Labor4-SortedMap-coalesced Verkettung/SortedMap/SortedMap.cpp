#include "SMIterator.h"
#include "SortedMap.h"
#include <exception>

using namespace std;

//Komplexitat: theta(1): weil jedes mal werden wir exact n Schritte durchlaufen
SortedMap::SortedMap(Relation r) {
    this->m=10; //cap
    this->firstEmpty=0;
    this->next=new int[m]; //arrayul next de aceasi dimensiune
    this->T=new TElem[m]; //key%m
    this->r=r;

    for(int i=0;i<m;i++){
        next[i]=-1; //fixam toate pe -1;
        T[i]=NULL_TPAIR;
    }
}
//theta(1)
SortedMap SortedMap::copyConstructor() { //copy constructor
    SortedMap newMap(r);
    for (int i = 0; i < m; i++) {
        newMap.add(T[i].first, T[i].second);
    }
    return newMap; //mapul exact cu aceleasi valori
}

//komplexitat: theta(1)
int SortedMap::h(TKey key) const {
    return abs(key % m);

}

//komplexitat: alle fallen theta(n) weil jedes mal werden exact n schritte gemacht um den rise und rehash zu machen
void SortedMap::resize(){

    auto oldM=this->m;
    auto oldT= this->T;
    auto oldNext= this->next;

    this->m *=2;
    this->firstEmpty=0;
    this->T=new TElem[this->m];
    this->next=new int[this->m];

    for(int i=0;i< this->m;i++){
        this->T[i]=NULL_TPAIR;
        this->next[i]=-1;
    }

    for(int i=0; i<oldM;i++){
        this->add(oldT[i].first, oldT[i].second);
    }
    delete[] oldT;
    delete[] oldNext;

}

//Komplexitat - best_case: theta(1): daca nu face riseze si cheia exista pe prima positie
//            - worst_case: theta(n): cand adauga o noua cheie, si sa vada unde este loc liber
//            - average_case: theta(1) amortizat: in acel caz cand Map ul este bine sortat si are locuri libere
//                          pentru a insera o noua cheie cu valoarea sa. si hashul este facut bine.
TValue SortedMap::add(TKey k, TValue v) {
    if(size() == m)
        resize();

    int HT= h(k);
    int current=HT;
    bool already_exists = false;

    //cautam key
    while(current!=-1){
        if(T[current].first == k){
            already_exists = true;
            break;
        }
        current=next[current];
    }

    //if the key already exists in the map, then the value associated to the key is replaced by the new value and the old value is returned
    if(already_exists == true){
        TValue old=T[current].second;
        T[current].second=v;
        return old; //returnam valoarea veche
    }

        //daca key nu este gasita, adaugam (c,v) si returnam false
    else{
        //daca este loc liber
        if(T[HT] == TElem(-111111, -111111))
            T[HT]=TElem(k,v);
        else {
            int temp = HT;
            while(next[temp]!=-1) //cat timp are dupa el mergem mai departe
                temp=next[temp];
            next[temp]=firstEmpty;
            T[firstEmpty]=TElem(k,v);
            firstEmpty=firstEmpty+1;
            while(T[firstEmpty] != TElem(-111111, -111111) && firstEmpty != m-1)
                firstEmpty=firstEmpty+1;
            if(firstEmpty == m-1)
                firstEmpty = -1;//e sfarsitul
        }
        return NULL_TVALUE;
    }
}


//Komplexitat: - best_case: theta(1): wenn er leer ist oder genau an der berechneten Indexposition index in der Tabelle T gefunden wird
//             -worst_case: theta(m): wenn die key nicht existiert oder am ende der Kollsiion verketuung ist
//             - Average_case:theta(m)- weil jedes mal muss er mit hilfe von h den ganzen map durchlaufen um die key zu findem

TValue SortedMap::search(TKey k) const {
//    if(isEmpty())
//        return NULL_TVALUE;
//    int temp=h(k);
//    while(temp != -1){
//        if(T[temp].first==k) {
//            return T[temp].second;
//        }
//        temp=next[temp];
//    }

    int index= this->h(k);

    //daca este golaa
    if(this->T[index] ==TElem(-111111, -111111))
        return NULL_TVALUE;
    //daca este chiar primul element pe care il cautam
    if(this->T[index].first == k)
        return this->T[index].second;

    //cautam cheia peste tot
    while(this->next[index]!=-1 && this->T[index].first !=k)
        index=this->next[index];
    //daca nu am gasit key
    if(this->next[index] == -1)
        return NULL_TVALUE;
    else
        return this->T[index].second;

}

//Komeplxitat:- best_case:theta(1): daca elemen tul este pe h corect din prima
//            - wosrt_case:theta(m): daca se afla la sfarsit de kollision verkettung
//            -average_case: theta(1): daca este bine impartit si hasul bun atunci are komplexitatea theta(n)
            //daca exista multe kollisionene ii theta(n)
TValue SortedMap::remove(TKey k) {
    int HT=h(k);
    int current=HT;

    //cautam the key
    while(current!=-1 && T[current].first !=k)
        current=next[current];

    //daca am gasit cheia, stergem perechea de (k,v)
    if(T[current].first == k){
        TValue value=T[current].second;
        //dam un update la pointers pentru a sterge pereche curenta
        while(next[current]!=-1){
            T[current]=T[next[current]];
            current=next[current];
        }
        T[current]=NULL_TPAIR;

        if(firstEmpty > current)
            firstEmpty=current;

        return value;
    } else{
        //daca nu am gasit key
        return NULL_TVALUE;
    }

}
//Komeplxitat:- best_case:theta(1): daca T este gol si contine doar -111,-111..
//            - worst_case:theta(m): se vor face m iteratii
//            -average_case: theta(1): daca cheile sunt impartite bine si hash ul este bun
int SortedMap::size() const {
    int ct=0;
    for(int i=0; i<m; i++)
        if(T[i] != pair<TKey, TValue>(-111111, -111111))
            ct++;

    return ct;
}

//komplexitat: theta(1)
bool SortedMap::isEmpty() const {
    return size()==0;
}

//komplexitat: theta(1)
SMIterator SortedMap::iterator() const {
    return SMIterator(*this);
}
//komplexitat: theta(1)
SortedMap::~SortedMap() {
    delete[] this->T;
    delete[] this->next;
}

//pre: m hehort Map, start gehort Tkey, end gehort Tkey si intervalul sa fie valid
//post: returniert map-nou (key,value), wenn key gehort [start,end]
//Komplexitat:-best_case: theta(n)
//            -worst_case: theta(n) - daca toate cheile sunt in acel interval
//            -average_case: theta(n)
SortedMap SortedMap::mapNou(TKey start, TKey end) const {
    SortedMap mapnou(r); //utilizând copy-constructor

    //pentru a itera prin perechile cheie-valoare din obiectul SortedMap curent.
    SMIterator it = iterator();
    while (it.valid()) { //cat timp exista cheie valoaree pe parcurs
        TKey current_Key = it.getCurrent().first; //folosim getCurrent sa aflama keya currenta si valoarea acestia
        TValue current_Value = it.getCurrent().second;

        if (current_Key >= start && current_Key <= end) {
            mapnou.add(current_Key, current_Value);
        }
        it.next(); //se avanseaza iteratorul pentru urmatoarea key, valoare
    }
    return mapnou; //returnam un map nou
}

