package com.wancan.samformapi.security;
import com.wancan.samformapi.model.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWT {

    public String generateToken(UserModel user){
        String id = user.getId();
        Date now = new Date();
        Date expireAt = new Date(now.getTime() + (24 * 60 * 60 * 1000) );

        String token = Jwts.builder()
                .setSubject(id)
                .setIssuedAt(now)
                .setExpiration(expireAt)
                .signWith(SignatureAlgorithm.HS256,"3I39sUDaLKNljedt6dMiAcj83XEINA1PvCeJhQUsfnr8f8e3Ht3DevVc3Ie6iVAs")
                .compact();
        return  token;
    }

    public Claims parseToken(String token) throws Exception{
        Claims claims = Jwts.parserBuilder()
                .setSigningKey("3I39sUDaLKNljedt6dMiAcj83XEINA1PvCeJhQUsfnr8f8e3Ht3DevVc3Ie6iVAs")
                .build()
                .parseClaimsJws(token)
                .getBody();

        Date expiration = claims.getExpiration();
        if(expiration.before(new Date())){
            throw new Exception(HttpStatus.UNAUTHORIZED.toString());
        }
        return claims;
    }
}
