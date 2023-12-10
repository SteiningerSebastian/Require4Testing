package services;

import java.security.SecureRandom;
import java.util.Random;
import entity.Requirement;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class RequirementService extends EntityService<Requirement> {
	// Password hashing etc. inspired by:
	// https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
	protected static final Random RANDROM = new SecureRandom();

	protected static final int ITERATIONS = 120000;

	public RequirementService() {
		super(Requirement.class);
	}
}
