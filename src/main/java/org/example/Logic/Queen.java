package org.example.Logic;

import org.example.GUI.GameWindow;
import org.example.GUI.components.RectangleWithPiece;
import org.example.Logic.pieces.Piece;

import static java.lang.Math.abs;


public class Queen extends Piece {

    public Queen(String color, int myY, int myX) {

        super(color, myY, myX);
        //zmiana wygladu
        setStyle(
                getStyle()
                        +"-fx-background-color: red;"
        );
    }

    @Override
    public boolean makeMove() {
        return killPiece();
    }

    private RectangleWithPiece Whatpiecestokill(){
        RectangleWithPiece tokill = null;
        int amount = 0;

        int forX;
        int forY;
        //po lewej = -1; po prawej = 1; ten sam = 0
        forX = Integer.compare(coordinates.getTargetX(), myX);
        //nizej,wyzej
        forY = Integer.compare(coordinates.getTargetY(), myY);


        //jesli sa po przekatnej
        if((forX *(coordinates.getTargetX()-myX)) == (forY *(coordinates.getTargetY()-myY))) {
            //nie trzeba danego punktu bo warunek dla square
            //dopoki sprawdzamy punkty inne niz nasz (kolejne do niego)
            int i = 1;
            while ((myX != coordinates.getTargetX() - i*forX) && (myY != coordinates.getTargetY() - i*forY)) { //teoretycznie wystarczylby jeden warunek
                RectangleWithPiece RwPToKill = GameWindow.checkSquare((coordinates.getTargetX()) - i*forX, (coordinates.getTargetY() - i*forY));
                //nastepny
                if (RwPToKill.getPiece() != null) {
                    //ten sam kolor
                    if (RwPToKill.getPiece().getColor().equals(color)) {
                        return null;
                    }
                    amount++;
                    if(amount>1){
                        break;
                    }
                    tokill = RwPToKill;//?czy to na pewno to bedzie
                }
                i++;
            }
            //jesli znaleziono 1, to on do usuniecia
            if(amount == 1){
                return tokill;
            }
            //jesli nic nie znaleziono to tylko ruch
            if(amount == 0){
                return GameWindow.checkSquare(myX,myY);
            }
        }
        //nic nie ma do zabicia
        return null;
    }

    private boolean killPiece() {
        //co zbic
        RectangleWithPiece tokill = Whatpiecestokill();
        if (tokill == null) {
            return false;
        }
        if (tokill == GameWindow.checkSquare(myX, myY)) { //?
            return longMove();
        }
        tokill.setPiece(null);
        myY = coordinates.getTargetY();
        myX = coordinates.getTargetX();
        return true;
    }

    private boolean longMove(){
        System.out.println("dlugi");
        if(abs(coordinates.getTargetX()-myX) == abs(coordinates.getTargetY()-myY) ){
            this.myY = coordinates.getTargetY();
            this.myX = coordinates.getTargetX();
            return true;
        }
        return  false;
    }

}