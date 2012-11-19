package controllers;

import play.mvc.*;
import models.*;

public class Application extends Controller {

   public static void index() {
      render();
   }

   public static class WebSocket extends WebSocketController {

      public static void listen() {
         while(inbound.isOpen()) {
            String event = await(StatefulModel.instance.event.nextEvent());
            outbound.send(event);
         }
      }
   }
}
