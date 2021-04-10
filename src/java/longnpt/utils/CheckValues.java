/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnpt.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import longnpt.dtos.CarDTO;
import longnpt.dtos.DemiseDTO;

/**
 *
 * @author Admin
 */
public class CheckValues {

    //Check String này có phải là số không
    public static int getIndexListDemise(List<DemiseDTO> list, String a) {
        int check = -1;
        if (a == null || a.isEmpty()) {
            return -1;
        }
        try {

            for (int i = 0; i < list.size(); i++) {
                if (a.equals(list.get(i).getCarId())) {
                    return i;
                }
            }
        } catch (Exception e) {
            check = -1;
        }
        return check;
    }

    public static int getIndexListCar(List<CarDTO> list, String a) {
        int check = -1;
        try {

            for (int i = 0; i < list.size(); i++) {
                int aa = list.get(i).getCarId();
                if (a.equals(list.get(i).getCarId() + "")) {
                    return i;
                }
            }
        } catch (Exception e) {
            check = -1;
        }
        return check;
    }

    public static float getTotalPriceCart(List<DemiseDTO> list) {
        float check = 0;
        try {
            for (int i = 0; i < list.size(); i++) {
                check += list.get(i).getPrice() * list.get(i).getQuantity();
            }
        } catch (Exception e) {
            check = 0;
        }
        return check;
    }

    public static int getDays(String dateRental, String dateReturn) {
        int check = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateObj1 = sdf.parse(dateRental);
            Date dateObj2 = sdf.parse(dateReturn);
            long diff = dateObj2.getTime() - dateObj1.getTime();
            check = (int) (diff / (24 * 60 * 60 * 1000));
        } catch (Exception e) {
            check = 0;
        }
        return check;
    }

}
