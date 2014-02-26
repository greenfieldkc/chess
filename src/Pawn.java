
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kylegreenfield
 */
public class Pawn extends Piece {
    public Pawn( Board board, Square origSquare, int id)
    {
        super( board, origSquare, id);
        value = 1;
    }
    
    @Override
    public boolean validMove( Square destSquare)
    { 
       if ( destSquare.getCol() == getLocation().getCol() )
       {
           if  ( getId() == 0 ) 
               return ( checkWhiteOneForward( destSquare ) || 
                       checkWhiteTwoForward( destSquare ) );
           else if ( getId() == 1 )
               return ( checkBlackOneForward( destSquare ) ||
                       checkBlackTwoForward( destSquare ) );
           else
               //System.out.print( "not valid on either white or black pawn forward methods");
               return false; 
       }
       else
       {
           if ( ( getId() == 0 ) )
               return checkWhiteAttack( destSquare );
           else if ( getId() == 1 )
               return checkBlackAttack( destSquare );
           else
               //System.out.print( "not valid on either white/black pawn attack methods");
               return false;
       }       
    }
    
    private boolean checkWhiteOneForward( Square destSquare )
    {
        if ( (destSquare.getRow() - 1) == getLocation().getRow() )
            return squareIsEmpty( destSquare );
        else
            return false;
    }
    
    private boolean checkWhiteTwoForward( Square destSquare )
    {
        if ( (getLocation().getRow() == 2) && (destSquare.getRow() == 4 ) )
        {
            Square middleSquare = board.findSquareByRowCol( 3 , destSquare.getCol() ); //white moving up the board
            return (squareIsEmpty( destSquare ) && squareIsEmpty( middleSquare));
        }
        else
        {
            //System.out.println("inside else of checkWhiteTwoForward");
        
            return false;
        }
    }
    
    private boolean checkWhiteAttack( Square destSquare )
    {
        if ( ( (destSquare.getRow() - 1) == getLocation().getRow() ) &&
                ( Math.abs( destSquare.getCol() - getLocation().getCol() ) == 1))
        {
            if ( destSquare.getOccupant() == null )
                return false;
            else if (destSquare.getOccupant().getId() == 1)
                return true;
            else
                return false;
        }
        else
            return false;
    }
    
    private boolean checkBlackOneForward( Square destSquare )
    {
        if ( (destSquare.getRow() + 1) == getLocation().getRow() )
            return squareIsEmpty( destSquare );
        else
            return false;
    }
    
    private boolean checkBlackTwoForward( Square destSquare )
    {
        if ( getLocation().getRow() == 7 && destSquare.getRow() == 5 ) 
        {
            Square middleSquare = board.findSquareByRowCol( 6, destSquare.getCol() );
            return ( squareIsEmpty( destSquare) && squareIsEmpty( middleSquare ));
        }
        else
            return false;
    }
    
    private boolean checkBlackAttack( Square destSquare )
    {
        if ( ( (destSquare.getRow() + 1) == getLocation().getRow() ) &&
                ( Math.abs( destSquare.getCol() - getLocation().getCol() ) == 1))
        {
            if (destSquare.getOccupant() == null)
                return false;
            else if ( destSquare.getOccupant().getId() == 0 )
                return true;
            else
                return false;
        }
        else
            return false;
    }
    
    @Override
    public ArrayList<Square[]> findPieceLegalMoves()
    {
        ArrayList<Square[]> pieceLegalMoves = new ArrayList();
        ArrayList<Square> squaresToCheck = new ArrayList();
        int r = getLocation().getRow();
        int c = getLocation().getCol();
        if (board.checkValidSquare(r+1, c))
            squaresToCheck.add(board.findSquareByRowCol(r+1, c));
        if (board.checkValidSquare(r-1, c))
            squaresToCheck.add(board.findSquareByRowCol(r-1, c));
        if (board.checkValidSquare(r+2, c))
            squaresToCheck.add(board.findSquareByRowCol(r+2, c));
        if (board.checkValidSquare(r-2, c))
            squaresToCheck.add(board.findSquareByRowCol(r-2, c));
        if (board.checkValidSquare(r+1, c+1))
            squaresToCheck.add(board.findSquareByRowCol(r+1, c+1));
        if (board.checkValidSquare(r+1, c-1))
            squaresToCheck.add(board.findSquareByRowCol(r+1, c-1));
        if (board.checkValidSquare(r-1, c+1))
            squaresToCheck.add(board.findSquareByRowCol(r-1, c+1));
        if (board.checkValidSquare(r-1, c-1))
            squaresToCheck.add(board.findSquareByRowCol(r-1, c-1));
        validatePossibleMoves(squaresToCheck, pieceLegalMoves);
        //System.out.println("squaresToCheck: " + squaresToCheck.size());
        return pieceLegalMoves;
    }
    
}
