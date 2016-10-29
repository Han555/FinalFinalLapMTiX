/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.ticketing;

import entity.Promotion;
import entity.SectionEntity;
import entity.SessionEntity;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author catherinexiong
 */
@Local
public interface BookingSessionLocal {
    
  
    public List<SessionEntity> getSessionsBySubeventId(Long id);

    public Double getPriceBySessionAndSectionId(Long sessionId, Long sectionId);

    public List<SessionEntity> getSessionsByEventIdSorted(Long id);

    public List<SectionEntity> getReservedSectionsBySessionId(Long id);

    public List<SectionEntity> getClosedSectionsBySessionId(Long id);
    
    HashMap<Long, List<Double>> getSessionsPricingByEventId(Long id);

  

    public List<Double> getSessionsPricingBySessionId(Long id, String type);

    public Collection<Promotion> getPromotionsByEventId(Long id);
    public Collection<Promotion> getPromotionsBySubEventId(Long id);

    public Boolean addToCartByUsernameFree(String username, Long sessionId, Long promotionId, String numOfTickets, String price);

    public List<Integer> getTicketSalesBySessionId(Long id, String type);

    public Boolean addToCartByUsernameFreeSection(String username, Long sessionId, Long promotionId, String numOfTickets, String price, String section);

   
}
