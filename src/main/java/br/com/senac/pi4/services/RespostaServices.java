package br.com.senac.pi4.services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.com.senac.pi4.util.DatabaseUtil;

@Path("/resposta/{idGrupo}/{idQuestao}/{idAlternativa}/{idQuestao}")
public class RespostaServices {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@PathParam("idGrupo") Integer idGrupo, @PathParam("idQuestao") Integer idQuestao, @PathParam("idAlternativa") Integer idAlternativa, @PathParam("idQuestao") String textoQuestao) {
		Boolean status = null;
		
		try {
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
			//selecionar questao correta
			psta = conn.prepareStatement("select codAlternativa, textoAlternativa from Alternativa where codQuestao = ? and correta = ?");
			psta.setInt(1, idQuestao);
			psta.setInt(2, 1);// resposta correta sempre 1
			
			Integer codAlternativa = 0;
			String textoAlternativa = "";
			
			ResultSet rs = psta.executeQuery();
			
			while (rs.next()) {
				codAlternativa = rs.getInt("codAlternativa");
				textoAlternativa = rs.getString("nmParticipante");
			}
			
			//verifica se é a correta
			Boolean correta;
			if(codAlternativa == idAlternativa && textoAlternativa.equalsIgnoreCase(textoQuestao))
			{
				correta = true;
			}
			else
			{
				correta = false;
			}
			
			//grava no banco a questao
			PreparedStatement stmt = conn.prepareStatement("insert into questaoGrupo(codQuestao, codAlternativa, codGrupo, TextoResp, Correta) values(?, ?, ?, ?, ?)");
			stmt.setInt(1, idQuestao);
		    stmt.setInt(2, idAlternativa);
		    stmt.setInt(3, idGrupo);
		    stmt.setString(4, textoQuestao);
		    stmt.setBoolean(5, correta);
	        rowChange = stmt.executeUpdate();
			
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
