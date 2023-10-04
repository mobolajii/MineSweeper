package de.unitrier.st.fp.s23.blatt02;

final class Game
{
    private Field[][] board;
    private final int columns, rows;
    private int numberOfBombs;
    private int unhiddenFields;
    private boolean done = false;

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    private void initGame()
    {
        board = new Field[columns][rows];
        numberOfBombs = 0;
        unhiddenFields = 0;
        // Initialization works in two phases:
        // First: generate board and all fields
        for (int i = 0; i < columns; i++)
            for (int j = 0; j < rows; j++)
            {
                board[i][j] = new Field(this, i, j);
            }
        // Second: compute for each field the number of neighbors with bombs
        for (int i = 0; i < columns; i++)
            for (int j = 0; j < rows; j++)
            {
                board[i][j].computeNeighborsWithBombs();
                if (board[i][j].isBomb())
                {
                    numberOfBombs++;
                }
            }
    }



    Game(int columns, int rows)
    {
        this.columns = columns;
        this.rows = rows;
        initGame();
    }

    boolean allUnhidden()
    {
        return unhiddenFields == columns * rows - numberOfBombs;
    }

    void increaseUnhiddenFields()
    {
        this.unhiddenFields++;
    }

    Field getField(int x, int y)
    {
        return this.board[x][y];
    }

    int getColumns()
    {
        return this.columns;
    }

    int getRows()
    {
        return this.rows;
    }

}
