package job;

import play.jobs.*;
import models.StatefulModel;

@OnApplicationStart(async = true)
public class Startup extends Job {
   public void doJob() throws InterruptedException {
      int i = 0;

      while (true) {
         i++;
         Thread.sleep(1000);
         StatefulModel.instance.event.publish("On step " + i);
      }
   }
}
