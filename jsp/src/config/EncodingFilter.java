package config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class EncodingFilter implements Filter {

  // 웹 서버가 stop될 때 자동으로 호출 (예외처리문 finally와 같은역할)
  public void destroy() {
  }

  // 요청이 있을 때 선처리되는 코드
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    // TODO Auto-generated method stub
    // place your code here
    request.setCharacterEncoding("utf-8");
    // pass the request along the filter chain
    chain.doFilter(request, response);
  }

  // 필터가 load될때 자동으로 호출되는 코드
  public void init(FilterConfig fConfig) throws ServletException {
  }

}
