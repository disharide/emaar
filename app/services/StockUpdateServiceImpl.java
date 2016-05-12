package services;

import java.util.Random;

import play.Logger;
import entity.EPStockRecord;
import entity.PriceChangeDirection;

public class StockUpdateServiceImpl {

	public boolean stockFetchAndUpdate() {
		// HttpClient httpClient = new DefaultHttpClient();

		boolean isUpdated = false;

		try {

			/*
			 * HttpGet httpGet = new HttpGet(
			 * "http://demo6504781.mockable.io/get/stock");
			 * 
			 * HttpResponse response = httpClient.execute(httpGet);
			 */

			EPStockRecord epStockRecord = EPStockRecord.find.order("id desc")
					.setMaxRows(1).findUnique();

			EPStockRecord incomingEpStockRecord = new EPStockRecord();

			incomingEpStockRecord.stockPrice = Double
					.parseDouble(getRandomValue(5, 7, 2));

			if (epStockRecord == null
					|| epStockRecord.stockPrice != incomingEpStockRecord.stockPrice) {

				if (epStockRecord != null) {
					double difference = epStockRecord.stockPrice
							- incomingEpStockRecord.stockPrice;
					if (difference < 0) {
						incomingEpStockRecord.changeDirection = PriceChangeDirection.UP;
					} else {
						incomingEpStockRecord.changeDirection = PriceChangeDirection.DOWN;
					}

					double percentage = Math.abs(difference) * 100
							/ epStockRecord.stockPrice;

					incomingEpStockRecord.percentageChange = percentage;

				}

				incomingEpStockRecord.timeInMillis = System.currentTimeMillis();
				incomingEpStockRecord.save();

				isUpdated = true;
			}

			Logger.info("stock price " + epStockRecord.stockPrice);
			Logger.info("incoming stock price "
					+ incomingEpStockRecord.stockPrice);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isUpdated;
	}

	private String getRandomValue(final int lowerBound, final int upperBound,
			final int decimalPlaces) {

		if (lowerBound < 0 || upperBound <= lowerBound || decimalPlaces < 0) {
			throw new IllegalArgumentException("Put error message here");
		}

		final double dbl = (new Random().nextDouble() //
				* (upperBound - lowerBound))
				+ lowerBound;
		return String.format("%." + decimalPlaces + "f", dbl);

	}
}
