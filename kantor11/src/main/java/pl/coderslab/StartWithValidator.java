package pl.coderslab;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StartWithValidator implements ConstraintValidator<StartWith, Integer> {

	int start;
	

	@Override
	public void initialize(StartWith constraintAnnotation) {
		this.start = constraintAnnotation.value();
		
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		boolean valid;
		if(value % start == 0) {
			valid = true;
		}else {
			valid = false;
		}
		return valid;
	}
	
}