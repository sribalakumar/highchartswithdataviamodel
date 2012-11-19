package controllers;

import play.Logger;
import play.mvc.Http.WebSocketClose;
import play.mvc.Http.WebSocketEvent;
import play.mvc.Http.WebSocketFrame;
import play.mvc.WebSocketController;
import play.libs.*;

public class MyWebSocket extends WebSocketController {
 
	public static void hello(String name) {

        while(inbound.isOpen()) {
            WebSocketEvent evt = await(inbound.nextEvent());
            if(evt instanceof WebSocketFrame) {
                WebSocketFrame frame = (WebSocketFrame)evt;
                System.out.println("received: " +  frame.textData);
                if(!frame.isBinary) {
                    if(frame.textData.equals("quit")) {
                        outbound.send("Bye!");
                        disconnect();
                    } else {
                        outbound.send("Echo: %s", frame.textData);
                    }
                }
            } else if(evt instanceof WebSocketClose) {
                System.out.println("socket closed");
            }
        }
    }

}