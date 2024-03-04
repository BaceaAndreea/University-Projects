#pragma once
#include <vector>
#include <utility>

using namespace std;

//DO NOT CHANGE THIS PART
typedef int TElem;
typedef int TPriority;
typedef std::pair<TElem, TPriority> Element;
typedef bool (*Relation)(TPriority p1, TPriority p2);
#define NULL_TELEM pair<TElem, TPriority> (-11111,-11111);

struct Node{
    Element e; //der eigentliche Zahl
    int next; //de la asta stim unde mergem mai departe
};

class PriorityQueue {
private:
    Node* elems; //der array
    int cap;
    int size;
    int head; //element mit dem hochsten prioritat, este sortat
    int firstEmpty; //ersten freien element in array
    Relation r;

public:
	PriorityQueue(Relation r);

    void resize();

    void insertFirst(int new_elem);

    void insertOnPosition(int new_elem);

	//pushes an element with priority to the queue
	void push(TElem e, TPriority p);

	//returns the element with the highest priority with respect to the order relation
	//throws exception if the queue is empty
	Element top()  const;

    //interclasare a doua cozi care au aceasi prioritate
    PriorityQueue interclasare(PriorityQueue& other);

	//removes and returns the element with the highest priority
	//throws exception if the queue is empty
	Element pop();

	//checks if the queue is empty
	bool isEmpty() const;

	//destructor
	~PriorityQueue();

};