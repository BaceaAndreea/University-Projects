#include "PriorityQueue.h"
#include "ShortTest.h"
#include <assert.h>
#include <iostream>

bool rel(TPriority p1, TPriority p2) {
	if (p1 <= p2) {
		return true;
	}
	else {
		return false;
	}
}


void testAll() { //call each function and see if it exists
	PriorityQueue pq(rel);
	
	assert(pq.isEmpty() == true);
	
	//adaugam 5, 3, 10, 2, 12 -> rezultat: 2, 3, 5, 10, 12
	pq.push(5, 5);
	assert(pq.top().first == 5);
	assert(pq.top().second == 5);
	pq.push(3, 3);
	assert(pq.top().first == 3);
	assert(pq.top().second == 3);
	pq.push(10, 10);
	assert(pq.top().first == 3);
	assert(pq.top().second == 3);
	pq.push(2, 2);
	assert(pq.top().first == 2);
	assert(pq.top().second == 2);
	pq.push(12, 12);
	assert(pq.top().first == 2);
	assert(pq.top().second == 2);
	assert(pq.isEmpty() == false);
	assert(pq.pop().second == 2);
	assert(pq.top().second == 3);
	assert(pq.pop().second == 3);
	assert(pq.top().second == 5);
	assert(pq.pop().second == 5);
	assert(pq.top().second == 10);
	assert(pq.pop().second == 10);
	assert(pq.top().second == 12);
	assert(pq.pop().second == 12);
	assert(pq.isEmpty() == true);

//
//    PriorityQueue q1(rel);
//    PriorityQueue q2(rel);
//
//    q1.push(1,3);
//    q1.push(3,2);
//    q1.push(2,1);
//
//    q2.push(4,6);
//    q2.push(6,5);
//    q2.push(5,4);
//
//    PriorityQueue final= q1.interclasare(q2);
//    while(!final.isEmpty()){
//        Element e= final.pop();
//        assert()
//    }

}

