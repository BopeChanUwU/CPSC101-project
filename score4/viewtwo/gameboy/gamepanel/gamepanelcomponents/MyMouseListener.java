package score4.viewtwo.gameboy.gamepanel.gamepanelcomponents;

public class MyMouseListener implements java.awt.event.MouseListener {

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        // does nothing
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        // does nothing
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        // does nothing
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        // this should change the image to the hover image
        // e.getComponent().setIcon(new ImageIcon("path/to/hover/image"));
        // e.getComponent().repaint();
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        // this should change the image back to the original
        // e.getComponent().setIcon(new ImageIcon("path/to/original/image"));
        // e.getComponent().repaint();
    }

}
