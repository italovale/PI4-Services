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

import br.com.senac.pi4.entity.Resultado;
import br.com.senac.pi4.util.DatabaseUtil;

@Path("/resultado")
public class ResultadoServices {
	
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response resultado(@PathParam("param") Integer idEvento) {
		
		List<Resultado> resultados = null;
		
		try {
			resultados = getResultado(idEvento);
		} catch (Exception e) {
			return Response.status(500).entity(null).build();	
		}
		if (resultados == null)
			return Response.status(404).entity(null).build();
		
		
		return Response.status(200).entity(resultados).build();
	}
	
	public List<Resultado> getResultado(Integer idEvento) throws Exception {
		
		List<Resultado> resultados = new ArrayList<Resultado>();
		
		Connection conn = null;
		PreparedStatement psta = null;
		try {
			conn = DatabaseUtil.get().conn();		
			psta = conn.prepareStatement("select * from Participante where email = ?");
			psta.setInt(1, idEvento);

			ResultSet rs = psta.executeQuery();
			
			while (rs.next()) {
				Resultado resultado = new Resultado();
				
				resultado.setNmGrupo(rs.getString("nmGrupo"));
				resultado.setPontosGrupo(rs.getInt("pontosGrupo"));
				resultado.setPontosTotal(rs.getInt("pontosTotal"));
				
				resultados.add(resultado);
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
		
		return resultados;
	}
}
