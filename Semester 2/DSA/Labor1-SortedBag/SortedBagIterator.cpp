#include "SortedBagIterator.h"
#include "SortedBag.h"
#include <exception>

using namespace std;
//Komplexität: Θ(1)
SortedBagIterator::SortedBagIterator(const SortedBag& b) : bag(b) {
	//TODO - Implementation
    this->index=0;
}
//Komplexität: Θ(1)
TComp SortedBagIterator::getCurrent() {
	//TODO - Implementation
	if(!valid()){
        throw exception();
    }else{
        return this->bag.v[this->index];
        //elementul de pe indexul acela din bag
    }
}
//Komplexität: Θ(1) allen Fallen
//gibt true zurück, wenn der aktuelle Index des Iterators kleiner als die Gesamtanzahl der Elemente im Bag ist, auf den der Iterator zeigt.
bool SortedBagIterator::valid() const {
	//TODO - Implementation
    //daca este in n=LANGE a lui n si nu este mai mic decat 0 este valid
    return  (this->index < this->bag.n);

}
//Komplexität: Θ(1) allen Fallen
void SortedBagIterator::next() {
	//TODO - Implementation
    if (!valid()){
        throw exception();
    }else{
        this->index++;
    }
}
//Komplexität: Θ(1) allen Fallen
void SortedBagIterator::first() {
	//TODO - Implementation
    this->index=0;
}

