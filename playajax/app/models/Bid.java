package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Bid extends Model {

    public Float amount;
    public Date date;

    public Bid(Float amount) {
        this.amount = amount;
        this.date = new Date();
    }
}
