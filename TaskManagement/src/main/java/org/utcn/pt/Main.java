package org.utcn.pt;

import org.utcn.pt.gui.MainFrame;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().createAndShowGUI();
            }
        });
    }
}