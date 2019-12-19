//Sudoku-Solver using Backtracking Algorithm
//Marvin Fuchs 19.12.2019
//C++-Version

#include <iostream>
using namespace std;

typedef struct {
    int row, column;
} Field;


class Solver {
    public:
        int* board;
        
        Solver(int board_[9][9]) {
            board = &board_[0][0];
            cout << "Problem:" << endl;
            printBoard();
            solve();
            cout << "\nSolution:" << endl;
            printBoard();
        }

        int getField(int row, int column) {
            return *(board+9*row+column);
        }

        void setField(int row, int column, int value) {
            *(board+9*row+column) = value;
        }

        Field findNextField() {
            Field nextField;
            nextField.row=-1;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (getField(i,j)==0) {
                        nextField.row=i;
                        nextField.column=j;
                        break;
                    }
                }
            }
            return nextField;
        }

        bool validate(int row, int column, int value) {
            //chechk row
            for (int i = 0; i < 9; i++) {
                if (getField(row, i) == value && i!=column) {
                    return false;
                }
            }
            //check volumn
            for (int i = 0; i < 9; i++) {
                if (getField(i, column) == value && i!=row) {
                    return false;
                }
            }
            //check subgrid
            int rowSpace = row / 3;
            int columnSpace = column / 3;
            for (int i = 3*rowSpace; i < 3*rowSpace+3; i++) {
                for (int j = 3*columnSpace; j < 3*columnSpace+3; j++) {
                    if (getField(i,j)==value && i!=row && j!=column) {
                        return false;
                    }
            }
            }
            return true;
        }

        bool solve() {
            Field nextField = findNextField();
            if (nextField.row==-1) {
                return true;
            }
            else {
                for (int i = 1; i <= 9; i++) {
                    if (validate(nextField.row, nextField.column, i)) {
                        setField(nextField.row, nextField.column, i);
                        if (solve()) {
                            return true;
                        }
                        setField(nextField.row, nextField.column, 0);
                    }
                }
                return false;
            }
        }


        void printBoard() {
            for (int i = 0; i < 9; i++) {
                if (i % 3 == 0 && i > 0) {
                    cout << "---------------------" << endl;
                }
                for (int j = 0; j < 9; j++) {
                    if (j % 3 == 0 && j > 0) {
                        cout << "| ";
                    }
                    if (j == 8) {
                        cout << *(board+9*i+j) << endl;
                    }
                    else {
                        cout << *(board+9*i+j) << " ";
                    }
                }
            }
        }
};

int main() {
    int sudoku[9][9] = {{0,7,1,0,4,0,0,0,8},
                        {4,0,0,3,0,0,0,0,0},
                        {9,0,0,0,0,0,0,7,4},
                        {5,0,6,0,7,1,8,0,0},
                        {0,2,0,0,0,0,0,1,0},
                        {0,0,3,4,9,0,6,0,2},
                        {2,9,0,0,0,0,0,0,5},
                        {0,0,0,0,0,2,0,0,6},
                        {1,0,0,0,8,0,3,2,0}
                       };
    Solver machine(sudoku);
    return 0;
}




