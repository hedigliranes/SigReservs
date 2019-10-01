package br.ufrn.imd.reservs.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufrn.imd.reservs.dominio.Usuario;



public class SegurancaFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		Usuario usuarioLogado = (Usuario) req.getSession().getAttribute("usuarioLOgado");
		
		if(usuarioLogado == null)
			res.sendRedirect("/MaterialJSF/index.jsf");
		else
			chain.doFilter(request, response);
		
	}
	
	

}
