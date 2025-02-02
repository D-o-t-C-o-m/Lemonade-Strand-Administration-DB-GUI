package org.mike.validators;

import org.mike.domain.Supplier;
import org.mike.exceptions.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierValidator {

public void validateSupplier(Supplier supplier) {
	StringBuilder stringBuilder = new StringBuilder();
	String name = supplier.getName().trim();
	if (name.length() < 3 || supplier.getName().length() > 100) {
		stringBuilder.append("Supplier name must be between 3 and 100 characters");
	}

	String email = supplier.getEmail().trim();

	if (email.length() > 100 || supplier.getEmail().length() < 3) {
		stringBuilder.append("Supplier email must be between 3 and 100 characters");
	}

	String regex = "^(.+)@(.+)$";
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(email);

	if (!matcher.matches()) {
		stringBuilder.append(" Supplier email should be in the pattern: Email@email.com");
	}
	if (!stringBuilder.isEmpty()) {
		throw new ValidationException(stringBuilder.toString());
	}
}

}
