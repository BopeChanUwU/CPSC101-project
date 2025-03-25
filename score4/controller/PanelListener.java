package score4.controller;

import java.util.EventListener;

public interface PanelListener extends EventListener{

    public void panelEventOccured(PanelEvent event);
}
