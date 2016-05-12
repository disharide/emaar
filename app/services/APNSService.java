package services;

import java.io.IOException;

import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotifications;

public class APNSService {

	public void sendNote() {
		try {
			PushNotificationPayload payload = PushNotificationPayload.complex();
			payload = CreatePayLoad(payload);
			PushedNotifications notes = getPayLoad(payload);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private PushedNotifications getPayLoad(PushNotificationPayload payload)
			throws CommunicationException, KeystoreException, IOException {
		return Push
				.payload(payload, "conf/iOsCerts/Certificates2.p12", "", false,
						"d2b2e3b7906d329c74a1e18e21ab7dd1636bed7e3739140054a48fd2e10aaa4c");
	}

	private PushNotificationPayload CreatePayLoad(
			PushNotificationPayload payload) {
		payload.addAlert("Can't be simpler than this!");
		return payload;
	}
}
