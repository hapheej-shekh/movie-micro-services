package hibernate.jpa.service.beans;

import java.util.List;
import java.util.Map;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class OrderCart {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CartGen")
	@SequenceGenerator(name = "CartGen", sequenceName = "CartSeq", initialValue = 1)
	private int id;
	private String status;
	private Map<Customer, List<Package>> cartDetail;
	private DeliveryAgent agent;
	
	public OrderCart() {		}
	
	public OrderCart(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<Customer, List<Package>> getCartDetail() {
		return cartDetail;
	}

	public void setCartDetail(Map<Customer, List<Package>> cartDetail) {
		this.cartDetail = cartDetail;
	}

	public DeliveryAgent getAgent() {
		return agent;
	}

	public void setAgent(DeliveryAgent agent) {
		this.agent = agent;
	}

	@Override
	public String toString() {
		return "OrderCart{id: " + id + ", status: " + status + ", cartDetail: " + cartDetail + ", agent: " + agent + "}";
	}
}
