package bitcamp.myapp.member;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberDao {
  Member findByEmail(
          @Param("email") String email
  );

  int updatePassword(
          @Param("email") String email,
          @Param("password") String password
  );

  int updateAllPassword(String password);
}
