package domain.entity;

import java.util.Objects;

public class Contract {

    private int id;
    private String brand;
    private String business;
    private String rent;
    private String director;
    private String ondate;
    private String offdate;

    public Contract(String brand, String business, String rent, String director, String ondate, String offdate) {
        this.brand = brand;
        this.business = business;
        this.rent = rent;
        this.director = director;
        this.ondate = ondate;
        this.offdate = offdate;
    }

    public Contract(int id, String brand, String business, String rent, String director, String ondate, String offdate) {
        this.id = id;
        this.brand = brand;
        this.business = business;
        this.rent = rent;
        this.director = director;
        this.ondate = ondate;
        this.offdate = offdate;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

   /* public String getFullName() {
        return brand + " " + business;
    }*/

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOndate() {
        return ondate;
    }

    public void setOndate(String ondate) {
        this.ondate = ondate;
    }

    public String getOffdate() {
        return offdate;
    }

    public void setOffdate(String offdate) {
        this.offdate = offdate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return id == contract.id &&
                Objects.equals(brand, contract.brand) &&
                Objects.equals(business, contract.business) &&
                Objects.equals(rent, contract.rent) &&
                Objects.equals(director, contract.director) &&
                Objects.equals(ondate, contract.ondate) &&
                Objects.equals(offdate, contract.offdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, business, rent, director, ondate, offdate);
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", business='" + business + '\'' +
                ", rent='" + rent + '\'' +
                ", director='" + director + '\'' +
                ", ondate='" + ondate + '\'' +
                ", offdate='" + offdate + '\'' +
                '}';
    }
}
