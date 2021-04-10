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
public class DemiseDetailDTO {

    String demiseId;
    String carId;
    String carName;
    int quantity;
    float totalPrice;
    String rentId;

    public DemiseDetailDTO() {
    }

    public DemiseDetailDTO(String demiseId, String carId, String carName, int quantity, float totalPrice, String rentId) {
        this.demiseId = demiseId;
        this.carId = carId;
        this.carName = carName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.rentId = rentId;
    }

    public String getDemiseId() {
        return demiseId;
    }

    public void setDemiseId(String demiseId) {
        this.demiseId = demiseId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRentId() {
        return rentId;
    }

    public void setRentId(String rentId) {
        this.rentId = rentId;
    }

}
