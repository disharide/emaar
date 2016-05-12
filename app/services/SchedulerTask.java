package services;

import java.util.Date;
import java.util.TimerTask;

import play.Logger;

public class SchedulerTask extends TimerTask {

	Date now;

	final StockUpdateServiceImpl updateService = new StockUpdateServiceImpl();

	final APNSService apnsService = new APNSService();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		now = new Date(); // initialize date
		Logger.info("Time is :" + now);

		boolean isdataUpdated = updateService.stockFetchAndUpdate();

		if (isdataUpdated) {
			//apnsService.sendNote();
		}

	}

}
