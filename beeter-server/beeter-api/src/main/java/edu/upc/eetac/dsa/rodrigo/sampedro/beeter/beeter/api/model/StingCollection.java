package edu.upc.eetac.dsa.rodrigo.sampedro.beeter.beeter.api.model;

import java.util.ArrayList;
import java.util.List;

public class StingCollection {
	
	private List<Sting> stings = new ArrayList<Sting>();
	
	public void addSting(Sting sting)
	{
		stings.add(sting);
	}

	public List<Sting> getStings() {
		return stings;
	}

	public void setStings(List<Sting> stings) {
		this.stings = stings;
	}

}
