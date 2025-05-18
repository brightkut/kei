package com.brightkut.kei.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static com.brightkut.kei.constant.TokenConstant.ALPHANUMERIC;
import static com.brightkut.kei.constant.TokenConstant.BEARER_PREFIX;

@UtilityClass
public class TokenUtil {

	private static final Logger log = LoggerFactory.getLogger(TokenUtil.class);

	public static String generateSalt(int length) {
		try {
			log.info("Generating salt token with length: {}", length);

			// SecureRandom instance for cryptographic strength random number generation
			SecureRandom secureRandom = SecureRandom.getInstanceStrong();
			StringBuilder token = new StringBuilder(length);

			// Generate random alphanumeric string
			for (int i = 0; i < length; i++) {
				int randomIndex = secureRandom.nextInt(ALPHANUMERIC.length());
				token.append(ALPHANUMERIC.charAt(randomIndex));
			}

			// Get the current timestamp in nanoseconds for even more uniqueness
			long timestamp = System.nanoTime();
			String uniqueId = UuidUtil.generateUUIDv7().toString().replace("-", "").substring(0, 8);

			// Combine all parts: random string + timestamp + unique UUID part
			String saltToken = token.toString() + timestamp + uniqueId;
			log.info("Salt token generated successfully: {}", saltToken);

			return saltToken;
		} catch (NoSuchAlgorithmException e) {
			log.error("No secure random algorithm available", e);
			throw new RuntimeException("No secure random algorithm available", e);
		}
	}

	public static String extractTokenFromRequest(HttpServletRequest request) {
		// Get the Authorization header from the request
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		// Check if the Authorization header is not null and starts with "Bearer "
		if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(BEARER_PREFIX)) {
			// Extract the JWT token (remove "Bearer " prefix)
			return authorizationHeader.substring(7);
		}

		// If the Authorization header is not valid, return null
		return null;
	}
}
