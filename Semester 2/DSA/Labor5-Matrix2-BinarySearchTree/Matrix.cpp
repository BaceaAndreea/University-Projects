#include "Matrix.h"
#include <exception>
using namespace std;

//komplexitat: theta(1)
Matrix::Matrix(int nrLines, int nrCols) {
    //baum leer
    this->nrOfLines = nrLines;
    this->nrOfColumns = nrCols;
    this->root = nullptr; //zeiger auf den wurzelknoten
}

//komplexitat: theta(1)
int Matrix::nrLines() const {
    return nrOfLines;
}

//komplexitat: theta(1)
int Matrix::nrColumns() const {
    return nrOfColumns;
}

//Komplexitat: -best_case: theta(1): wenn der element auf (i,j) die wurzel ist
//             -worst_case: theta(log n): Dacă arborele este complet echilibrat și numărul de noduri crește exponențial odată cu înălțimea arborelui
//             -average_case: O(log n), n elemente non-zero
TElem Matrix::element(int i, int j) const {
    //keine gultige position
    if (i < 0 || i >= nrOfLines || j < 0 || j >= nrOfColumns) {
        throw exception();
    }

    BTNode* currentNode = root; //bekommt die Wurzel
    while (currentNode != nullptr) {
        if (currentNode->line == i && currentNode->column == j) {
            return currentNode->info;
        } else if (currentNode->line > i || (currentNode->line == i && currentNode->column > j)) {
            currentNode = currentNode->left;
        } else {
            currentNode = currentNode->right;
        }
    }

    //daca nu a fost gasit
    return NULL_TELEM;
}

//Komplexitat: -best_case: theta(1): wurzel
//             -worst_case: theta(log n): jumate de baum verifica
//             -average_case: O(log n), n elemente non-zero
BTNode* Matrix::findMinimum(BTNode* node) {
    if (node == nullptr) { //leer
        return nullptr;
    }
    while (node->left != nullptr) {
        node = node->left;
    }
    return node;
}


//Komplexitat: -best_case: theta(1): keine kinder
//             -worst_case: theta(log n): cauta knote ul sters
//             -average_case: O(log n)
void Matrix::removeNode(BTNode* parentNode, BTNode* currentNode) {
    if (parentNode == nullptr) {
        //Wurzel
        if (currentNode->left == nullptr && currentNode->right == nullptr) {
            root = nullptr;
        } else if (currentNode->left == nullptr) {
            root = currentNode->right;
        } else if (currentNode->right == nullptr) {
            root = currentNode->left;
        } else {
            //succesor-cel mai mic eleemnt din partea dreapta
            BTNode* successor = findMinimum(currentNode->right);
            successor->left = currentNode->left;
            successor->right = currentNode->right;
            root = successor;
        }
    } else {
        //hat einen Elternknoten
        if (currentNode->left == nullptr && currentNode->right == nullptr) {
            //verifica dacă nodul de șters este vecinul din stanga sau din dreapta al nodului părinte
            if (parentNode->left == currentNode) {
                parentNode->left = nullptr;
            } else {
                parentNode->right = nullptr;
            }
            //un copil in dreapta
        } else if (currentNode->left == nullptr) {
            //verifica dacă nodul de șters este vecinul din stanga sau din dreapta al nodului părinte
            if (parentNode->left == currentNode) {
                parentNode->left = currentNode->right;
            } else {
                parentNode->right = currentNode->right;
            }
            //un copil in stanga
        } else if (currentNode->right == nullptr) {
            if (parentNode->left == currentNode) {
                parentNode->left = currentNode->left;
            } else {
                parentNode->right = currentNode->left;
            }
        } else {
            //succesor - cel mai mic element din subarborele din dreapta nodului
            BTNode* successor = findMinimum(currentNode->right);
            successor->left = currentNode->left;
            successor->right = currentNode->right;
            //daca nodul de șters este vecinul din stanga sau din dreapta al nodului parinte.
            if (parentNode->left == currentNode) {
                parentNode->left = successor;
            } else {
                parentNode->right = successor;
            }
        }
    }

    delete currentNode;
}


//komplexitat: -best_case: theta(1) - old_value =! 0 und wird aktualisiert, und wurzel ist
//             -worst_case: theta(log n) - old_value = 0 und new_value ≠ 0 und muss einfugen
//             -average_case: O(log n)
TElem Matrix::modify(int i, int j, TElem e) {
    // Keine gültige Position
    if (i < 0 || i >= nrOfLines || j < 0 || j >= nrOfColumns) {
        throw std::exception();
    }

    BTNode* currentNode = root;
    BTNode* parentNode = nullptr;
    while (currentNode != nullptr) {
        if (currentNode->line == i && currentNode->column == j) {
            TElem oldValue = currentNode->info;

            if (oldValue != NULL_TELEM && e == NULL_TELEM) {
                // Fall 3: oldValue ≠ NULL_TELEM und e = NULL_TELEM ⇒ lösche den Knoten mit oldValue
                removeNode(parentNode, currentNode);
                return oldValue;
            } else if (oldValue != NULL_TELEM && e != NULL_TELEM){
                // Fall 4: oldValue ≠ NULL_TELEM - aktualisiere den Wert mit e oder füge einen neuen Knoten ein
                currentNode->info = e;
                return oldValue;
            }
        } else if (currentNode->line > i || (currentNode->line == i && currentNode->column > j)) {
            parentNode = currentNode;
            currentNode = currentNode->left;
        } else {
            parentNode = currentNode;
            currentNode = currentNode->right;
        }
    }

    if (e != NULL_TELEM) {
        // Fall 2: oldValue = NULL_TELEM und e ≠ NULL_TELEM ⇒ füge einen neuen Knoten mit e ein
        BTNode* newNode = new BTNode;
        newNode->line = i;
        newNode->column = j;
        newNode->info = e;
        newNode->left = nullptr;
        newNode->right = nullptr;

        if (parentNode == nullptr) {
            root = newNode;
        } else if (parentNode->line > i || (parentNode->line == i && parentNode->column > j)) {
            parentNode->left = newNode;
        } else {
            parentNode->right = newNode;
        }
    }

    return NULL_TELEM; // Fall 1: oldValue = NULL_TELEM und e = NULL_TELEM
}

//komplexitat: theta(1)
Matrix::~Matrix() {
    destroyBST(root);

}

//komplexitat:  Best-case: Theta(n)
//              Average-case:Theta(n)
//              Worst-case: Theta(n)
void Matrix::destroyBST(BTNode *node) {
    if (node != nullptr) {
        destroyBST(node->left);
        destroyBST(node->right);
        delete node;
    }
}
