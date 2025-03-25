package score4.controller;

import java.util.EventObject;

public class PanelEvent extends EventObject{

    public PanelEvent(Object source, int x, int y) {
        
        super(source);
    }

}
