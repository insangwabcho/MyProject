package common;

import javax.servlet.http.Cookie;

public class Util {

  public static String getCookie(Cookie[] cookies, String name) {
    String result = "";
    if (cookies != null) { // 쿠키가 존재하면
      for (int i = 0; i < cookies.length; i++) {
        // 원하는 쿠키 변수값을 저장
        if (cookies[i].getName().equals(name)) {
          result = cookies[i].getValue();
          break;
        }
      }
    }

    return result;
  }

}
