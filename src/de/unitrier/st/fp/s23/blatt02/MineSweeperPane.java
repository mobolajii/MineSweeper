package de.unitrier.st.fp.s23.blatt02;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

final class MineSweeperPane extends GridPane
{
    void setGame(Game game)
    {
        for (int i = 0; i < game.getColumns(); i++)
        {
            for (int j = 0; j < game.getRows(); j++)
            {
                FieldButton button = new FieldButton(game.getField(i, j));
                GridPane.setHgrow(button, Priority.ALWAYS);
                GridPane.setVgrow(button, Priority.ALWAYS);
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                add(button, i, j);
            }
        }
    }



    MineSweeperPane(Game game)
    {
        setGame(game);
    }
}
