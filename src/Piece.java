/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kylegreenfield
 */

import java.lang.Math;
import java.util.ArrayList;

public abstract class Piece {
    private Square location;
    private String color;
    private int ownerId;
    protected Board board;
    protected int value;
    protected ArrayList<Square[]> legalMoves;
    
    public Piece( Board board, Square origSquare, int id )
    {
        ownerId = id;
        location = origSquare;
        setColor();
        this.board = board;
    }
    
    public Square getLocation()
    {
        return location;
    }
    
    protected void setLocation( Square newLocation )
    {
        location.clearOccupant();
        location = newLocation;
        newLocation.setOccupant(this);
    }
    
    private void setColor()
    {
        if (ownerId == 0)
            color = "white";
        else if (ownerId == 1)
            color = "black";
    }
    
    public String getColor()
    {
        return color;
    }
    
    public int getId()
    {
        return ownerId;
    }
    
    public int getValue()
    {
        return value;
    }
    
    public void setValue(int val)
    {
        value = val;
    }
    
    public boolean squareIsEmpty( Square destSquare )
    {
       return destSquare.getOccupant() == null;
    }
    
    public boolean squareHasOpponent( Square destSquare )
    {
        if ( ( destSquare.getOccupant().getColor() != color ) 
                && (destSquare.getOccupant() != null ))
            return true;
        else
            return false;
    }
    
    public void validatePossibleMoves( ArrayList<Square> squaresToCheck,
            ArrayList<Square[]> pieceLegalMoves )
    {
        for (int i = 0; i < squaresToCheck.size(); i++)
        {
            if ( validMove(squaresToCheck.get(i)) )
            {
                Square[] newTempSquareArray = new Square[2];
                newTempSquareArray[0] = getLocation();
                newTempSquareArray[1] = squaresToCheck.get(i) ;
                pieceLegalMoves.add(newTempSquareArray);
            }
        }
    }
    
    abstract public ArrayList<Square[]> findPieceLegalMoves();
           
    abstract public boolean validMove( Square destSquare );
    

}
