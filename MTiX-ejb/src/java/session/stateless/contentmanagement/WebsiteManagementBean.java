/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.contentmanagement;

import entity.Promotion;
import entity.PromotionType;
import entity.SessionCategoryPrice;
import entity.SessionEntity;
import entity.WebContentEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JingYing
 */
@Stateless
public class WebsiteManagementBean implements WebsiteManagementBeanLocal {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<ArrayList> getWebpageList() {
        Query q = em.createQuery("SELECT a FROM WebContentEntity a");

        List<ArrayList> webPageList = new ArrayList();
        ArrayList list;

        for (Object o : q.getResultList()) {
            WebContentEntity webpage = (WebContentEntity) o;
            list = new ArrayList();
            em.refresh(webpage);
            Date today = Calendar.getInstance().getTime();

            if (webpage.getApproved() && (today.after(webpage.getStart()) || today.equals(webpage.getStart()))
                    && today.before(webpage.getEnd())) {
                list.add(webpage.getId()); //0
                list.add(webpage.getEventTitle()); //1
                list.add(webpage.getFileName()); //2

                webPageList.add(list);
            }
        }

        return webPageList;
    }

    @Override
    public List<ArrayList> getWebpageListByType(String type) {
        Query q = em.createQuery("SELECT a FROM WebContentEntity a");

        List<ArrayList> webPageList = new ArrayList();
        ArrayList list;

        for (Object o : q.getResultList()) {
            WebContentEntity webpage = (WebContentEntity) o;
            list = new ArrayList();
            em.refresh(webpage);
            Date today = Calendar.getInstance().getTime();

            if (webpage.getEvent() != null) {

                if (webpage.getApproved() && today.before(webpage.getEnd()) && (today.after(webpage.getStart()) || today.equals(webpage.getStart()))
                        && webpage.getEvent().getType().equals(type)) {
                    list.add(webpage.getId()); //0
                    list.add(webpage.getEventTitle()); //1
                    list.add(webpage.getFileName()); //2

                    webPageList.add(list);
                }
            } else {
                if (webpage.getApproved() && today.before(webpage.getEnd()) && (today.after(webpage.getStart()) || today.equals(webpage.getStart()))
                        && webpage.getSubevent().getType().equals(type)) {
                    list.add(webpage.getId()); //0
                    list.add(webpage.getEventTitle()); //1
                    list.add(webpage.getFileName()); //2

                    webPageList.add(list);
                }

            }
        }

        return webPageList;
    }

    @Override
    public ArrayList getEventWebpageInfo(String id) {
        ArrayList data = new ArrayList();
        WebContentEntity content = em.find(WebContentEntity.class, Long.valueOf(id));
        em.refresh(content);

        data.add(content.getId());//0

        data.add(content.getEventTitle()); //1
        data.add(content.getSynopsis()); //2
        data.add(content.getFileName()); //3
        data.add(content.getOtherDetails()); //4
        data.add(content.getAdmissionRules()); //5
        data.add(content.getProgramDetails()); //6
        data.add((String) new SimpleDateFormat("yyyy-MM-dd").format(content.getStart())); //7
        data.add((String) new SimpleDateFormat("yyyy-MM-dd").format(content.getEnd())); //8

        if (content.getEvent() != null) {
            data.add(content.getEvent().getName()); //9
            data.add((String) new SimpleDateFormat("yyyy-MM-dd").format(content.getEvent().getStart())); //10
            data.add((String) new SimpleDateFormat("yyyy-MM-dd").format(content.getEvent().getEnd())); //11
            data.add(content.getEvent().getDescription()); //12
            data.add(content.getEvent().getProperty().getPropertyName()); //13
            data.add(content.getEvent().getId() + " event"); //14
        } else {
            data.add(content.getSubevent().getName()); //9
            data.add((String) new SimpleDateFormat("yyyy-MM-dd").format(content.getSubevent().getStart())); //10
            data.add((String) new SimpleDateFormat("yyyy-MM-dd").format(content.getSubevent().getEnd())); //11
            data.add(content.getSubevent().getEvent().getDescription()); //12
            data.add(content.getSubevent().getProperty().getPropertyName()); //13
            data.add(content.getSubevent().getId() + " subevent"); //14
        }

        return data;
    }

