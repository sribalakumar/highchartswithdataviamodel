package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import
com.google.gson.JsonObject;

public class Application extends Controller {

    public static void index() {
        render();
    }
    
    public static void addBid(Long id, Float amount) {
    	AuctionItem item = AuctionItem.findById(id);
    	item.addBid(amount);
    	item.save();
    	}
    public static void newBids(Long id) {
    	// count new bids
    	long newBids = Bid.count("from AuctionItem a join a.bids as b " +
    	"where a.id = ? AND b.date > ?", id, request.date);
    	// wait if needed
    	if (newBids == 0) {
              suspend("1s");
    	}
    	// return the JSON output of the new bids
    	AuctionItem item = AuctionItem.findById(id);
    	JsonObject json = new JsonObject();
    	json.addProperty("next", item.getNextBidPrice());
    	json.addProperty("top", item.getCurrentTopBid());
    	renderJSON(json.toString());
    	}

	
}