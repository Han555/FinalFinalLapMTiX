/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import entity.SectionEntity;
import session.stateless.commoninfrastucture.ProductSessionLocal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

  
}