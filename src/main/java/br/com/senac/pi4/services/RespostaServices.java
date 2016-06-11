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

import br.com.senac.pi4.entity.Resposta;
import br.com.senac.pi4.util.DatabaseUtil;

@Path("/resposta/{idGrupo}/{idQuestao}/{idAlternativa}/{textoQuestao}")
public class RespostaServices {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@PathParam("idGrupo") Integer idGrupo, @PathParam("idQuestao") Integer idQuestao, @PathParam("idAlternativa") Integer idAlternativa, @PathParam("textoQuestao") String textoQuestao) {
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
		Boolean respostaCorreta = false;
		
		int rowChange = 0;
		Connection conn = null;
		PreparedStatement psta = null;
		List<Resposta> alternativas = new ArrayList<Resposta>();
		
		try {
			conn = DatabaseUtil.get().conn();		
			//selecionar questao correta
			psta = conn.prepareStatement("select a.codAlternativa, 	a.textoAlternativa,	a.correta,	q.codTipoQuestao from 	Alternativa	a inner join	questao q on a.codquestao = q.codquestao where 	a.codQuestao = ?");
			psta.setInt(1, idQuestao);
			
			ResultSet rs = psta.executeQuery();
			
			while (rs.next()) {
				Resposta r = new Resposta();
				r.setCodAlternativa(rs.getInt("codAlternativa"));
				r.setTextoQuestao(rs.getString("textoAlternativa"));
				r.setCorreta(rs.getBoolean("correta"));
				r.setCodTipoQuestao(rs.getString("codTipoQuestao"));
				
				alternativas.add(r);
			}
			
			
			//alternativa
			if(alternativas.get(0).getCodTipoQuestao().equalsIgnoreCase("A")){
				for (Resposta item : alternativas) {
					if(item.getCodAlternativa() == idAlternativa && item.getCorreta())
					{
						respostaCorreta = true;
					}
				}
			}
			//texto
			else if(alternativas.get(0).getCodTipoQuestao().equalsIgnoreCase("T"))
			{
				for (Resposta item : alternativas) {
					if(item.getTextoQuestao().equalsIgnoreCase(textoQuestao))
					{
						respostaCorreta = true;
						idAlternativa = item.getCodAlternativa();
					}
				}
			}
			//booleana
			else if(alternativas.get(0).getCodTipoQuestao().equalsIgnoreCase("V"))
			{
				for (Resposta item : alternativas) {
					if(item.getCorreta() ==  Boolean.parseBoolean(textoQuestao))
					{
						respostaCorreta = true;
						idAlternativa = item.getCodAlternativa();
					}
				}
			}
			
			//grava no banco a questao
			PreparedStatement stmt = conn.prepareStatement("insert into questaoGrupo(codQuestao, codAlternativa, codGrupo, TextoResp, Correta) values(?, ?, ?, ?, ?)");
			stmt.setInt(1, idQuestao);
		    stmt.setInt(2, idAlternativa);
		    stmt.setInt(3, idGrupo);
		    stmt.setString(4, textoQuestao);
		    stmt.setBoolean(5, respostaCorreta);
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
