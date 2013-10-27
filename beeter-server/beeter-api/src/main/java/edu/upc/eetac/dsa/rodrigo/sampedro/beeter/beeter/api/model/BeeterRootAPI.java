package edu.upc.eetac.dsa.rodrigo.sampedro.beeter.beeter.api.model;

import java.util.ArrayList;
import java.util.List;

import edu.upc.eetac.dsa.rodrigo.sampedro.beeter.beeter.api.links.Link;

public class BeeterRootAPI {

	private List<Link> rw;

	public BeeterRootAPI() {
		rw = new ArrayList<Link>();
	}

	public void addLink(Link link) {
		rw.add(link);
	}

	public void setRw(List<Link> rw) {
		this.rw = rw;
	}

	public List<Link> getRw() {
		return rw;
	}

}
