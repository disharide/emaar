package entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.avaje.ebean.Model;

@Entity
@Table(name = "EP_Stock_Record")
public class EPStockRecord extends RootEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public double stockPrice;

	public double percentageChange;

	public PriceChangeDirection changeDirection;

	public Long timeInMillis;

	public static Model.Finder<Long, EPStockRecord> find = new Finder<>(
			EPStockRecord.class);

}
