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
public class King extends Piece {
    public King( Board board, Square origSquare, int id)
    {
        super( board, origSquare, id);
        value = 200;
    }
    
    @Override
    public boolean validMove( Square destSquare)
    {
        if ( squareIsEmpty( destSquare ) || squareHasOpponent( destSquare ) )
        {
            int changeInRow = destSquare.getRow() - getLocation().getRow();
            int changeInCol = destSquare.getCol() - getLocation().getCol();
            if ( (Math.abs(changeInRow) == 1) || (Math.abs(changeInCol) == 1 ))
                return true;
            else
            {
                //System.out.println( "not a valid King movement");
                return false;
            }
        }
        else
            //System.out.print("destSquare appears to be occupied by your own color");
            return false;
    }
    
    @Override public ArrayList<Square[]> findPieceLegalMoves()
    {
        ArrayList<Square[]> pieceLegalMoves = new ArrayList();
        ArrayList<Square> squaresToCheck = new ArrayList();
        Square[] tempSquareArray = new Square[2];
        tempSquareArray[0] = getLocation();
        int r = getLocation().getRow();
        int c = getLocation().getCol();
        int[] possibleDestRow = {r-1, r, r+1};
        int[] possibleDestCol = {c-1, c, c+1};
        for (int rowIndex = 0; rowIndex < possibleDestRow.length; rowIndex++)
        {
            for (int colIndex = 0; colIndex < possibleDestCol.length; colIndex++)
            {
                if ( (board.checkValidSquare(rowIndex, colIndex)) && 
                        !(rowIndex == r && colIndex == c))
                {
                    squaresToCheck.add(board.findSquareByRowCol(rowIndex, colIndex));
                }
            }
        }
        for (int i = 0; i < squaresToCheck.size() ; i++)
        {
            if ( validMove(squaresToCheck.get(i)) )
            {
                tempSquareArray[1] = squaresToCheck.get(i);
                pieceLegalMoves.add(tempSquareArray);
            }
        }
        return pieceLegalMoves;
    }
    
}
