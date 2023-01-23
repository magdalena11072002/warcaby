package org.example;

import org.example.data.*;
import org.example.gui.Board;
import org.example.gui.GameWindow;
import org.example.gui.components.RectangleWithPiece;
import org.example.logic.Pawn;
import org.example.logic.Queen;
import org.example.logic.pieces.Piece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class WarcabyTest {

    @Test
    void PieceTest(){
        Piece pieceTest = new Queen("#FFFFFF",0,0);
        assertEquals(pieceTest.getColor(),"#FFFFFF");

        //usun
        pieceTest.setColor("#000000");
        assertEquals(pieceTest.getColor(),"#000000");
    }

    @Test
    void PolskieTest(){
        TypeData polskieTest = new PolskieData();
        assertNotEquals(polskieTest.getSize(),8);
        assertNotEquals(polskieTest.getLinePieces(), new int[]{-1, -1, -1, 0, 0, 1, 1, 1});
        assertArrayEquals(polskieTest.getLinePieces(), new int[]{-1, -1, -1,-1, 0, 0, 1, 1, 1, 1});
        assertEquals(polskieTest.getBoardStart(), RectangleWithPiece.Colortype.LIGHT);
        assertEquals(polskieTest.getPiecesstand(), RectangleWithPiece.Colortype.DARK);
        assertTrue(polskieTest.isAbleCaptureBackwards());
        assertFalse(polskieTest.isStandingAnywhereQueenAfterCapture());
    }
    RectangleWithPiece[][] chessBoardTest;

    @Test
    void BoardTest(){
        Board boardTest = new Board(new HiszpanskieData());

        //RectangleWithPiece[][] chessBoardTest = boardTest.createBoard();
        chessBoardTest = boardTest.createBoard();

        assertEquals(chessBoardTest.length, 8);
        assertEquals(chessBoardTest[0][0].getColortype(), RectangleWithPiece.Colortype.DARK);
        assertNotNull(chessBoardTest[1][0].getPiece());
    }

    @Test
    void MovingPiecesTest(){
        Date cordsTest = new Date();
        Board boardTest = new Board(new DwulinioweData());
        RectangleWithPiece[][] chessBoardTest = boardTest.createBoard();
        Pawn aa = (Pawn) chessBoardTest[0][1].getPiece();
        cordsTest.setSelectedX(0);
        cordsTest.setSelectedY(1);
        cordsTest.setTargetX(4);
        cordsTest.setTargetY(2);

        assertFalse(aa.makeMove(cordsTest));

        cordsTest.setTargetX(1);
        cordsTest.setTargetY(2);
        assertTrue(aa.makeMove(cordsTest));

        /*
        chessBoardTest[1][2].setPiece(aa);
        chessBoardTest[0][1].setPiece(null);

        cordsTest.setSelectedX(1);
        cordsTest.setSelectedY(2);

        cordsTest.setTargetX(3);
        cordsTest.setTargetY(4);

        chessBoardTest[2][3].createPawn("#FFFFFF",3,2);
        assertTrue(aa.makeMove(cordsTest));
        */

    }
}
