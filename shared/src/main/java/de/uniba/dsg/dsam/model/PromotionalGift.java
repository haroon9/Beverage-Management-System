package de.uniba.dsg.dsam.model;

public class PromotionalGift extends Incentive {
	
	private String name;
	private String type;

	public PromotionalGift(String type, String name) {
		this.type = type;
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
		
	}
}
