package com.beans.validation;

import java.util.InputMismatchException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FormivalidointiValidator implements
		ConstraintValidator<Formivalidointi, String> {

	public void initialize(Formivalidointi formivalidointi) {

	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		return false;
	}
}