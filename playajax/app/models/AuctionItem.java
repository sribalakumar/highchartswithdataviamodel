package models;
import java.util.*;

import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class AuctionItem extends Model {

	@OneToMany(cascade = CascadeType.PERSIST)
public List<Bid> bids = new ArrayList<Bid>();

public void addBid(Float amount) {
bids.add(new Bid(amount));
}

public Float getCurrentTopBid() {
if (bids.size() == 0) {
Float startBid=(float) 1.0; //gets this value from the user 
return startBid;
}
else {
return bids.get(bids.size()-1).amount;
}
}

public Float getNextBidPrice() {
return getCurrentTopBid() + 2.50F;
}

}