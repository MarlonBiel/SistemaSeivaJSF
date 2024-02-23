
package br.edu.ifpr.irati.ads.filter;

import br.edu.ifpr.irati.ads.mb.LoginMB;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns="/restricted/*")
public class LoginFilter implements Filter{
    
    @Inject
    private LoginMB loginMb;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        if(loginMb.getUsuario() == null){
            HttpServletResponse resp = (HttpServletResponse) sr1;
            resp.sendRedirect("/SistemaSeiva/index.xhtml");
            return;
        }
        fc.doFilter(sr, sr1);
    }

    @Override
    public void destroy() {
        
    }
    
}
