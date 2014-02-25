/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kylegreenfield
 */
public class Square {
    private int row;
    private int col;
    private int index;
    private Piece occupant;
    
    public Square( int row, int col)
    {
        setRow( row );
        setCol( col );
    }
    
    public void setRow( int row )
    {
        if ( row > 0 && row < 9 )
            this.row = row;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public void setCol( int col )
    {
        if ( col > 0 && col < 9 )
            this.col = col;
    }
    
    public int getCol()
    {
        return col;
    }
    
    public Piece getOccupant()
    {
        return occupant;
    }
    
    public void setOccupant( Piece newOccupant)
    {
        occupant = newOccupant;
    }
    
    public void clearOccupant()
    {
        occupant = null;
    }
    
   

    
    public void displaySquare()
    {
        if ( occupant != null )
            System.out.printf("_%s_ |", displayOccupant() );
        else
            System.out.print("_----_ |");
        if (col == 8)
            System.out.println("");
    }
    
    private String displayOccupant()
    {
       String name1 = occupant.getColor().substring(0,2);
       String name2 = occupant.getClass().toString().substring(6,8);
       return name1 + name2 ;
    }
    
    
    
}   
    
