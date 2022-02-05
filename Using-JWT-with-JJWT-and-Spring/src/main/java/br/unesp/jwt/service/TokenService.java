package br.unesp.jwt.service;

//Imports:
import br.unesp.jwt.exception.ExpiredTokenException;
import br.unesp.jwt.model.User;
import com.fasterxml.jackson.core.JsonParseException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    //Attributes:
    private String key = "s3cr3t";
    private static final long expirationTime = 60000;

    //Methods:
    public String generateToken(User user){
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(String.valueOf(user.getID()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, this.key)
                .compact();
    }

    public Claims decodeToken(String token) throws JsonParseException, SignatureException, ExpiredJwtException{
        return Jwts.parser()
                .setSigningKey(key) 
                .parseClaimsJws(token)
                .getBody();
    }
    
    public boolean validate(String token){
        try{
            String tokenProcessed = token.replace("Bearer ", "");
            Claims claims = this.decodeToken(tokenProcessed);

            if(claims.getExpiration().before(new Date(System.currentTimeMillis()))){
                throw new ExpiredTokenException();
            }
            
            return true;
        }catch(ExpiredTokenException | JsonParseException | SignatureException | ExpiredJwtException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}