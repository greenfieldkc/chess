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

public class Bishop extends Piece {
    public Bishop( Board board, Square origSquare, String color)
    {
        super( board, origSquare, color);
        value = 3;
    }
    
    @Override
    public boolean validMove( Square destSquare)
    {
        if ( squareIsEmpty( destSquare ) || squareHasOpponent(destSquare))
        {
            int changeInRow = destSquare.getRow() - getLocation().getRow();
            int changeInCol = destSquare.getCol() - getLocation().getCol();
            if ( Math.abs(changeInRow) == Math.abs(changeInCol) )
            {
                for (int i = Math.abs(changeInRow) - 1; i > 0; i--)
                {
                   if ( (changeInRow > 0 && changeInCol > 0))
                   {
                       int newRow = destSquare.getRow() - i;
                       int newCol = destSquare.getCol() - i;
                       Square sq = board.findSquareByRowCol( newRow, newCol);
                       if (!squareIsEmpty( sq ) )
                           return false;
                   }
                   else if ( (changeInRow > 0 && changeInCol < 0))
                   {
                       int newRow = destSquare.getRow() - i;
                       int newCol = destSquare.getCol() + i;
                       Square sq = board.findSquareByRowCol( newRow, newCol);
                       if (!squareIsEmpty( sq ) )
                           return false;
                   }
                   else if ( (changeInRow < 0 && changeInCol > 0))
                   {
                       int newRow = destSquare.getRow() + i;
                       int newCol = destSquare.getCol() - i;
                       Square sq = board.findSquareByRowCol( newRow, newCol);
                       if (!squareIsEmpty( sq ) )
                           return false;
                   }
                   else if ( (changeInRow < 0 && changeInCol < 0))
                   {
                       int newRow = destSquare.getRow() + i;
                       int newCol = destSquare.getCol() + i;
                       Square sq = board.findSquareByRowCol( newRow, newCol);
                       if (!squareIsEmpty( sq ) )
                           return false;
                   }
                }
                return true;
            }
            else
            {
                //System.out.println( "not a valid diagonal movement");
                return false;
            }
        }
        else
        {
            //System.out.print("destSquare appears to be occupied by your own color");
            return false;
        }
    }
    
    @Override
    public ArrayList<Square[]> findPieceLegalMoves()
    {
        ArrayList<Square[]> pieceLegalMoves = new ArrayList() ;
        ArrayList<Square> squaresToCheck = new ArrayList();
        int r = getLocation().getRow();
        int c = getLocation().getCol();
        for ( int i = 1; i < 8; i++)
        {
            if (board.checkValidSquare(r - i, c - i))
                squaresToCheck.add(board.findSquareByRowCol(r - i, c - i));
            if (board.checkValidSquare(r + i, c + i))
                squaresToCheck.add(board.findSquareByRowCol(r + i, c + i));
            if (board.checkValidSquare(r - i, c + i))
                squaresToCheck.add(board.findSquareByRowCol(r - i, c + i));
            if (board.checkValidSquare(r + i, c - i))
                squaresToCheck.add(board.findSquareByRowCol(r + i, c - i));
        }
        validatePossibleMoves(squaresToCheck, pieceLegalMoves);
        return pieceLegalMoves;
    }
    
}