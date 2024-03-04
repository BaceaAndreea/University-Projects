#include "MultiMap.h"
#include "MultiMapIterator.h"
#include <exception>
#include <iostream>

using namespace std;

//Komplexitat: Theta(1)
MultiMap::MultiMap() {
    //der MM ist am anfang leer
    this->head = nullptr;
    this->nr_elems = 0;
}

//Komplexitat: Theta(n) - pentru ca nu se stie cate elemente adaugam se poate chiar n sa fie egal cu 1
//se face un nr konstant de pasi si se adauga la Kopf der liste
//Die Funktion fügt ein Schlüssel-Wert-Paar in eine verkettete Liste ein,
void MultiMap::add(TKey c, TValue v) {
    SLLNode* new_node= new SLLNode[1];
    new_node->data= std::make_pair(c,v);
    new_node->next= nullptr;
    //zeiger auf nullptr pentru ca nu exista alte Noduri in lista

    //Fall1: array leeer also wir fugen als der erste, devine head
    if(this->head == nullptr){
        this->head=new_node;
    }
    //Fall2, array nicht leer, fugt am anfang der Liste weil es kein SortedMultiMap ist und es ist efizierter so
    else{
        new_node->next= this->head;
        this->head=new_node;
    }
    this->nr_elems++;

}

// Komeplxitat: Best-case: theta(1)
//              Worst-case: theta(nr_elems) -man kann sein dass man die ganze liste durchlaufen muss um den elem zu finden
//              Average-case: O(nr_elem)
bool MultiMap::remove(TKey c, TValue v) {
	SLLNode* currentNode= this->head;
    SLLNode* prevNode= nullptr;

    if(currentNode == nullptr) //daca e gol
        return false;

    if(currentNode->data.first == c && currentNode->data.second == v){
        this->head=currentNode->next;
        delete currentNode;
        this->nr_elems--;
        return true;
    }
    prevNode=currentNode;
    currentNode=currentNode->next;

    while(currentNode != nullptr){
        if(currentNode->data.first == c && currentNode->data.second == v){
            prevNode->next=currentNode->next;
            delete currentNode;
            this->nr_elems--;
            return true;
        }
        prevNode=currentNode;
        currentNode=currentNode->next;
    }
	return  false;
}

// Komeplxitat: Best-case: theta(1)
//man kann den elem in den ersten knoten finden
//              Worst-case: theta(nr_elems)
//man kann sein dass man die ganze liste durchlaufen muss um den elem zu finden
//              Average-case: O(nr_elem)
vector<TValue> MultiMap::search(TKey c) const {
	SLLNode* current=this->head;
    vector<TValue> result;

    while(current != nullptr){
        if(current->data.first == c){
            result.push_back(current->data.second);
        }
        current=current->next;
    }
	return result;
}

//Komplexitat: Theta(1)
int MultiMap::size() const {
    return this->nr_elems;
}

//Komplexitat: Theta(1)
bool MultiMap::isEmpty() const {
    return this->head == nullptr;
}

MultiMapIterator MultiMap::iterator() const {
	return MultiMapIterator(*this);
}

// Komeplxitat: Best-case: theta(1) -daca contine un elem
//              Worst-case: theta(nr_elems) -man kann sein dass man die ganze liste durchlaufen
//              Average-case: O(nr_elem)
MultiMap::~MultiMap() {
    SLLNode* currentNode= this->head;
    SLLNode* prevNode= nullptr;
    while(currentNode != nullptr){
        prevNode=currentNode;
        currentNode=currentNode->next;
        delete prevNode;
    }
}

