#pragma once

#include <vector>
#include "SortedMap.h"

//DO NOT CHANGE THIS PART
class SMIterator{
	friend class SortedMap;
private:
	const SortedMap& map;
	SMIterator(const SortedMap& mapionar);

    int current; //die aktuelle position im Array
    TElem* sorted_array; // sortiertes Array der Elemente


public:
	void first();
	void next();
	bool valid() const;
    TElem getCurrent() const;


};