    @Override
    public List<ArrayList> getEventSessionInfo(String id) {
        Date todayNow = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(todayNow);
        cal.add(Calendar.HOUR, -2);
        Date today = cal.getTime(); //-2Hr
        List<ArrayList> info = new ArrayList();
        System.out.print(today);

        WebContentEntity content = em.find(WebContentEntity.class, Long.valueOf(id));
        em.refresh(content);

        if (content.getEvent() != null) {
            for (Object o : content.getEvent().getSessions()) {
                SessionEntity session = (SessionEntity) o;
                em.refresh(session);

                if (session.getPrice().size() != 0) {

                    ArrayList data = new ArrayList();
                    if (today.after(session.getTimeStart())) {
                        data.add(session.getName() + "     (Ticket Sales - CLOSED)"); //0
                    } else {
                        data.add(session.getName()); //0
                    }

                    data.add(session.getTimeStart()); //1
                    data.add(session.getTimeEnd()); //2
                    for (Object obj : session.getPrice()) {
                        SessionCategoryPrice price = (SessionCategoryPrice) obj;
                        data.add(price.getCategory().getCategoryNum()); //3
                        data.add(price.getPrice()); //4
                    }
                    info.add(data);
                }
            }
        } else {
            for (Object o : content.getSubevent().getSessions()) {
                SessionEntity session = (SessionEntity) o;
                em.refresh(session);
                ArrayList data = new ArrayList();
                if (today.after(session.getTimeStart())) {
                    data.add(session.getName() + "     (Ticket Sales - CLOSED)"); //0
                } else {
                    data.add(session.getName()); //0
                }
                data.add(session.getTimeStart()); //1
                data.add(session.getTimeEnd()); //2
                for (Object obj : session.getPrice()) {
                    SessionCategoryPrice price = (SessionCategoryPrice) obj;
                    data.add(price.getCategory().getCategoryNum()); //3
                    data.add(price.getPrice()); //4
                }
                info.add(data);
            }
        }

        return info;
    }

    @Override
    public List<ArrayList> getEventPromotionInfo(String id) {
        List<ArrayList> info = new ArrayList();

        WebContentEntity content = em.find(WebContentEntity.class, Long.valueOf(id));
        em.refresh(content);

        if (content.getEvent() != null) {
            for (Object o : content.getEvent().getPromotions()) {
                Promotion session = (Promotion) o;
                em.refresh(session);
                ArrayList data = new ArrayList();
                data.add(session.getName()); //0
                data.add(session.getDiscountRate()); //1
                data.add(session.getRequirements()); //2
                String promotionType = "";
                for (Object obj : session.getPromotionsType()) {
                    PromotionType type = (PromotionType) obj;
                    promotionType += type.getName() + ", ";
                }
                data.add(promotionType.substring(0, promotionType.length() - 2));
                info.add(data);
            }
        } else {
            for (Object o : content.getSubevent().getPromotions()) {
                Promotion session = (Promotion) o;
                em.refresh(session);
                ArrayList data = new ArrayList();
                data.add(session.getName()); //0
                data.add(session.getDiscountRate()); //1
                data.add(session.getRequirements()); //2
                String promotionType = "";
                for (Object obj : session.getPromotionsType()) {
                    PromotionType type = (PromotionType) obj;
                    promotionType += type.getName() + ", ";
                }
                data.add(promotionType.substring(0, promotionType.length() - 2));
            }
        }

        return info;
    }

