#Sudoku-Solver using Backtracking Algorithm
#Marvin Fuchs 16.12.2019

sudokuBoard = [[8,0,5,1,0,3,0,0,0],
         [6,2,1,8,0,0,0,0,3],
         [0,7,0,6,4,0,1,0,0],
         [0,8,0,4,0,0,0,0,0],
         [7,9,3,0,0,0,4,2,1],
         [0,0,0,0,0,7,0,3,0],
         [0,0,8,0,9,4,0,6,0],
         [2,0,0,0,0,6,8,5,9],
         [0,0,0,3,0,5,2,0,4]
        ]

def solve(board):
	posNextElement = findNextElement(board)
	if posNextElement is None:
		return True
	else:
		row, col = posNextElement
		for i in range(1,len(board)+1):
			if validate(board, i, (row, col)):
				board[row][col] = i
				if solve(board):
					return True
				board[row][col] = 0
		return False

def findNextElement(board):
	for i in range(len(board)):
		for j in range(len(board[0])):
        		if board[i][j]==0:
        			return (i,j)
	return None        			

def validate(board, value, pos):
	row, column = pos
	#check row
	for i in range(len(board[0])):
		if board[row][i] == value and column!=i:
			return False
	#check column
	for i in range(len(board)):
		if board[i][column] == value and row!=i:
			return False
	#check subboard
	rowSpace = row // 3
	columnSpace = column // 3
	for i in range(3*rowSpace, 3*rowSpace+3):
		for j in range(3*columnSpace, 3*columnSpace+3):
			if board[i][j] == value and i != row and j != column:
				return False
	#else its true
	return True

def printBoard(board):
    for i in range(len(board)):
        if i%3==0 and i>0:
            print('---------------------')
        for j in range(len(board[0])):
            if j%3==0 and j>0:
                print('|', end=' ')
            if j == len(board)-1:
            	print(str(board[i][j]))
            else:
            	print(str(board[i][j]), end=' ')
    print()

print("Sudoku:",end='\n\n')
printBoard(sudokuBoard)
print("Solution:",end='\n\n')
solve(sudokuBoard)
printBoard(sudokuBoard)


