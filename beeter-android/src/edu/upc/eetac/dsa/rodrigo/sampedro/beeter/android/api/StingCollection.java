package edu.upc.eetac.dsa.rodrigo.sampedro.beeter.android.api;

import java.util.ArrayList;
import java.util.List;


public class StingCollection {
	
	private List<Sting> stings = new ArrayList<Sting>();
	private List<Link> links = new ArrayList<Link>();
	
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> rw) {
		this.links = rw;
	}
	public void addSting(Sting sting)
	{
		stings.add(sting);
	}
	public void addLink(Link link) {
		links.add(link);
	}

	public List<Sting> getStings() {
		return stings;
	}

	public void setStings(List<Sting> stings) {
		this.stings = stings;
	}

}
