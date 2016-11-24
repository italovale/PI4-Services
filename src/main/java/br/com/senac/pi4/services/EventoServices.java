package br.com.senac.pi4.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.senac.pi4.entity.Evento;
import br.com.senac.pi4.util.DatabaseUtil;

@Path("/evento")
public class EventoServices {

	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEvento(@PathParam("param") String identificador) {
		
		Evento ev = new Evento();
	
		try {
			ev = selectEvento(identificador);
		} catch (Exception e) {
			return Response.status(200).entity(false).build();	
		}
		if (ev == null)
			return Response.status(200).entity(false).build();
		
		
		return Response.status(200).entity(ev).build();
	}
	
	public Evento selectEvento (String identificador) throws Exception {
		Connection conn = null;
		PreparedStatement psta = null;
		Evento ev = new Evento();
		
		try {
			conn = DatabaseUtil.get().conn();		
			psta = conn.prepareStatement("select * from Evento where identificador = ?");
			psta.setString(1, identificador);
			
			
			//
			ResultSet rs = psta.executeQuery();
			
			while (rs.next()) {
				
				ev.setCodEvento(rs.getInt("CodEvento"));
				ev.setDescricao(rs.getString("descricao"));
				ev.setCodTipoEvento(rs.getInt("codTipoEvento"));
				ev.setCodStatus(rs.getString("codStatus"));
				ev.setCodProfessor(rs.getInt("codProfessor"));
				ev.setIdentificador(rs.getString("identificador"));
				
			}
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		} finally {
			if (psta != null)
				psta.close();
			if (conn != null)
				conn.close ();
		}
		
		return ev;
	}
}
