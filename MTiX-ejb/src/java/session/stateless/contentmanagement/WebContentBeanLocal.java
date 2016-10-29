/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.contentmanagement;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.servlet.http.Part;

/**
 *
 * @author JingYing
 */
@Local
public interface WebContentBeanLocal {

    public boolean signIn(String name);

    public List<ArrayList> getEventList();

    public ArrayList createWebpageInfo(long id, String type);

    public void createEventWebpage(Part filePart, String eventTitle, String synopsis, String programDetails, String rules, String details, String start, String end, String id, String type, String ext);

    public List<ArrayList> getEditedWebEventList();

    public ArrayList editWebpageInfo(long id, String type);

    public void editEventWebpage(Part filePart, String eventTitle, String synopsis, String programDetails, String rules, String details, String start, String end, String id, String type, String ext);

    public void deleteEventWebpage(String id, String type);

    public List<ArrayList> geWebpageList();

    public ArrayList reviewWebpageInfo(String id);

    public void webpageReviewed(String id, String review, String apply);
    
    public void createCompanyWebpage(Part filePart, String mission, String vision, String aboutUs, String contactDetails, String career, String otherDetails, String ext);

    public ArrayList getCompanyInfo();

    public void editCompanyWebpage(long id, Part filePart, String mission, String vision, String aboutUs, String contactDetails, String career, String otherDetails, String ext);

}
