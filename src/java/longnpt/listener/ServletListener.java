/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnpt.listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Admin
 */
public class ServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        InputStream in = null;
        try {
            ServletContext context = sce.getServletContext();
            Map<String, String> listMap = new HashMap<>();
            String realPath = context.getRealPath("/");
            in = new FileInputStream(realPath + "WEB-INF/mapList.txt");
            ResourceBundle rs = new PropertyResourceBundle(in);
            Enumeration<String> keys = rs.getKeys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                String a = rs.getString(key);
                listMap.put(key, rs.getString(key));
            }
            context.setAttribute("LIST_MAP", listMap);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ServletListener.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
