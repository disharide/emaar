package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import entity.EPStockRecord;
import entity.UserDevices;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

public class DataUpdateController extends Controller {

	@BodyParser.Of(value = BodyParser.Json.class)
	public Result saveDeviceInfo() {
		
		JsonNode deviceJson = request().body().asJson();
		
		UserDevices devices = Json.fromJson(deviceJson, UserDevices.class);
		
		devices.save();

		return ok("SUCCESS");
	}

	public Result getLatestData() {
		EPStockRecord epStockRecord = EPStockRecord.find.order("id desc")
				.setMaxRows(1).findUnique();

		return ok(Json.toJson(epStockRecord));
	}
}
