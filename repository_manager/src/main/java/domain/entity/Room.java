package domain.entity;

import java.util.Objects;

public class Room {

    private int id;
    private String space;
    private String company;
    private String temp;
    private String goods;

    public Room(String space, String company, String temp, String goods) {
        this.space = space;
        this.company = company;
        this.temp = temp;
        this.goods = goods;
    }

    public Room(int id, String space, String company, String temp, String goods) {
        this.id = id;
        this.space = space;
        this.company = company;
        this.temp = temp;
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFullName() {
        return space + " " + company;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id &&
                Objects.equals(space, room.space) &&
                Objects.equals(company, room.company) &&
                Objects.equals(temp, room.temp) &&
                Objects.equals(goods, room.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, space, company, temp, goods);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", space='" + space + '\'' +
                ", company='" + company + '\'' +
                ", temp=" + temp +
                ", goods='" + goods + '\'' +
                '}';
    }
}
