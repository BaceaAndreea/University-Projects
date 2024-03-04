#include "SMIterator.h"
#include "SortedMap.h"
#include <exception>

using namespace std;

//komplexitat: theta(m log m)
void quickSort(TElem arr[], int left, int right,Relation r) {
    if (left < right) {
        // Wähle ein Pivot-Element
        TElem pivot = arr[right];
        int i = left - 1;

        // Partitionierung
        for (int j = left; j < right; j++) {
            if (r(arr[j].first,pivot.first)) {
                i++;
                // Tausche Elemente
                TElem temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Tausche das Pivot-Element an die richtige Position
        TElem temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;

        int pivotIndex = i + 1;

        // Rekursiver Aufruf für die linke und rechte Teilmenge des Pivot-Elements
        quickSort(arr, left, pivotIndex - 1,r);
        quickSort(arr, pivotIndex + 1, right,r);
    }
}


//Komplexitat: -best_case: theta(1): daca e gol
//             -worst_case: theta(m): putem sa avem mapul gol si numa pe ultima pozitie
//             -average_case: theta(m log m): da die Quick-Sort-Schleife im Durchschnitt  von m logm Vergleiche und Swaps durchführt
SMIterator::SMIterator(const SortedMap& m) : map(m){
    //sortieren der Elemente in einen temp Array
    sorted_array = new TElem[map.size()];
    int ct=0;

    if(map.isEmpty()) {
        current = -1;
    }
    else{
        current=0;
        //le mutam
        for (int i = 0; i < map.m; i++)
            if (map.T[i] != TElem(-111111, -111111))
                sorted_array[ct++] = map.T[i];
    }

    quickSort(sorted_array, 0, map.size() - 1,this->map.r);
}

//komeplexitat: theta(1)
void SMIterator::first(){
        current = 0;
}

//komeplexitat: best theta(1)
// worst-theta(m) nu stim cat de mare e pasul pana la elementul urmator
void SMIterator::next(){
    if(!this->valid() ){

        throw exception();
    }
    this->current++;
}

//komeplexitat: theta(1)
bool SMIterator::valid() const{
    if(map.isEmpty())
        return false;
    return current >-1 && current < map.size();
}

//komeplexitat: theta(1)
TElem SMIterator::getCurrent() const{
    if(!valid()){
        throw exception();
    }
    return sorted_array[this->current];
}


