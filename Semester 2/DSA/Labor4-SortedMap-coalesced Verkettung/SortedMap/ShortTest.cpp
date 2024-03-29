#include <assert.h>

#include "SortedMap.h"
#include "SMIterator.h"
#include "ShortTest.h"
#include <exception>
#include <iostream>
using namespace std;

bool relatie1(TKey cheie1, TKey cheie2) {
	if (cheie1 <= cheie2) {
		return true;
	}
	else {
		return false;
	}
}

void testAll(){
	SortedMap sm(relatie1);
	assert(sm.size() == 0);
	assert(sm.isEmpty());
    sm.add(1,2);
    assert(sm.size() == 1);
    assert(!sm.isEmpty());
    assert(sm.search(1)!=NULL_TVALUE);
    TValue v =sm.add(1,3);
    assert(v == 2);
    assert(sm.search(1) == 3);
    SMIterator it = sm.iterator();
    it.first();
    while (it.valid()){
    	TElem e = it.getCurrent();
    	assert(e.second != NULL_TVALUE);
    	it.next();
    }
    assert(sm.remove(1) == 3);
    assert(sm.isEmpty());


}

void test_mapnou(){
    SortedMap sm(relatie1);
    sm.add(1,3);
    sm.add(2,6);
    sm.add(3,8);
    sm.add(4, 5);
    sm.add(5,23);

    SortedMap result2= sm.mapNou(2,5);
    assert(result2.size()==4);
    assert(result2.search(2)==6);
    assert(result2.search(5)==23);
    assert(result2.search(1)==NULL_TVALUE);


}

