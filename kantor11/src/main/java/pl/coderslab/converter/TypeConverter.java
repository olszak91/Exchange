package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.entity.Currency;
import pl.coderslab.entity.TransactionType;
import pl.coderslab.repository.CurrencyRepository;
import pl.coderslab.repository.TypeRepository;

public class TypeConverter implements Converter<String, TransactionType>{

	@Autowired
	private TypeRepository repo;
	@Override
	public TransactionType convert(String source) {
		TransactionType trans = repo.findByName(source);
	return trans;
	}
}
