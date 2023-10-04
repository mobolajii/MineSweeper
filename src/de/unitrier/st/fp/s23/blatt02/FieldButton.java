package de.unitrier.st.fp.s23.blatt02;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

class FieldButton extends Button
{
    private Field field;
    private boolean marked = false;

    public void toggle(){
        marked=!marked;
        if (marked){
            setText("M");
        }else {
            setText("U");
        }
    }

    private static EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>()
    {
        public void handle(MouseEvent e)
        {
            FieldButton b = (FieldButton) e.getSource();
            if (MouseButton.PRIMARY == e.getButton()){
                b.getField().checkField();
            }
            if (MouseButton.SECONDARY == e.getButton()){
                b.toggle();
            }
        }
    };

    private Field getField()
    {
        return this.field;
    }

    FieldButton(Field field)
    {
        super(" ");
        this.field = field;
        this.field.setButton(this);
        this.setOnMouseClicked(mouseHandler);
    }
}
