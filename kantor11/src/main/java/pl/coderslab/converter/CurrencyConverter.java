package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.entity.Currency;
import pl.coderslab.repository.CurrencyRepository;

public class CurrencyConverter implements Converter<String, Currency> {
	@Autowired
	private CurrencyRepository repo;
	@Override
	public Currency convert(String source) {
	Currency curr = repo.findByName(source);
	System.out.println(source);
	return curr;
	}
}
