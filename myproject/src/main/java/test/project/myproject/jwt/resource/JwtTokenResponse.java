package test.project.myproject.jwt.resource;

import java.io.Serializable;

import org.springframework.security.core.userdetails.UserDetails;
// also changed
public class JwtTokenResponse implements Serializable {

  private static final long serialVersionUID = 8317676219297719109L;
  private final String role;
  private final String token;
  //private final Long id;
  //private final UserDetails userDetails;
   public JwtTokenResponse(String token, String role) {
  
        this.token = token;
        this.role=role;
        //this.id=id;
        //this.userDetails = ud;
    }

    public String getToken() {
        return this.token;
    }
    
    public String getRole() {
		return role;
	}
//    public Long getId() {
//		return id;
//	}
//    public UserDetails getUserDetails() {
//		return userDetails;
//	}
}