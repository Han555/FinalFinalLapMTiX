/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.ArrayList;
import session.stateless.BulletinSessionLocal;

/**
 *
 * @author Student-ID
 */
public class BulletinManager {
    private BulletinSessionLocal bulletinSession;
    
    public BulletinManager(BulletinSessionLocal bulletinSession) {
        this.bulletinSession = bulletinSession;
    }
    
    public void releaseMessage(String message, String subject) {
        bulletinSession.broadcast(message, subject);
    }
    
    public ArrayList<ArrayList<String>> getBoard() {
        ArrayList<ArrayList<String>> board = new ArrayList();
        ArrayList<ArrayList<String>> subBoard = bulletinSession.retrieveBulletin();
        int size = subBoard.size();
        size--;
        
        for(int i=size; i>=0; i--) {
            board.add(subBoard.get(i));
        }
        
        return board;
    }
    
    public ArrayList<String> retrieveMessage(String id) {
        return bulletinSession.getMessage(id);
    }
    
    public ArrayList<ArrayList<String>> boardPage(ArrayList<ArrayList<String>> board, int offset, int noOfRecords) {
        ArrayList<ArrayList<String>> boardPage = new ArrayList();
        int size = board.size();
        int finalRecord = offset + noOfRecords;
        if(finalRecord >= size) {
            finalRecord = size;
        }
        for(int i=offset; i<finalRecord; i++) {
            ArrayList<String> page = new ArrayList();
            page.add(board.get(i).get(0));
            page.add(board.get(i).get(1));
            boardPage.add(page);
        }
        
        return boardPage;
    }
}
