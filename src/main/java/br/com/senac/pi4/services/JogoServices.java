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

import br.com.senac.pi4.entity.Jogo;
import br.com.senac.pi4.util.DatabaseUtil;

@Path("/jogo")
public class JogoServices {
	
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatus(@PathParam("param") Integer eventoId) {
		
		Jogo jogo = null; 
	
		try {
			jogo = verificaJogo(eventoId);
		} catch (Exception e) {
			return Response.status(200).entity(false).build();	
		}
		if (jogo == null)
			return Response.status(200).entity(false).build();
		
		
		return Response.status(200).entity(jogo).build();
	}
	
	public Jogo verificaJogo (int eventoId) throws Exception {
		Connection conn = null;
		PreparedStatement psta = null;
		Jogo jogo = new Jogo();
		
		try {
			conn = DatabaseUtil.get().conn();		
			psta = conn.prepareStatement("select * from Evento where codEvento = ?");
			psta.setInt(1, eventoId);
			
			
			//
			ResultSet rs = psta.executeQuery();
			
			while (rs.next()) {
				jogo = new Jogo();
				jogo.setStatus(rs.getString("codStatus").charAt(0));
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
		
		return jogo;
	}
}
