package services;

import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import play.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import entity.EPStockRecord;
import entity.PriceChangeDirection;

@SuppressWarnings("deprecation")
public class StockUpdateServiceImpl {

	public boolean stockFetchAndUpdate() {
		HttpClient httpClient = new DefaultHttpClient();

		boolean isUpdated = false;

		try {

			HttpGet httpGet = new HttpGet(
					"http://demo6504781.mockable.io/get/stock");

			HttpResponse response = httpClient.execute(httpGet);

			EPStockRecord epStockRecord = EPStockRecord.find.order("id desc")
					.setMaxRows(1).findUnique();

			EPStockRecord incomingEpStockRecord = new ObjectMapper().readValue(
					response.getEntity().getContent(), EPStockRecord.class);

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
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isUpdated;
	}
}
