package sqlmap;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisManager {

  // 싱글톤 패턴으로 SqlSessionFactory 생성
  // SqlSessionFactoryBuilder => SqlSessionFactory =>
  // SqlSession (sql 실행 객체: JDBC의 Statement+ResultSet 객체)
  private static SqlSessionFactory instance;

  private MybatisManager() {
  } // private 생성자
  // 클래스 설계목적상 객체 생성을 막아놓음

  public static SqlSessionFactory getInstance() {
    Reader reader = null;
    try {
      // xml설정파일의 정보를 읽기
      reader = Resources.getResourceAsReader("sqlmap/sqlMapConfig.xml");
      // reader의 정보로 세션팩토리를 이용하여 세션팩토리 선언
      instance = new SqlSessionFactoryBuilder().build(reader);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (reader != null)
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
    // 세션팩토리 리턴
    return instance;
  } // getInstance()

}
