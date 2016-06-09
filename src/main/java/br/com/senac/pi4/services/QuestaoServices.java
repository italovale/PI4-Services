package br.com.senac.pi4.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.senac.pi4.entity.Alternativa;
import br.com.senac.pi4.entity.Questao;
import br.com.senac.pi4.util.DatabaseUtil;

@Path("/questao")
public class QuestaoServices {
	
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEvento(@PathParam("param") Integer eventoId) {
	

		Questao questao = new Questao();
	
		try {
			questao = selectQuestaoAtiva(eventoId);
		} catch (Exception e) {
			return Response.status(500).entity(null).build();	
		}
		if (questao == null)
			return Response.status(404).entity(null).build();
		
		
		return Response.status(200).entity(questao).build();
	}
	
	public Questao selectQuestaoAtiva (int eventoId) throws Exception {
		Connection conn = null;
		PreparedStatement psta = null;
		Questao questao = new Questao();
		
		try {
			conn = DatabaseUtil.get().conn();

			psta = conn.prepareStatement("select q.codQuestao, q.textoQuestao, q.codTipoQuestao, qe.tempo, a.codAlternativa, a.textoAlternativa from questao q inner join QuestaoEvento qe on q.codQuestao = qe.codQuestao left join	Alternativa a on q.codQuestao = a.codQuestao where	ativo = 1 and codstatus = 'A' and codevento = ?");
			//psta = conn.prepareStatement("select q.codQuestao, q.textoQuestao, q.codTipoQuestao, qe.tempo, a.codAlternativa, a.textoAlternativa from	questao q inner join QuestaoEvento qe on q.codQuestao = qe.codQuestao left join	Alternativa a on q.codQuestao = a.codQuestao where codstatus = 'A' and codevento = ?");
			psta.setInt(1, eventoId);
			
			
			//
			ResultSet rs = psta.executeQuery();
			List<Alternativa> a = new ArrayList<Alternativa>();
			
			while (rs.next()) {
				
				questao.setCodQuestao(rs.getInt("codQuestao"));
				questao.setTextoQuestao(rs.getString("textoQuestao"));
				questao.setCodTipoQuestao(rs.getString("codTipoQuestao"));
				
				if(questao.getCodTipoQuestao().equalsIgnoreCase("A"))
				{
					Alternativa alter = new Alternativa();
					alter.setCodAlternativa(rs.getInt("codAlternativa"));
					alter.setTextoAlternativa(rs.getString("textoAlternativa"));
					a.add(alter);
				}
				
			}
			
			if(a != null)
			{
				questao.setAlternativas(a);
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
		
		return questao;
	}
	

}
