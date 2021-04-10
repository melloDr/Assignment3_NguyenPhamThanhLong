/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnpt.dtos;

/**
 *
 * @author Admin
 */
public class HistoryDTO {
    String rentId;
    float totalCart;
    String rentalDate;
    String dateRental;
    String dateReturn;

    public HistoryDTO() {
    }

    public HistoryDTO(String rentId, float totalCart, String rentalDate, String dateRental, String dateReturn) {
        this.rentId = rentId;
        this.totalCart = totalCart;
        this.rentalDate = rentalDate;
        this.dateRental = dateRental;
        this.dateReturn = dateReturn;
    }

    public String getRentId() {
        return rentId;
    }

    public void setRentId(String rentId) {
        this.rentId = rentId;
    }

    public float getTotalCart() {
        return totalCart;
    }

    public void setTotalCart(float totalCart) {
        this.totalCart = totalCart;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getDateRental() {
        return dateRental;
    }

    public void setDateRental(String dateRental) {
        this.dateRental = dateRental;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }
    
    
}
