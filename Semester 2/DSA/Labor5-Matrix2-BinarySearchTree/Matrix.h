#pragma once
//DO NOT CHANGE THIS PART
typedef int TElem;
#define NULL_TELEM 0
struct BTNode {
    //SLL
    // Value of the node
    TElem info;
    // Pointer to left child node (are doar 2 copii)
    BTNode* left;
    // Pointer to right child node
    BTNode* right;
    int line, column;
};

class Matrix {

private:
    // The number of Lines, respectively Columns, of the Sparse Matrix
    int nrOfLines, nrOfColumns;

    BTNode* root; // Pointer to the root node of the binary tree

    static BTNode* findMinimum(BTNode* node) ;

    void destroyBST(BTNode* node);
public:
	//constructor
	Matrix(int nrLines, int nrCols);

	//returns the number of lines
	int nrLines() const;

	//returns the number of columns
	int nrColumns() const;

	//returns the element from line i and column j (indexing starts from 0)
	//throws exception if (i,j) is not a valid position in the Matrix
	TElem element(int i, int j) const;

    void removeNode(BTNode* parentNode, BTNode* currentNode);

	//modifies the value from line i and column j
	//returns the previous value from the position
	//throws exception if (i,j) is not a valid position in the Matrix
	TElem modify(int i, int j, TElem e);
	
	// destructor
	~Matrix();

};
