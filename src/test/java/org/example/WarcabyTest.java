package org.example;

import org.example.data.*;
import org.example.gui.Board;
import org.example.gui.components.RectangleWithPiece;
import org.example.logic.Pawn;
import org.example.logic.Queen;
import org.example.logic.pieces.Piece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class WarcabyTest {

    @Test
    void PieceTest() {
        Piece pieceTest = new Queen("#FFFFFF", 0, 0);
        assertEquals(pieceTest.getColor(), "#FFFFFF");
    }

    @Test
    void FieldTest(){
        RectangleWithPiece rectangleTest = new RectangleWithPiece(0,0);
        rectangleTest.setColortype(RectangleWithPiece.Colortype.DARK);
        assertEquals(rectangleTest.getColortype(), RectangleWithPiece.Colortype.DARK);
        rectangleTest.createQueen("#000000",0,0);
        assertEquals(rectangleTest.getPiece().getColor(),"#000000");
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

    @Test
    void DwulionioweTest(){
        TypeData dwuTest = new DwulinioweData();
        assertEquals(dwuTest.getSize(),8);
        assertArrayEquals(dwuTest.getLinePieces(), new int[]{-1,-1, 0, 0, 0, 0, 1, 1,});
        assertEquals(dwuTest.getBoardStart(), RectangleWithPiece.Colortype.LIGHT);
        assertEquals(dwuTest.getPiecesstand(), RectangleWithPiece.Colortype.DARK);
        assertFalse(dwuTest.isAbleCaptureBackwards());
        assertTrue(dwuTest.isStandingAnywhereQueenAfterCapture());
    }

    @Test
    void BoardTest(){
        Board boardTest = new Board(new HiszpanskieData());

        RectangleWithPiece[][] chessBoardTest = boardTest.createBoard();
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

    }
}
