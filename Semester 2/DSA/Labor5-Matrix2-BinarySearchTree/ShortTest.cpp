#include <assert.h>
#include "Matrix.h"
#include "MatrixIterator .h"

using namespace std;

void testAll() { 
	Matrix m(4, 4);
	assert(m.nrLines() == 4);
	assert(m.nrColumns() == 4);	
	m.modify(1, 1, 5);
	assert(m.element(1, 1) == 5);
	m.modify(1, 1, 6);
	assert(m.element(1, 2) == NULL_TELEM);
}

//void test_iterator(){
//    Matrix matrix(3, 3);
//    matrix.modify(0, 0, 1);
//    matrix.modify(0, 1, 2);
//    matrix.modify(0, 2, 3);
//    matrix.modify(1, 0, 4);
//    matrix.modify(1, 1, 5);
//    matrix.modify(1, 2, 6);
//    matrix.modify(2, 0, 7);
//    matrix.modify(2, 1, 8);
//    matrix.modify(2, 2, 9);
//
//    MatrixIterator it=matrix.
//
//    it.first();
//
//    int sum = 0;
//    while (it.valid()) {
//        TElem e = it.getCurrent();
//        sum += e;
//        it.nextColumn();
//    }
//
//    assert(sum == 45); //1 + 2 + ... + 9 = 45 sein
//}