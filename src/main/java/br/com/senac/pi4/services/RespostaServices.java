package br.com.senac.pi4.services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.senac.pi4.util.DatabaseUtil;

@Path("/resposta")
public class RespostaServices {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@FormParam("idGrupo") Integer idGrupo, @FormParam("idQuestao") Integer idQuestao, @FormParam("idAlternativa") Integer idAlternativa, @FormParam("idQuestao") String textoQuestao) {
		Boolean status = null;
		
		try {
			//usuarioLogado = Logar(email, senha);
			status = gravarResposta(idGrupo, idQuestao, idAlternativa, textoQuestao);
		} catch (Exception e) {
			return Response.status(500).entity(null).build();	
		}
		if (status == null)
			return Response.status(404).entity(null).build();
		
		
		return Response.status(200).entity(status).build();
	}
	
	public Boolean gravarResposta(Integer idGrupo, Integer idQuestao, Integer idAlternativa, String textoQuestao) throws Exception {
		
		Boolean gravou = false;
		int rowChange = 0;
		Connection conn = null;
		PreparedStatement psta = null;
		
		try {
			conn = DatabaseUtil.get().conn();		
			//ISERT
			
			if(rowChange > 0)
			{
				gravou = true;
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
		
		return gravou;
	}

}
