package edu.upc.eetac.dsa.rodrigo.sampedro.beeter.beeter.api.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import edu.upc.eetac.dsa.rodrigo.sampedro.beeter.beeter.api.model.Sting;
import edu.upc.eetac.dsa.rodrigo.sampedro.beeter.beeter.api.model.StingCollection;

@Path("/stings")
public class StingResource {
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();

	// realizamos los metodos GET que nos devuevle los stings de la base de
	// datos
	@GET
	@Produces(MediaType.BEETER_API_STING_COLLECTION)
	public StingCollection getStings() {
		StingCollection stings = new StingCollection();

		// arrancamos la conexion
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceUnavailableException(e.getMessage());
		}

		// hacemso la consulta y el array de stings
		try {
			// creamos el statement y la consulta
			Statement stmt = conn.createStatement();
			String sql = "select users.name, stings.* from users, stings where users.username=stings.username";
			// realizamos la consulta
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// creamos el sting
				Sting sting = new Sting();
				sting.setUsername(rs.getString("username"));
				sting.setAuthor(rs.getString("name"));
				sting.setContent(rs.getString("content"));
				sting.setSubject(rs.getString("subject"));
				sting.setcreationTimestamp(rs
						.getTimestamp("creation_timestamp"));

				// añadimos el sting a la lista
				stings.addSting(sting);
			}
			rs.close();
			stmt.close();
			// devolvemos a tomcat la conexion
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new InternalServerException(e.getMessage());
		}

		// devolvemos el sting
		return stings;
	}

	// realizamos el metodo POST
	@POST
	@Consumes(MediaType.BEETER_API_STING)
	@Produces(MediaType.BEETER_API_STING)
	public Sting createSting(Sting sting) {

		if(sting.getSubject().length() >100)
		{
			throw new BadRequestException("Subject length must be less or equal than 100 characters");
		}
		if(sting.getContent().length() >500)
		{
			throw new BadRequestException("Content length must be less or equal than 500 characters");
		}
		// realizamos conexion
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceUnavailableException(e.getMessage());
		}

		// leemos lo que nos manda y lo insertamos enla base de datos
		try {
			// realizamos la consulta
			Statement stmt = conn.createStatement();
			String sql = "insert into stings (username, content, subject) values ('"
					+ sting.getUsername()
					+ "', '"
					+ sting.getContent()
					+ "', '" + sting.getSubject() + "')";

			// le indicamos que nso devuelva la primary key que le genere a la
			// nueva entrada
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			// leemos la primary key
			if (rs.next()) {
				int stingid = rs.getInt(1);
				rs.close();
				// TODO: Retrieve the created sting from the database to get all
				// the remaining fields
				sql = "select creation_timestamp from stings where stingid="
						+ stingid;

				rs = stmt.executeQuery(sql);
				rs.next();
				sting.setStingid(Integer.toString(stingid));
				sting.setcreationTimestamp(rs
						.getTimestamp("creation_timestamp"));
			} else {
				// TODO: Throw exception, something has failed. Don't do now
			}

			rs.close();
			stmt.close();
			// devolvemos a tomcat la conexion
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new InternalServerException(e.getMessage());
		}
		return sting;
	}

	@GET
	@Path("/{stingid}")
	@Produces(MediaType.BEETER_API_STING)
	public Sting getSting(@PathParam("stingid") String stingid) {
		Sting sting = new Sting();

		// arrancamos la conexion
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceUnavailableException(e.getMessage());
		}

		// hacemso la consulta y el array de stings
		try {
			// creamos el statement y la consulta
			Statement stmt = conn.createStatement();
			String sql = "select users.name, stings.* from users, stings where users.username=stings.username and stingid="
					+ stingid;
			// realizamos la consulta
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			// creamos el sting
			sting.setUsername(rs.getString("username"));
			sting.setAuthor(rs.getString("name"));
			sting.setContent(rs.getString("content"));
			sting.setSubject(rs.getString("subject"));
			sting.setcreationTimestamp(rs.getTimestamp("creation_timestamp"));

			rs.close();
			stmt.close();
			// devolvemos a tomcat la conexion
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new InternalServerException(e.getMessage());
		}

		return sting;
	}

	@DELETE
	@Path("/{stingid}")
	public void deleteSting(@PathParam("stingid") String stingid) {
		// TODO Delete record in database stings identified by stingid.
		// arrancamos la conexion
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceUnavailableException(e.getMessage());
		}

		// hacemso la consulta y el array de stings
		try {
			// creamos el statement y la consulta
			Statement stmt = conn.createStatement();
			String sql = "Delete from  stings where stingid=" + stingid;
			// realizamos la consulta
			
			int rows = stmt.executeUpdate(sql);
			if (rows == 0)
				throw new StingNotFoundException();


			stmt.close();
			// devolvemos a tomcat la conexion
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new InternalServerException(e.getMessage());
		}
	}

	@PUT
	@Path("/{stingid}")
	@Consumes(MediaType.BEETER_API_STING)
	@Produces(MediaType.BEETER_API_STING)
	public Sting updateSting(@PathParam("stingid") String stingid, Sting sting) {
		// TODO: Update in the database the record identified by stingid with
		// the data values in sting

		if(sting.getSubject().length() >100)
		{
			throw new BadRequestException("Subject length must be less or equal than 100 characters");
		}
		if(sting.getContent().length() >500)
		{
			throw new BadRequestException("Content length must be less or equal than 500 characters");
		}
		
		// arrancamos la conexion
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceUnavailableException(e.getMessage());
		}

		// hacemso la consulta
		try {
			// creamos el statement y la consulta
			Statement stmt = conn.createStatement();
			String sql = "update  stings SET stings.username='"
					+ sting.getUsername()					
					+ "',stings.content='"
					+ sting.getContent()
					+ "', stings.subject='"
					+ sting.getSubject()
					+ "' where stingid="
					+ stingid;
			// realizamos la consulta
			
			int rows = stmt.executeUpdate(sql);
			if (rows == 0)
				throw new StingNotFoundException();
			
			sql = "select creation_timestamp from stings where stingid="
					+ stingid;
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			// creamos el sting			
			sting.setcreationTimestamp(rs.getTimestamp("creation_timestamp"));

			rs.close();
			stmt.close();
			// devolvemos a tomcat la conexion
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new InternalServerException(e.getMessage());
		}

		return sting;
	}

}