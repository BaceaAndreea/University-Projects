
#include <iostream>
#include "Matrix.h"
#include "ExtendedTest.h"
#include "ShortTest.h"

using namespace std;


int main() {


	testAll();
    cout<<"short test end"<<endl;
    //test_iterator();
    //cout<<"test iterator end"<<endl;
	testAllExtended();
	cout << "Test End" << endl;
	system("pause");
}