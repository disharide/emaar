package entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user_devices")
public class UserDevices extends RootEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3304817642959240364L;

	public String deviceId;

	public String deviceToken;

}
