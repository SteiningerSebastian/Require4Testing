package services;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


import dao.SystemUserDAO;
import entity.SystemUser;
import entity.TestRun;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class UserService extends EntityService<SystemUser> {
	// Password hashing etc. inspired by:
	// https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
	protected static final Random RANDROM = new SecureRandom();

	protected static final int ITERATIONS = 120000;
	
	@Inject
	protected TestRunService testRunService;

	public UserService() {
		super(SystemUser.class);
	}

	public SystemUser identify(String username) {
		return ((SystemUserDAO) dao).identify(username);
	}
	
	@Override
	public void delete(SystemUser user) {
		for(TestRun testRun : testRunService.getByUser(user)) {
			testRun.setAssignee(null);
			testRunService.update(testRun);
		}
		super.delete(user);
	}

	public void setPassword(SystemUser user, String password) {
		if (password.length() >= 4) {
			String hash;
			try {
				hash = this.getHashedPassword(password);
				user.setPasswordHash(hash);
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				e.printStackTrace();
			}
		}
	}

	// Inspired by: https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
	public String getHashedPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		char[] chars = password.toCharArray();

		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		;
		byte[] salt = new byte[16];
		sr.nextBytes(salt);

		PBEKeySpec spec = new PBEKeySpec(chars, salt, ITERATIONS, 64 * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");

		byte[] hash = skf.generateSecret(spec).getEncoded();

		return ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
	}

	// Inspired by: https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
	private static String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);

		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	// Inspired by: https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}

	/**
	 * Change the password if the old password matches the current password.
	 * 
	 * @param user
	 * @param oldPassword
	 * @param newPassword
	 */
	public boolean changePassword(SystemUser user, String oldPassword, String newPassword) {
		String oldHash;
		try {
			oldHash = this.getHashedPassword(oldPassword);

			if (oldHash.equals(user.getPasswordHash()))
				user.setPasswordHash(this.getHashedPassword(newPassword));
			return !user.getPasswordHash().matches(oldHash);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Inspired by: https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
	 * @param password The password the user provided.
	 * @param passwordHash The hash of the stored password.
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public boolean passwordHashEqual(String password, String passwordHash)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		String[] parts = passwordHash.split(":");
		int iterations = Integer.parseInt(parts[0]);

		byte[] salt = fromHex(parts[1]);
		byte[] hash = fromHex(parts[2]);

		PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, hash.length * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
		byte[] testHash = skf.generateSecret(spec).getEncoded();

		int diff = hash.length ^ testHash.length;
		for (int i = 0; i < hash.length && i < testHash.length; i++) {
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0;
	}

	/**
	 * Check if the password matches, authenticate the user.
	 * 
	 * @param password The password for authentication.
	 * @return True if the password matches, false otherwise.
	 */
	public boolean authenticate(SystemUser user, String password) {
		try {
			return this.passwordHashEqual(password, user.getPasswordHash());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			return false;
		}
	}
}
