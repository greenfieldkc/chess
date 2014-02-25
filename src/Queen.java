
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
public class Queen extends Piece {
    public Queen( Board board, Square origSquare, String color)
    {
        super( board, origSquare, color);
        value = 9;
    }
    
    @Override
    public boolean validMove( Square destSquare)
    {
        if ( squareIsEmpty( destSquare ) || squareHasOpponent( destSquare ) )
        {
            return (checkDiagonal( destSquare )|| checkVerticalHorizontal( destSquare ));
        }
        else
            //System.out.print("destSquare appears to be occupied by your own color");
            return false;
    }
    
    private boolean checkDiagonal( Square destSquare )
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
    
    private boolean checkVerticalHorizontal( Square destSquare)
    {
        int rowDifference = destSquare.getRow() - getLocation().getRow();
        int colDifference = destSquare.getCol() - getLocation().getCol();
        if ( (rowDifference != 0 ^ colDifference != 0))
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
            //System.out.print("error in verticalhorizontal");
            return false;
    }
    
    @Override
    public ArrayList<Square[]> findPieceLegalMoves()
    {
        ArrayList<Square[]> pieceLegalMoves = new ArrayList();
        ArrayList<Square> movesToCheck = new ArrayList();
        int r = getLocation().getRow();
        int c = getLocation().getCol();
        for (int i = 1; i <= 8; i++)
        {
            movesToCheck.add(board.findSquareByRowCol(i, c));
            movesToCheck.add(board.findSquareByRowCol(r, i));
            if ( board.checkValidSquare( r - i, c - i) )
                movesToCheck.add(board.findSquareByRowCol(r-i, c-i));
            if ( board.checkValidSquare( r + i, c + i) )
                movesToCheck.add(board.findSquareByRowCol(r+i, c+i));
            if ( board.checkValidSquare( r - i, c + i) )
                movesToCheck.add(board.findSquareByRowCol(r-i, c+i));
            if ( board.checkValidSquare( r + i, c - i) )
                movesToCheck.add(board.findSquareByRowCol(r+i, c-i));
        }
        validatePossibleMoves(movesToCheck, pieceLegalMoves);
        return pieceLegalMoves;
    }
    
}