#pragma once
#include "Matrix.h"


class MatrixIterator {
    friend class Matrix;

private:
    const Matrix &matrix;

    int currentLine;       // Aktuelle Zeile des Iterators
    int currentColumn;     // Aktuelle Spalte des Iterators

    //constructor
    MatrixIterator(const Matrix& matrix);

public:

    void first();

    void nextLine();

    void nextColumn();

    TElem getCurrent();

    bool valid() const;



};

