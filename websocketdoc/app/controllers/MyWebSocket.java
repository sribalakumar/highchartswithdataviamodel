package controllers;
import play.Logger;
import play.mvc.*;

import play.mvc.Http.*;
import models.*;
import play.libs.F;
import java.lang.*;

public class MyWebSocket extends WebSocketController {
 
	public static void hello(String name) {
        outbound.send("Hello %s!", name); // line 13
    }
	
	
	public static void echo() {
        while(inbound.isOpen()) {
             WebSocketEvent e = await(inbound.nextEvent());
             if(e instanceof WebSocketFrame) {
                  WebSocketFrame frame = (WebSocketFrame)e;
                  if(!((WebSocketFrame) e).isBinary) {
                      if(frame.textData.equals("quit")) {
                          outbound.send("Bye!");
                          disconnect();
                      } else {
                          outbound.send("Echo: %s", frame.textData);
                      }
                  }
             }
             if(e instanceof WebSocketClose) {
                 Logger.info("Socket closed!");
             }
        }
    }
}