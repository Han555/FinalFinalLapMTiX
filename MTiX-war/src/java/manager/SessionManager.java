/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;
import entity.Promotion;
import entity.SectionEntity;
import entity.SessionEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import session.stateless.ticketing.BookingSessionLocal;

/**
 *
 * @author catherinexiong
 */
public class SessionManager {
    private BookingSessionLocal bsbl;
    
    public SessionManager(BookingSessionLocal bsbl) {
        this.bsbl= bsbl;
    }
    
    public List<SessionEntity> getSessionsByEventIdSorted(Long id) {
        return bsbl.getSessionsByEventIdSorted(id);
    }
    
    public List<SessionEntity> getSessionsBySubeventId(Long id) {
        return bsbl.getSessionsBySubeventId(id);
    }
    public Double getPriceBySessionAndSectionId(Long sessionId, Long sectionId){
        return bsbl.getPriceBySessionAndSectionId(sessionId, sectionId);
    }
    
    public List<SectionEntity> getReservedSectionsBySessionId (Long id){
        return bsbl.getReservedSectionsBySessionId(id);
    }
    public List<SectionEntity> getClosedSectionsBySessionId (Long id) {
    return bsbl.getClosedSectionsBySessionId(id);
}
    
    public HashMap<Long, List<Double>> getSessionsPricingByEventId(Long id) {
        return bsbl.getSessionsPricingByEventId(id);
        
    }
    
    public List<Double> getSessionsPricingBySessionId(Long id,String type) {
        return bsbl.getSessionsPricingBySessionId(id,type);
        
    }
    
    public Collection<Promotion> getPromotionsByEventId(Long id){
        return bsbl.getPromotionsByEventId(id);
    }
    public Collection<Promotion> getPromotionsBySubEventId(Long id){
        return bsbl.getPromotionsBySubEventId(id);
    }
    
    public Boolean addToCartByUsernameFree(String username, Long sessionId, Long promotionId, String numOfTickets, String price){
        return bsbl.addToCartByUsernameFree(username, sessionId, promotionId, numOfTickets, price);
    }
    
    public Boolean addToCartByUsernameFreeSection(String username, Long sessionId, Long promotionId, String numOfTickets, String price,String section){
        return bsbl.addToCartByUsernameFreeSection(username, sessionId, promotionId, numOfTickets, price,section);
    }
    
    public List<Integer> getTicketSalesBySessionId(Long id, String type){
        return bsbl.getTicketSalesBySessionId(id,type);
    }
}
