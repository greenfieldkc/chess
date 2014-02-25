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
public class Player {
    private String name;
    private int playerId; //0 for white and 1 for black
    private int opponentId;
    private String color;
    private double playerScore;
    private ArrayList<Piece> activePieces;
    protected ArrayList<Square[]> legalMoves;
    protected ArrayList<Piece> threatenedPieces;
    protected Board board;
    
    
    public Player(Board board, int id)
    {
        this.board = board;
        setPlayerandOpponentId(id);
        setPlayerColor(id);
        setPlayerName(id);
        activePieces = new ArrayList();
        legalMoves = new ArrayList();
        threatenedPieces = new ArrayList();
        setOriginalActivePieces(id);
    }
    
    private void setPlayerandOpponentId(int id)
    {
        if (id == 0)
        {
            playerId = 0;
            opponentId = 1;
        }
        else if (id == 1)
        {
            id = 1;
            opponentId = 0;
        }
        else
            System.out.println("Problem setting Player/Opponent Id...");
    }
    
    private void setPlayerColor(int id)
    {
        if (id == 0)
            color = "white";
        else if (id == 1)
            color = "black";
        else
            System.out.println("Problem setting Player color..");
    }
    
    private void setPlayerName(int id)
    {
        if (id == 0)
            name = "Player 1";
        else if (id == 1)
            name = "Player 2";
        else
            System.out.println("Problem setting Player name...");
    }
    
    private void setOriginalActivePieces(int id)
    {
        if (id == 0)
        {
            for ( int i = 0; i < 16; i++)
            {
                activePieces.add(board.getSquareArray(i).getOccupant() );
            }
        }
        else if ( id == 1)
        {
            for ( int i = 48; i < 64; i++ )
            {
                activePieces.add(board.getSquareArray(i).getOccupant() );
            }
        }
        else
            System.out.println("Problem adding pieces to activePieces...");
    }
    
    public double getPlayerScore()
    {
        updatePlayerScore();
        return playerScore;
    }
    
    protected void updatePlayerScore()
    {
        playerScore = 0;
        ArrayList<Square[]> tempLegalMoves = findPlayerLegalMoves();
        for (int i = 0; i < activePieces.size(); i++)
        {   
            Piece piece = activePieces.get(i);
            //System.out.println();
            //System.out.println("Piece in updatePlayerScore: " + piece.getColor() +
            //        piece.toString());
            playerScore += piece.getValue();
            //System.out.println("New playerScore end of if: " + playerScore);
        }
        playerScore += (tempLegalMoves.size() * 0.5);
        for (int i = 0; i < threatenedPieces.size(); i++)
        {
            Piece piece = threatenedPieces.get(i);
            playerScore -= (piece.getValue()*0.5);
        }
        for (int i = 0; i < board.getPlayer(Math.abs(playerId-1)).threatenedPieces.size();
                i++)
        {
            Piece piece = board.getPlayer(Math.abs(playerId-1)).threatenedPieces.get(i);
            playerScore += (piece.getValue()*0.25);
        }
        //could also add incentive for occupying center squares
    }
    
    public ArrayList<Square[]> findPlayerLegalMoves()
    {
        ArrayList<Square[]> tempLegalMoves = new ArrayList<Square[]>();
        for (int i = 0; i < activePieces.size(); i++)
        {
            //System.out.println("activePieces" + activePieces.get(i).toString());
            Piece tempPiece = activePieces.get(i);
            tempLegalMoves.addAll(tempPiece.findPieceLegalMoves());
            //System.out.println("legalmove size: " + legalMoves.size());
        }
        return tempLegalMoves;
    }
    
    public void setPlayerLegalMoves(ArrayList<Square[]> tempLegalMoves)
    {
        legalMoves = tempLegalMoves;
    }
    
    public ArrayList<Square[]> getLegalMoves()
    {
        return legalMoves;
    }
    
    public Square[] getMove()
    {
        Square[] selectedMove = new Square[2];
        Square origSquare = board.getOrigSquare();
        Square destSquare = board.getDestSquare();
        if ( board.checkValidSquare(origSquare.getRow(), origSquare.getCol()) && 
                board.checkValidSquare(destSquare.getRow(), destSquare.getCol()) )
        {
            selectedMove[0] = origSquare;
            selectedMove[1] = destSquare;
        }
        else 
            getMove();
        return selectedMove;
    }
    
    public void deletePiece(Piece piece)
    {
        
        activePieces.remove(piece);
    }
    
    public void addPiece( Piece piece )
    {
        activePieces.add(piece);
    }

    public void updateThreatenedPieces()
    {
        threatenedPieces.clear();
        ArrayList<Square[]> opponentMoves = board.getPlayer(Math.abs(playerId-1)).getLegalMoves();
        for (int i = 0; i < opponentMoves.size(); i++)
        {
            Piece tempPiece = opponentMoves.get(i)[1].getOccupant();
            if (tempPiece != null && !threatenedPieces.contains(tempPiece))
                threatenedPieces.add(tempPiece);
        }
    }

}

