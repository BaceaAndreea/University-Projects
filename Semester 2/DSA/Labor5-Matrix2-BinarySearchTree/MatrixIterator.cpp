#include "MatrixIterator .h"
#include <exception>
using namespace std;


MatrixIterator::MatrixIterator(const Matrix &matrix)
        : matrix(matrix), currentLine(0), currentColumn(0) {}

//komeplxitat: theta(1)
void MatrixIterator::first() {
    currentLine = 0;
    currentColumn = 0;
}

//pre:
//post:Wenn currentLine kleiner als die Anzahl der Zeilen der Matrix minus 1 ist wird currentLine um 1 erhoht und currentColumn auf 0 gesetzt.
//komeplxitat: theta(1)
void MatrixIterator::nextLine() {
    if (currentLine < matrix.nrLines() - 1) {
        currentLine++;
        currentColumn = 0;
    }
}

//post:Wenn currentColumn kleiner als die Anzahl der Spalten der Matrix minus 1 ist, wird currentColumn um 1 erhoht.
//anders wenn currentColumn gleich der Anzahl der Spalten der Matrix minus 1 und currentLine kleiner als die Anzahl der Zeilen der Matrix minus 1 ist, wird currentLine um 1 erhöht und currentColumn auf 0 gesetzt.
//komeplxitat:theta(1)
void MatrixIterator::nextColumn() {
    if (currentColumn < matrix.nrColumns() - 1) {
        currentColumn++;
    }
    else if (currentLine < matrix.nrLines() - 1) {
        currentLine++;
        currentColumn = 0;
    }
}

//komeplxitat: theta(1)
//elem curent de pe linia i si j
TElem MatrixIterator::getCurrent() {
    return matrix.element(currentLine, currentColumn);
}

//komeplxitat: theta(1)
//ob der iterator das ende des matrix erreicht hat
bool MatrixIterator::valid() const {
    return currentLine < matrix.nrLines() && currentColumn < matrix.nrColumns();
}
