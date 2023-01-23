package org.example.data;

/**
 Klasa Date prztwarza dane z każdego trybu gry oraz udostępnia metody do korzystania z nich.
 */
public class Date {
    /**
     * * @param selectedX - współrzędna X pionka
     *  * @param selectedY - współrzędna Y pionka
     *  * @param targetX - współrzędna X pola
     *  * @param targetY - współrzędna Y pola
     *  * @param locked - informacja o zablokowaniu danego gracza, jeśli może wykonać więcej niż jedno bicie
     */
    private static int selectedX = -1;
    private static int selectedY = -1;

    private static int targetX = -1;
    private static int targetY = -1;

    //private static boolean locked = false;

    private int amountOfMadeMoves;

    /**
     * Funkcja kończąca turę danego gracza
     */
    public void endMove() {
        amountOfMadeMoves = 0;
    }

    /**
     * Funkcja podliczajaca wykonanie następnego ruchu tym samym pionkiem
     */
    public void nextMove() {
        amountOfMadeMoves += 1;
    }

    /**
     * Fnkcja która zwraca ilość możliwych ruchów
     * @return ilość możliwych ruchów
     */
    public int getAmountOfMoves(){
        return amountOfMadeMoves;
    }

    /**
     * Funckja zwraca współrzędną X wybranego pionka
     * @return współrzędn X pionka
     */
    public int getSelectedX() { return selectedX; }

    /**
     * Funkcja ustawia nową współrzędną X po ruchu pionkiem
     * @param selectedX nową współrzędną X
     */
    public void setSelectedX(int selectedX) { Date.selectedX = selectedX; }

    /**
     * Funkcja zwraca współrzędną Y wybranego pionka
     * @return współrzędną Y
     */
    public int getSelectedY() { return selectedY; }

    /**
     * Funkcja ustawia nową współrzędną Y po ruchu pionkiem
     * @param selectedY nową współrzędną Y
     */
    public void setSelectedY(int selectedY) { Date.selectedY = selectedY; }

    /**
     * Funkcja zwraca współrzędną X pola na które chcemy się ruszyć
     * @return współrzędną X pola
     */
    public int getTargetX() { return targetX; }

    /**
     * Funkcja która zmienia współrzędną X pola dla danego pionka na ta na które się ruszył
     * @param targetX współrzędną X nowego pola
     */
    public void setTargetX(int targetX) { Date.targetX = targetX; }

    /**
     * Funkcja zwraca współrzędną Y pola na które chcemy się ruszyć
     * @return współrzędną Y
     */
    public int getTargetY() { return targetY; }

    /**
     * Funckja która zmienia wspołrzędną Y pola dla danego pionka na które się poruszył
     * @param targetY wspołrzędną Y nowego pola
     */
    public void setTargetY(int targetY) { Date.targetY = targetY; }
}
