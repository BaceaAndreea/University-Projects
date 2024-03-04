#include "MultiMapIterator.h"
#include "MultiMap.h"
#include <exception>

//Komplexitat: Theta(1)
MultiMapIterator::MultiMapIterator(const MultiMap& c): col(c) {
    this-> current_node=c.head;
}


//Komplexitat: Theta(1)
TElem MultiMapIterator::getCurrent() const{
	if(this->current_node == nullptr)
        throw exception();
    //in eine Variable tuen die eigentlichen Daten der currrent Node
    TElem result= this->current_node->data;
    return result;
}

//Komplexitat: Theta(1)
bool MultiMapIterator::valid() const {
    //man uberpruft ob der iterator valid ist
    return this->current_node != nullptr;
}

//Komplexitat: Theta(1)
void MultiMapIterator::next() {
    if(this->current_node == nullptr) //daca este tail
        throw exception();
    this->current_node=this->current_node->next;
}

//Komplexitat: Theta(1)
void MultiMapIterator::first() {
    //col eine konstante referenz
    this->current_node=this->col.head;
}
