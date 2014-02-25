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

public class Knight extends Piece {
    public Knight( Board board, Square origSquare, String color)
    {
        super( board, origSquare, color);
        value = 3;
    }
    
    @Override
    public boolean validMove( Square destSquare)
    {
        if ( squareIsEmpty( destSquare ) || squareHasOpponent( destSquare ) )
        {
            int changeInRow = destSquare.getRow() - getLocation().getRow();
            int changeInCol = destSquare.getCol() - getLocation().getCol();
            if ( (Math.abs(changeInRow) == 2 && Math.abs(changeInCol) == 1) )
                return true;
            else if ( Math.abs(changeInCol) == 2 && Math.abs(changeInRow) == 1 )
                return true;
            else
            {
                //System.out.println( "not a valid Knight movement");
                return false;
            }
        }
        else
            //System.out.print("destSquare appears to be occupied by your own color");
            return false;
    }
    
    @Override
    public ArrayList<Square[]> findPieceLegalMoves()
    {
        ArrayList<Square[]> pieceLegalMoves = new ArrayList();
        ArrayList<Square> squaresToCheck = new ArrayList();
        int r = getLocation().getRow();
        int c = getLocation().getCol();
        if (board.checkValidSquare(r+1, c+2))
            squaresToCheck.add(board.findSquareByRowCol(r+1, c+2));
        if (board.checkValidSquare(r+1, c-2))
            squaresToCheck.add(board.findSquareByRowCol(r+1, c-2));
        if (board.checkValidSquare(r-1, c+2))
            squaresToCheck.add(board.findSquareByRowCol(r-1, c+2));
        if (board.checkValidSquare(r-1, c-2))
            squaresToCheck.add(board.findSquareByRowCol(r-1, c-2));
        if (board.checkValidSquare(r+2, c+1))
            squaresToCheck.add(board.findSquareByRowCol(r+2, c+1));
        if (board.checkValidSquare(r+2, c-1))
            squaresToCheck.add(board.findSquareByRowCol(r+2, c-1));
        if (board.checkValidSquare(r-2, c+1))
            squaresToCheck.add(board.findSquareByRowCol(r-2, c+1));
        if (board.checkValidSquare(r-2, c-1))
            squaresToCheck.add(board.findSquareByRowCol(r-2, c-1));
        /*System.out.println("Knight squaresToCheck:");
        for (int i = 0; i < squaresToCheck.size(); i++)
        {
            System.out.println(squaresToCheck.get(i).getRow() + "" + squaresToCheck.get(i).getCol());
        }*/
        validatePossibleMoves(squaresToCheck, pieceLegalMoves);
        /*System.out.println("Knight pieceLegalMoves:");
        for (int i = 0; i < pieceLegalMoves.size(); i++)
            printMove(pieceLegalMoves.get(i));*/
        return pieceLegalMoves;
    }

    private void printMove(Square[] move)
    {
        System.out.println(move[0].getOccupant().toString() + 
                move[0].getRow() + move[0].getCol() +
                "to" + move[1].getRow() + move[1].getCol());
    }
}
