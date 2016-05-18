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

import br.com.senac.pi4.entity.Participante;
import br.com.senac.pi4.util.DatabaseUtil;


@Path("/participante")
public class ParticipanteServices {
	
	private Participante usuario;

	@GET
	@Path("/{email}/{senha}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@PathParam("email") String email, @PathParam("senha") String senha) {
		Participante usuarioLogado = null;
		try {
			usuarioLogado = Logar(email, senha);
		} catch (Exception e) {
			return Response.status(500).entity(null).build();	
		}
		if (usuarioLogado == null)
			return Response.status(404).entity(null).build();
		
		
		return Response.status(200).entity(usuarioLogado).build();
	}
	
	public Participante Logar (String email, String senha) throws Exception {
		
		usuario = null;
		Connection conn = null;
		PreparedStatement psta = null;
		try {
			conn = DatabaseUtil.get().conn();		
			psta = conn.prepareStatement("select * from Participante where email = ? and ativo = 1");
			psta.setString(1, email);
			//psta.setString(2, senha);
			
			
			//
			ResultSet rs = psta.executeQuery();
			
			while (rs.next()) {
				usuario = new Participante();
				usuario.setCodParticipante(rs.getInt("codParticipante"));
				usuario.setNmParticipante(rs.getString("nmParticipante"));
				usuario.setCodCurso(rs.getInt("codCurso"));
				usuario.setEmail(rs.getString("email"));
				usuario.setAtivo(rs.getBoolean("ativo"));
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
		
		return usuario;
	}
	
 }