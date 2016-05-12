import java.util.Timer;

import play.Application;
import play.GlobalSettings;
import play.Logger;
import services.SchedulerTask;

@SuppressWarnings("deprecation")
public class Global extends GlobalSettings {

	@Override
	public void onStart(Application application) {

		Timer time = new Timer();
		SchedulerTask st = new SchedulerTask();
		time.schedule(st, 0, 30000);

		// for demo only.

		Logger.info("Execution in Main Thread....");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
