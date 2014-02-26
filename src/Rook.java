
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
public class Rook extends Piece {
    public Rook( Board board, Square origSquare, int id)
    {
        super( board, origSquare, id);
        value = 5;
    }
    
    @Override
    public boolean validMove( Square destSquare)
    {
        int rowDifference = destSquare.getRow() - getLocation().getRow();
        int colDifference = destSquare.getCol() - getLocation().getCol();
        if ((squareIsEmpty( destSquare ) || squareHasOpponent( destSquare )) &&
                (rowDifference != 0 ^ colDifference != 0))
        {
            if ( rowDifference > 0 )
            {
                for (int i = rowDifference - 1; i > 0; i--)
                {
                    if ( !squareIsEmpty(board.findSquareByRowCol(
                          destSquare.getRow() - i, destSquare.getCol()) ))
                    {
                      //System.out.println("Another piece is blocking the path!");
                      return false;
                    }
                }
                return true;    
            }
            else if ( rowDifference < 0 )
            {
                for (int i = rowDifference + 1; i < 0; i++)
                {
                    if ( !squareIsEmpty(board.findSquareByRowCol(
                          destSquare.getRow() - i, destSquare.getCol()) ))
                    {
                      //System.out.println("Another piece is blocking the path!");
                      return false;
                    }
                }
                return true;    
            }
            else if ( colDifference > 0 )
            {
                for (int i = colDifference - 1; i > 0; i--)
                {
                    if ( !squareIsEmpty(board.findSquareByRowCol(
                          destSquare.getRow(), destSquare.getCol() - i) ))
                    {
                      //System.out.println("Another piece is blocking the path!");
                      return false;
                    }
                }
                return true;    
            }
            else if ( colDifference < 0 )
            {
                for (int i = colDifference + 1; i < 0; i++)
                {
                    if ( !squareIsEmpty(board.findSquareByRowCol(
                          destSquare.getRow(), destSquare.getCol() - i) ))
                    {
                      //System.out.println("Another piece is blocking the path!");
                      return false;
                    }
                }
                return true;    
            }
            else
                //System.out.println( "not a valid Rook movement");
                return false;
        }
        else
            //System.out.print("destSquare appears to be occupied by your own color");
            return false;
    }
    
    @Override
    public ArrayList<Square[]> findPieceLegalMoves()
    {
        ArrayList<Square[]> pieceLegalMoves = new ArrayList();
        Square[] tempSquareArray = new Square[2];
        tempSquareArray[0] = getLocation();
        int r = getLocation().getRow();
        int c = getLocation().getCol();
        for (int i = 1; i <= 8; i++)
        {
            Square tempDest = board.findSquareByRowCol(i, c);
            if ( validMove(tempDest) )
            {
                tempSquareArray[1] = tempDest;
                pieceLegalMoves.add( tempSquareArray );
            }
            tempDest = board.findSquareByRowCol(r, i);
            if ( validMove(tempDest) )
            {
                tempSquareArray[1] = tempDest;
                pieceLegalMoves.add( tempSquareArray );
            }   
        }
        return pieceLegalMoves;
    }
}
