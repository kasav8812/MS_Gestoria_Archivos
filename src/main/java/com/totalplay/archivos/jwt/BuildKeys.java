package com.totalplay.archivos.jwt;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class BuildKeys {

	private ClassLoader classLoader = getClass().getClassLoader();

	public PublicKey getPublicKey() throws Exception {
		try {
			String stringArchivo = IOUtils
					.toString(classLoader.getResourceAsStream("public.pem"), StandardCharsets.UTF_8.toString()).trim();
			byte[] decode = Base64.decodeBase64(stringArchivo);
			X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(decode);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publickey = keyFactory.generatePublic(pubSpec);
			return publickey;
		} catch (Exception e) {
			throw new Exception("Error al crear la llave publica");
		}
	}

}
