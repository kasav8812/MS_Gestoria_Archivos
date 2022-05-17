package com.totalplay.archivos.jwt;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import  java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class BuildJwt {

	public  SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
	
	@Autowired
	private BuildKeys buildKeys;
	
	
	public Map<String, Object> validateJwt(String token) throws JwtException {
		try {
			Map<String, Object> result = new HashMap<>();
			Claims claims = (Claims) Jwts.parser().setSigningKey(buildKeys.getPublicKey()).parseClaimsJws(token).getBody();
			result.put("expiration", formatoFecha.format(claims.getExpiration()));
			return result;
		} catch (Exception e) {
			throw new JwtException("Error al obteber el token");
		}
	}
}
