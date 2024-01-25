package rasmus.nisha.codersblog.services;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    static private final String keySecret = "lbkb238onKHGk377dhkhhgjhoakhnwlggbau654gy3hhdkjkgGGJgqyg5";
    static private final int jwtExpiration = 1000 * 60 * 60 * 2; //in ms

    private Key getSignInKey(){
        byte[] keyByte = Decoders.BASE64.decode(keySecret);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(getSignInKey()).compact();
    }

    public String extractUsernameFromToken(String jwt){
        String cleanToken = cleanToken(jwt);
        return Jwts.parserBuilder().setSigningKey(getSignInKey())
                .build().parseClaimsJws(cleanToken).getBody().getSubject();
    }

    private String cleanToken(String jwt) {
        if(jwt.startsWith("Bearer ")){
            return jwt.substring(7);
        }
        return jwt;
    }

    public boolean isValidToken(String jwt, UserDetails userDetails){
        try{
            String cleanToken = cleanToken(jwt);
            Jwts.parserBuilder().setSigningKey(getSignInKey())
                    .build().parse(cleanToken);
            return true;
        } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e){
            e.getMessage();
        }
        return false;
    }
}
