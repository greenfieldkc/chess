/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kylegreenfield
 */

/*This class provides a basic ComputerPlayer using brute force search of
possible moves for the current turn. The ComputerPlayer recognizes threatened
pieces, but does not consider the opponent's next move, nor any future moves.
Does not recognize being in check.
*/
import java.util.ArrayList;

public class ComputerPlayer extends Player {
    
    public ComputerPlayer(Board board, int id)
    {
        super(board, id);
    }
    
    @Override
    public Square[] getMove()
    {
        double origOpponentScore = board.getPlayer(0).getPlayerScore();
        Square[] selectedMove = new Square[2];
        setPlayerLegalMoves( findPlayerLegalMoves() );
        printLegalMoves();
        double[] tempScore = {0.0, -200.0}; //player[0] score, player[1] score 
        Square[] tempMove = new Square[2];
        for (int i = 0; i < legalMoves.size(); i++)
        {
            tempMove[0] = legalMoves.get(i)[0];
            tempMove[1] = legalMoves.get(i)[1];
                    
            double[] newTempScore = testMoveScore(tempMove);
            if ((newTempScore[1] - newTempScore[0]) > (tempScore[1] - tempScore[0]))
            {
                tempScore = newTempScore;
                updateSelectedMove(tempMove, selectedMove);
            }
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
        System.out.println(move[0].getOccupant().toString() + ":   " +
                move[0].getRow() + "-" + move[0].getCol() +
                " to " + move[1].getRow() + "-" + move[1].getCol());
    }
    
    private void updateSelectedMove(Square[] newMove, Square[] selectedMove)
    {
        selectedMove[0] = newMove[0];
        selectedMove[1] = newMove[1];
    }
    
        
    public void printLegalMoves()
    {
        for (int i = 0; i < legalMoves.size(); i++)
            System.out.println(legalMoves.get(i)[0].getRow()+ " "+
                    legalMoves.get(i)[0].getCol() + "to" +
                    legalMoves.get(i)[1].getRow()+ " "+
                    legalMoves.get(i)[1].getCol());
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