/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import session.stateless.commoninfrastucture.ProductSessionLocal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Student-ID
 */
public class ProductManager {

    private ProductSessionLocal session;

    public ProductManager(ProductSessionLocal s) {
        session = s;
    }

    public void createEvent(HttpServletRequest request) {
        try {
            String name = request.getParameter("name");
            String equipment = request.getParameter("equipment");
            Integer manpower = Integer.valueOf(request.getParameter("man"));

            String start = (String) request.getParameter("start");
            String end = (String) request.getParameter("end");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = formatter.parse(start);
            Date endDate = formatter.parse(end);

            session.createEvent(name, equipment, manpower, startDate, endDate);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void createSubEvent(HttpServletRequest request) {
        try {
            String name = request.getParameter("name");
            String equipment = request.getParameter("equipment");
            Integer manpower = Integer.valueOf(request.getParameter("man"));

            String start = (String) request.getParameter("start");
            String end = (String) request.getParameter("end");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = formatter.parse(start);
            Date endDate = formatter.parse(end);

            session.createSubEvent(name, equipment, manpower, startDate, endDate);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int createSession(HttpServletRequest request) {
        try {
            System.out.println("SESSIONNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
            String name = request.getParameter("name");
            String description = request.getParameter("description");

            int no = Integer.valueOf(request.getParameter("no"));
            String start, end;
            ArrayList<String> startArr = new ArrayList<String>();
            ArrayList<String> endArr = new ArrayList<String>();

            for (int i = 0; i < no; i++) {
                start = (String) request.getParameter("start" + i);
                end = (String) request.getParameter("end" + i);
                startArr.add(start);
                endArr.add(end);
            }

            //Checking
            ArrayList<Date> checkStart = new ArrayList<Date>();
            ArrayList<Date> checkEnd = new ArrayList<Date>();
            SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm");
            for (int j = 0; j < no; j++) {
                checkStart.add(formatter1.parse(startArr.get(j)));
                checkEnd.add(formatter1.parse(endArr.get(j)));
            }

            //Checking for time Error
            boolean timeError = false;

            for (int i = 0; i < checkStart.size(); i++){
                if (checkStart.get(i).before(checkEnd.get(i))){
                    timeError = false;
                } else {
                    timeError = true;
                }
                
                if(timeError){
                    return 0;
                }
            }
            
            for (int i = 0; i < checkStart.size(); i++) {
                System.out.println("CHECKING");
                for (int j = i + 1; j < checkStart.size(); j++) {
                    if (checkStart.get(i).before(checkStart.get(j))) {
                        if (checkEnd.get(i).before(checkStart.get(j))) {
                            System.out.println(checkEnd.get(i).toString());
                            System.out.println(checkEnd.get(j).toString());
                            System.out.println();
                            timeError = false;
                        } else {
                            timeError = true;
                            System.out.println("TIME ERROR");
                        }
                    } else if (checkStart.get(i).after(checkStart.get(j))) {
                        if (checkStart.get(i).after(checkEnd.get(j))) {
                            timeError = false;
                        } else {
                            timeError = true;
                        }
                    } else {
                        timeError = true; //Same start time also error!
                    }

                    if (timeError) {
                        return 0;
                    }
                }
            }

            //No timeError then come here 
            String dates = request.getParameter("dates");
            String[] date = dates.split(",");

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm yyyy-MM-dd");
            ArrayList<Date> startFinal = new ArrayList<Date>();
            ArrayList<Date> endFinal = new ArrayList<Date>();
            for (int i = 0; i < date.length; i++) {
                for (int j = 0; j < startArr.size(); j++) {
                    startFinal.add(formatter.parse(startArr.get(j) + " " + date[i]));
                    endFinal.add(formatter.parse(endArr.get(j) + " " + date[i]));
                }
            }
            Long id = Long.valueOf(request.getParameter("id"));
            String type = request.getParameter("type");
            int errorResult = session.createSession(name, startFinal, endFinal, description, type, id);
            return errorResult;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    public ArrayList editSession(HttpServletRequest request) {
        ArrayList data = new ArrayList();
        data.add(request.getParameter("id"));
        data.add(request.getParameter("name"));
        data.add(request.getParameter("description"));
        data.add(request.getParameter("previous"));
        data.add(request.getParameter("start"));
        data.add(request.getParameter("end"));
        data.add(request.getParameter("eventId"));
        data.add(request.getParameter("type"));
        return data;
    }

    public void editPromotion(HttpServletRequest request) {
        ArrayList data = new ArrayList();
        data.add(request.getParameter("eventId"));
        data.add(request.getParameter("eventType"));
        data.add(request.getParameter("id"));
        data.add(request.getParameter("desc"));
        data.add(request.getParameter("discount"));
        data.add(request.getParameter("requirement"));
        data.add(request.getParameter("name"));
        session.writePromotion(data);
    }
}
