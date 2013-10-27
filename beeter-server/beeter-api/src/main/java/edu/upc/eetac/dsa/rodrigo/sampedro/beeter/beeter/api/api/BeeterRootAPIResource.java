package edu.upc.eetac.dsa.rodrigo.sampedro.beeter.beeter.api.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import edu.upc.eetac.dsa.rodrigo.sampedro.beeter.beeter.api.links.BeeterAPILinkBuilder;
import edu.upc.eetac.dsa.rodrigo.sampedro.beeter.beeter.api.model.BeeterRootAPI;

@Path("/")
public class BeeterRootAPIResource {

	//instancia de loguardado en context ala variables
	@Context
	private UriInfo uriInfo;
 
	// TODO: Return links
	
	@GET
	@Produces(MediaType.BEETER_API_LINK_COLLECTION)
	public BeeterRootAPI GetLinks()
	{
		BeeterRootAPI listalinks = new BeeterRootAPI();
		listalinks.addLink(BeeterAPILinkBuilder.buildURIRootAPI(uriInfo));
		listalinks.addLink(BeeterAPILinkBuilder.buildTemplatedURIStings(uriInfo, "stings",false));
		listalinks.addLink(BeeterAPILinkBuilder.buildTemplatedURIStings(uriInfo, "stings", true));				
		listalinks.addLink(BeeterAPILinkBuilder.buildURIStings(uriInfo, "sting") );
		
				
		return(listalinks);
	}
	
}
