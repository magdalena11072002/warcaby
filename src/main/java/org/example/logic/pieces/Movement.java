package org.example.logic.pieces;

/**
 * interfejs do obsługi pionków i królowej
 */
public interface Movement {

    /**
     * Funkcja do wykonywania ruchów
     * @return
     */
    boolean makeMove();

    /**
     * Funkcja zwracająca ilość możliwych bić wokół pionka
     * @return
     */
    int possibleBitesHere();

    /**
     * Funkcja zwracająca najdłższy możliwy ruch
     * @return
     */
    int longestway();

}
