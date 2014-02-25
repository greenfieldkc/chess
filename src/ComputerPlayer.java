/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kylegreenfield
 */
import java.util.ArrayList;

public class ComputerPlayer extends Player {
    
    public ComputerPlayer(Board board, String name, String color, int id)
    {
        super(board, name, color, id);
    }
    
    @Override
    public Square[] getMove()
    {
        double origOpponentScore = board.getPlayer(0).getPlayerScore();
        Square[] selectedMove = new Square[2];
        setPlayerLegalMoves( findPlayerLegalMoves() );
        printLegalMoves();
        double[] tempScore = {0.0, -200.0}; //player[0] score, player[1] score 
        for (int i = 0; i < legalMoves.size(); i++)
        {
            Square[] move = new Square[2];
            move[0] = legalMoves.get(i)[0];
            move[1] = legalMoves.get(i)[1];
                    
            double[] newTempScore = testMoveScore(move);
            printMove(move);
            if (selectedMove[0] != null)
            {
                printMove(selectedMove);
            }  
            if ((newTempScore[1] - newTempScore[0]) > (tempScore[1] - tempScore[0]))
            {
                tempScore = newTempScore;
                updateSelectedMove(move, selectedMove);
            }
            printMove(selectedMove);
        }
        printMove(selectedMove);
        return selectedMove;
    }
    
    private double[] testMoveScore(Square[] move)
    {
        double[] tempScoreArray = new double[2];
        Piece tempPiece = move[0].getOccupant();
        Piece attackedPiece = move[1].getOccupant();
        board.movePiece(move[0], move[1]);
        tempScoreArray[1] = getPlayerScore();
        tempScoreArray[0] = board.getPlayer(0).getPlayerScore();
        resetBoardToLastMove(move[0], move[1], tempPiece, attackedPiece);
        return tempScoreArray;
        
    }
    
    private void printMove(Square[] move)
    {
        System.out.println(move[0].getOccupant().toString() + 
                move[0].getRow() + move[0].getCol() +
                " to " + move[1].getRow() + move[1].getCol());
    }
    
    private void updateSelectedMove(Square[] newMove, Square[] selectedMove)
    {
        selectedMove[0] = newMove[0];
        selectedMove[1] = newMove[1];
    }
    
    private void testMove(Square origSquare, Square destSquare, double tempScore, Square[] selectedMove)
    {
        Piece tempPiece = origSquare.getOccupant();
        Piece attackedPiece = destSquare.getOccupant();
        board.movePiece(origSquare, destSquare);
        double newScore = getPlayerScore() - board.getPlayer(0).getPlayerScore();
        if ( newScore > tempScore)
        {
            selectedMove[0] = board.findSquareByRowCol(origSquare.getRow(), origSquare.getCol());
            selectedMove[1] = board.findSquareByRowCol(destSquare.getRow(), destSquare.getCol());
            tempScore = newScore;
            System.out.println("selectedMove:  " + selectedMove.toString());
        }
        resetBoardToLastMove(origSquare, destSquare, tempPiece, attackedPiece);
    }
        
    public void printLegalMoves()
    {
        for (int i = 0; i < legalMoves.size(); i++)
            System.out.println(legalMoves.get(i)[0].getRow()+ " "+
                    legalMoves.get(i)[0].getCol() + "to" +
                    legalMoves.get(i)[1].getRow()+ " "+
                    legalMoves.get(i)[1].getCol());
    }
    
    
    private void testOpponentMove()
    {
        
    }
    
    private void resetBoardToLastMove(Square origSquare, Square destSquare,
            Piece tempPiece, Piece attackedPiece)
    {
        origSquare.setOccupant(tempPiece);
        tempPiece.setLocation(origSquare);
        if (attackedPiece != null)
        {
            destSquare.setOccupant(attackedPiece);
            attackedPiece.setLocation(destSquare);
            board.getPlayer(0).addPiece(attackedPiece);
        }
        else
            destSquare.clearOccupant();
    }
    
}