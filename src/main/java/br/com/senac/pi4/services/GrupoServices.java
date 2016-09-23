package br.com.senac.pi4.services;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.senac.pi4.entity.ParticipanteGrupo;
import br.com.senac.pi4.entity.Resposta;
import br.com.senac.pi4.util.DatabaseUtil;

import com.google.gson.Gson;
@Path("/grupo")
public class GrupoServices {
 
	public List<ParticipanteGrupo> selectUsuario (String eventoId) throws Exception {
		//exemplo de select
		Connection conn = null;
		PreparedStatement psta = null;
		List<ParticipanteGrupo> listPg = new ArrayList<ParticipanteGrupo>();
		Integer eID = null;
		eID = Integer.parseInt(eventoId);				
		try {
			conn = DatabaseUtil.get().conn();		
			psta = conn.prepareStatement("select Grupo.codGrupo,nmGrupo, Participante.codParticipante, Participante.nmParticipante, Grupo.codLider, Grupo.finalizado from Grupo  inner join ParticipanteGrupo  on Grupo.codGrupo=ParticipanteGrupo.codGrupo inner join Participante on ParticipanteGrupo.codParticipante = Participante.codParticipante where codEvento = ?");
			psta.setInt(1, eID);
			
			
			//
			ResultSet rs = psta.executeQuery();
			
			while (rs.next()) {
				ParticipanteGrupo pg = new ParticipanteGrupo();
				pg.setCodGrupo(rs.getInt("codGrupo"));
				pg.setCodParticipante(rs.getInt("codParticipante"));
				pg.setNmGrupo(rs.getString("nmGrupo"));
				pg.setNmParticipante(rs.getString("nmParticipante"));
				pg.setCodLider(rs.getInt("codLider"));
				pg.setGrupoFinalizado(rs.getInt("finalizado"));
				listPg.add(pg);
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
		return listPg;
	}

	
	
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getParticipante(@PathParam("param") String eventoId) {
 
		List<ParticipanteGrupo> listPg = null;
		
		try {
			listPg = selectUsuario(eventoId);
		} catch (Exception e) {
			return Response.status(500).entity(null).build();	
		}
		if (listPg == null)
			return Response.status(404).entity(null).build();
		
		
		return Response.status(200).entity(listPg).build();
 
	}
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveUser(ParticipanteGrupo user) {
 
		Gson gson = new Gson ();
		String jsonUser = gson.toJson(user);
		System.out.println("salvando usuario "+jsonUser);
		return Response.status(200).entity("").build();
 
	}
	
	
	@GET
	@Path("/{param}/{param1}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setGrupo(@PathParam("param") Integer idGrupo, @PathParam("param1") Integer idParticiapante) {
		Boolean result = false;
		
		try {
			
			int rowChange = 0;
			Connection conn = null;
		
			try {
				conn = DatabaseUtil.get().conn();		
				
				PreparedStatement stmt = conn.prepareStatement("insert into ParticipanteGrupo(codGrupo, CodParticiapante) values(?, ?)");
				stmt.setInt(1, idGrupo);
				stmt.setInt(2, idParticiapante);
		        //rowChange = stmt.executeUpdate();
				
				if(rowChange > 0)
				{
					result = true;
				}
				
			} catch (SQLException e) {
				throw e;
			} catch (Exception e) {
				throw e;
			} finally {
				if (conn != null)
					conn.close ();
			}
			
		} catch (Exception e) {
			return Response.status(500).entity(null).build();	
		}
		if (!result)
			return Response.status(404).entity(null).build();
		
		
		return Response.status(200).entity(result).build();
 
	}
	
	@GET
	@Path("/{param}/{param1}/{param2}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setGrupo(@PathParam("param") Integer idEvento, @PathParam("param1") Integer idParticiapante, @PathParam("param2") String nmGrupo) {
		Boolean result = false;
		
		try {
			
			int rowChange = 0;
			Connection conn = null;
		
			try {
				conn = DatabaseUtil.get().conn();		
				
				PreparedStatement stmt = conn.prepareStatement("insert into grupo(codEvento, nmGrupo, codLider)values(?, ?, ?)");
				stmt.setInt(1, idEvento);
				stmt.setInt(2, idParticiapante);
				stmt.setString(3, nmGrupo);
		        //rowChange = stmt.executeUpdate();
				
				if(rowChange > 0)
				{
					result = true;
				}
				
			} catch (SQLException e) {
				throw e;
			} catch (Exception e) {
				throw e;
			} finally {
				if (conn != null)
					conn.close ();
			}
			
		} catch (Exception e) {
			return Response.status(500).entity(null).build();	
		}
		if (!result)
			return Response.status(404).entity(null).build();
		
		
		return Response.status(200).entity(result).build();
 
	}
	
	
 
}