    @Override
    public List<ArrayList> getCreditCardEvents() {
        List<ArrayList> arr = new ArrayList();

        ArrayList data = new ArrayList();
        Query q = em.createQuery("SELECT a FROM Promotion a");
        for (Object o : q.getResultList()) {
            data = new ArrayList();
            Promotion promotion = (Promotion) o;
            boolean isCreditCard = false;
            em.refresh(promotion);
            System.out.println(promotion.getId());

            for (Object object : promotion.getPromotionsType()) {
                PromotionType promotions = (PromotionType) object;
                if (promotions.getName().equals("Credit Card")) {
                    isCreditCard = true;
                    break;
                }
            }

            if (isCreditCard) {
                if (promotion.getEvent() != null) {
                    if (promotion.getEvent().getContent() != null) {
                        Date today = Calendar.getInstance().getTime();
                        if (promotion.getEvent().getContent().getApproved() && today.before(promotion.getEvent().getContent().getEnd()) && (today.after(promotion.getEvent().getContent().getStart())
                                || today.equals(promotion.getEvent().getContent().getStart()))) {
                            em.refresh(promotion);
                            data.add(promotion.getName()); //0
                            data.add(promotion.getDescriptions()); //1
                            data.add(promotion.getDiscountRate()); //2
                            data.add(promotion.getEvent().getName()); //3
                            data.add(promotion.getEvent().getContent().getId()); //4
                            data.add(promotion.getRequirements()); //5
                            data.add(promotion.getEvent().getContent().getFileName()); //6
                            arr.add(data);
                        }
                    }

                } else {
                    if (promotion.getSubEvent().getContent() != null) {
                        Date today = Calendar.getInstance().getTime();
                        if (promotion.getSubEvent().getContent().getApproved() && today.before(promotion.getSubEvent().getContent().getEnd()) && (today.after(promotion.getSubEvent().getContent().getStart())
                                || today.equals(promotion.getSubEvent().getContent().getStart()))) {
                            data.add(promotion.getName()); //0
                            data.add(promotion.getDescriptions()); //1
                            data.add(promotion.getDiscountRate()); //2
                            data.add(promotion.getSubEvent().getName()); //3
                            data.add(promotion.getSubEvent().getContent().getId()); //4
                            data.add(promotion.getRequirements()); //5
                            data.add(promotion.getSubEvent().getContent().getFileName()); //6
                            arr.add(data);
                        }
                    }
                }
            }
        }
        return arr;
    }

    @Override
    public List<ArrayList> getVolumeDiscountEvents() {
        List<ArrayList> arr = new ArrayList();

        ArrayList data = new ArrayList();
        Query q = em.createQuery("SELECT a FROM Promotion a");
        for (Object o : q.getResultList()) {
            data = new ArrayList();
            Promotion promotion = (Promotion) o;
            boolean isCreditCard = false;

            for (Object object : promotion.getPromotionsType()) {
                PromotionType promotions = (PromotionType) object;
                if (promotions.getName().equals("Volume Discount")) {
                    isCreditCard = true;
                    break;
                }
            }

            if (isCreditCard) {
                if (promotion.getEvent() != null) {
                    if (promotion.getEvent().getContent() != null) {
                        Date today = Calendar.getInstance().getTime();
                        if (promotion.getEvent().getContent().getApproved() && today.before(promotion.getEvent().getContent().getEnd()) && (today.after(promotion.getEvent().getContent().getStart())
                                || today.equals(promotion.getEvent().getContent().getStart()))) {
                            data.add(promotion.getName()); //0
                            data.add(promotion.getDescriptions()); //1
                            data.add(promotion.getDiscountRate()); //2
                            data.add(promotion.getEvent().getName()); //3
                            data.add(promotion.getEvent().getContent().getId()); //4
                            data.add(promotion.getRequirements()); //5
                            data.add(promotion.getEvent().getContent().getFileName()); //6
                            arr.add(data);
                        }
                    }

                } else {
                    if (promotion.getSubEvent().getContent() != null) {
                        Date today = Calendar.getInstance().getTime();
                        if (promotion.getSubEvent().getContent().getApproved() && today.before(promotion.getSubEvent().getContent().getEnd()) && (today.after(promotion.getSubEvent().getContent().getStart())
                                || today.equals(promotion.getSubEvent().getContent().getStart()))) {
                            data.add(promotion.getName()); //0
                            data.add(promotion.getDescriptions()); //1
                            data.add(promotion.getDiscountRate()); //2
                            data.add(promotion.getSubEvent().getName()); //3
                            data.add(promotion.getSubEvent().getContent().getId()); //4
                            data.add(promotion.getRequirements()); //5
                            data.add(promotion.getSubEvent().getContent().getFileName()); //6
                            arr.add(data);
                        }
                    }
                }
            }
        }
        return arr;
    }

}
