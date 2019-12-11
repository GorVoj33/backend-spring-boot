package test.project.myproject.jwt.resource;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import test.project.myproject.domain.User;
import test.project.myproject.jwt.JwtTokenUtil;
import test.project.myproject.jwt.JwtUserDetails;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class JwtAuthenticationRestController {

  @Value("${jwt.http.request.header}")
  private String tokenHeader;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserDetailsService jwtInMemoryUserDetailsService;

  @RequestMapping(value = "${jwt.get.token.uri}", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
      throws AuthenticationException {
	//String pass = new BCryptPasswordEncoder().encode(rawPassword) 
	  System.out.println("SAD IDE AUTENT: "+authenticationRequest.getUsername() +" ----- "+ authenticationRequest.getPassword());
    authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

    final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    String role = userDetails.getAuthorities().toString();
    if(role.contains("admin") || role.contains("ADMIN")) role = "admin";
    else role="user";
    System.out.println("ULOGA KORISNIKA JE : "+role);
    
    final String token = jwtTokenUtil.generateToken(userDetails);
//    User u = new User();
//    u.setName(userDetails.get);
//    return ResponseEntity.ok(new JwtTokenResponse(token, userDetails));
    return ResponseEntity.ok(new JwtTokenResponse(token,role));
  }

  @RequestMapping(value = "${jwt.refresh.token.uri}", method = RequestMethod.GET)
  public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
    String authToken = request.getHeader(tokenHeader);
    final String token = authToken.substring(7);
    String username = jwtTokenUtil.getUsernameFromToken(token);
    JwtUserDetails user = (JwtUserDetails) jwtInMemoryUserDetailsService.loadUserByUsername(username);
    String role = user.getAuthorities().toString();
    if(role.contains("admin") || role.contains("ADMIN")) role = "admin";
    else role="user";
    System.out.println("ULOGA KORISNIKA JE : "+role);
    if (jwtTokenUtil.canTokenBeRefreshed(token)) {
      String refreshedToken = jwtTokenUtil.refreshToken(token);
      return ResponseEntity.ok(new JwtTokenResponse(refreshedToken,role));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }

  @ExceptionHandler({ AuthenticationException.class })
  public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }

  private void authenticate(String username, String password) {
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new AuthenticationException("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new AuthenticationException("INVALID_CREDENTIALS", e);
    }
  }
}

