/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kylegreenfield
 */

import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private static Square[] squareArray;
    private static Player[] playerArray;
    
    public Board()
    {
        squareArray = new Square[ 64 ];
        int squareCount = 0;
        for ( int r = 1; r < 9; r++)
        {
            for ( int c = 1; c < 9; c++ )
            {
                squareArray[ squareCount ] = new Square( r, c);
                squareCount++;
            }
        }
        this.setOriginalPosition();
        playerArray = new Player[ 2 ];
        playerArray[0] = new Player(this, 0);
        playerArray[1] = new ComputerPlayer(this, 1);
    }
    
    public Board getBoardCopy()
    {
        Board boardCopy = new Board();
        for (int i = 0; i < squareArray.length; i++)
        {
            boardCopy.squareArray[i].setOccupant(squareArray[i].getOccupant());
        }
        boardCopy.playerArray[0] = playerArray[0];
        boardCopy.playerArray[1] = playerArray[1];
        return boardCopy;
    }
    
    public Square findSquareByRowCol( int row, int col)
    {
        Square returnSquare = null;
        for ( int i = 0; i < squareArray.length; i++)
        {
            if (squareArray[i].getRow() == row && squareArray[i].getCol() == col )
            {
                returnSquare = squareArray[i];
                break;
            }
        }
        return returnSquare;
    }
    
    public void displayBoard()
    {
        System.out.println("\n");
        for ( int sq = 0; sq < 64; sq++)
        {
            Square currentSquare = squareArray[ sq ];
            currentSquare.displaySquare();
        }
        System.out.println("\n");
    }
    
    public void setOriginalPosition()
    {
        squareArray[0].setOccupant( new Rook(this, squareArray[0], "white"));
        squareArray[1].setOccupant( new Knight(this, squareArray[1], "white"));
        squareArray[2].setOccupant( new Bishop(this, squareArray[2], "white"));
        squareArray[3].setOccupant( new Queen(this, squareArray[3], "white"));
        squareArray[4].setOccupant( new King(this, squareArray[4], "white"));
        squareArray[5].setOccupant( new Bishop(this, squareArray[5], "white"));
        squareArray[6].setOccupant( new Knight(this, squareArray[6], "white"));
        squareArray[7].setOccupant( new Rook(this, squareArray[7], "white"));
        for ( int i = 8 ; i <= 15 ; i++)
            squareArray[i].setOccupant( new Pawn(this, squareArray[i], "white")) ;
        squareArray[56].setOccupant( new Rook(this, squareArray[56], "black"));
        squareArray[57].setOccupant( new Knight(this, squareArray[57], "black"));
        squareArray[58].setOccupant( new Bishop(this, squareArray[58], "black"));
        squareArray[59].setOccupant( new Queen(this, squareArray[59], "black"));
        squareArray[60].setOccupant( new King(this, squareArray[60], "black"));
        squareArray[61].setOccupant( new Bishop(this, squareArray[61], "black"));
        squareArray[62].setOccupant( new Knight(this, squareArray[62], "black"));
        squareArray[63].setOccupant( new Rook(this, squareArray[63], "black"));
        for ( int i = 48 ; i <= 55 ; i++)
            squareArray[i].setOccupant( new Pawn(this, squareArray[i], "black")) ;    
    }
    
    public void movePiece( Square origSquare, Square destSquare )
    {
        Piece piece = origSquare.getOccupant();
        if (origSquare == null)
            System.out.println("origSquare in movePiece is null");
        else if (origSquare.getOccupant() == null)
            System.out.println("origSquare's occupant in movePiece is null");
        else if (piece.validMove(destSquare))
        {
        if (destSquare.getOccupant() == null)
        {
            destSquare.setOccupant( origSquare.getOccupant() );
            origSquare.clearOccupant();
            piece.setLocation(destSquare);
        }
        else if ( (piece.getColor() == "white") && 
                (destSquare.getOccupant().getColor() == "black") )
        {
            playerArray[1].deletePiece(destSquare.getOccupant());
            destSquare.setOccupant( origSquare.getOccupant() );
            origSquare.clearOccupant();
            piece.setLocation(destSquare);
        }
        else if ( (piece.getColor() == "black") && 
                (destSquare.getOccupant().getColor() == "white") )
        {
            playerArray[0].deletePiece(destSquare.getOccupant());
            destSquare.setOccupant( origSquare.getOccupant() );
            origSquare.clearOccupant();
            piece.setLocation(destSquare);
        }
        }
        else
            System.out.println("\nUnable to move piece in board movepiece");
    }
    
    public Square getOrigSquare()
    {
        System.out.println("Enter Original square row 1-8");
        Scanner input = new Scanner(System.in);
        int origRow = input.nextInt();
        System.out.println("Enter Original square column 1-8");
        int origCol = input.nextInt();
        return findSquareByRowCol( origRow, origCol );
    }
    
    public Square getDestSquare()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Destination row 1-8");
        int destRow = input.nextInt();
        System.out.println("Enter Destination column 1-8");
        int destCol = input.nextInt();
        return findSquareByRowCol( destRow, destCol);
    }
    
    public void executeTurn(Player player)
    {
        updatePlayerStates();
        player.setPlayerLegalMoves(player.findPlayerLegalMoves());
        Square[] move = player.getMove();
        this.movePiece(move[0], move[1]);
        this.displayBoard();
    }
   
    public Square getSquareArray(int index)
    {
        return squareArray[index];
    }
    
    public boolean checkValidSquare(int row, int col)
    {
        return ((row >= 1 && row <= 8) && (col >= 1 && col <= 8));
    }
    
    public Player getPlayer(int i)
    {
        return playerArray[i];
    }
    
    private void updatePlayerStates()
    {
        playerArray[0].setPlayerLegalMoves(playerArray[0].findPlayerLegalMoves());
        playerArray[1].setPlayerLegalMoves(playerArray[1].findPlayerLegalMoves());
        playerArray[0].updateThreatenedPieces();
        playerArray[1].updateThreatenedPieces();
    }
    
    public static void main( String[] args )
    {
        Board board = new Board();
        board.displayBoard();
        for (int i = 1; i < 11; i++)
        {
            if (i%2 == 1)
                board.executeTurn(playerArray[0]);
            else
                board.executeTurn(playerArray[1]);
        }
        for (int i = 1; i <= 3; i++)
            System.out.println("");
        System.out.println("Player1 Score:" + board.playerArray[0].getPlayerScore());
        System.out.println("Player2 Score:" + board.playerArray[1].getPlayerScore());
    }
}
