package org.mike.validators;

import org.mike.domain.Product;
import org.mike.exceptions.ValidationException;

public class ProductValidator {

public void validateProduct(Product product) throws ValidationException {
	StringBuilder stringBuilder = new StringBuilder();
	if (product.getName().length() < 3 || product.getName().length() > 100) {
		stringBuilder.append("Product name must be between 3 and 100 characters \n");
	}
	if (product.getDescription().length() < 3 || product.getDescription().length() > 250) {
		stringBuilder.append("Product description must be between 3 and 250 characters \n");
	}
	if (product.getPrice() < 1 || product.getPrice() > 1000) {
		stringBuilder.append("Product price must be between 1 and 1000 \n");
	}
	if (product.getQuantity() < 0 || product.getQuantity() > 25) {
		stringBuilder.append("Product quantity must be between 1 and 25 \n");
	}
	if (!stringBuilder.isEmpty()) {
		throw new ValidationException(stringBuilder.toString());
	}
}
}
