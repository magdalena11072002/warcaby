package org.example.GUI;

import org.example.Data.TypeData;
import org.example.GUI.components.RectangleWithPiece;

public class Board {
    int size;
    int[] pieceslines;
    RectangleWithPiece.colortype piecestand;

    RectangleWithPiece[][] chessBoard;
    Board(TypeData type){
        size = type.getSize();
        pieceslines = type.getLinePieces();
        piecestand = type.getPiecesstand();
    }

    RectangleWithPiece[][] createBoard(){
        /*if(chessBoard == null){ //singleton??? m
            createBoardd();
        }*/
        createBoardd();
        return chessBoard;

    }
    private void createBoardd() {
        chessBoard = new RectangleWithPiece[size][size];
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                RectangleWithPiece square = new RectangleWithPiece(j, i);
                if (((i + j) % 2) == 0) {
                    //square.setFill(Color.GREY);
                    square.setColortype(RectangleWithPiece.colortype.DARK);
                }
                else {
                    square.setColortype(RectangleWithPiece.colortype.LIGHT);
                }

                if(square.getColortype()== piecestand){
                    if(pieceslines[j]== -1 ){
                        square.createPawn("#FFFFFF", j, i);
                    }
                    else if (pieceslines[j]==1 ){
                        square.createPawn("#000000", j, i);
                    }
                }
                chessBoard[i][j] = square;
            }
        }

    }
    // TODO: kolejne parametry, ?przeniesc kolejne elementy GameWindow?
}
