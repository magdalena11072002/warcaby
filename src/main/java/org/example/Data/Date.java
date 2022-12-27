package org.example.Data;

import org.example.GUI.HelloApplication;

import java.util.Objects;

public class Date {

    private static int selectedX=-1; //współrzędna x pionka
    private static int selectedY=-1;

    private static int targetX=-1;
    private static int targetY=-1;

    private static boolean locked=false;

    public boolean isLocked() { return locked; }

    public void setLocked(boolean locked) { Date.locked = locked; }

    public int getSelectedX() {return selectedX; }

    public void setSelectedX(int selectedX) { Date.selectedX = selectedX; }

    public int getSelectedY() { return selectedY; }

    public void setSelectedY(int selectedY) { Date.selectedY = selectedY; }

    public int getTargetX() { return targetX; }

    public void setTargetX(int targetX) { Date.targetX = targetX; }

    public int getTargetY() { return targetY; }

    public void setTargetY(int targetY) { Date.targetY = targetY; }
}
