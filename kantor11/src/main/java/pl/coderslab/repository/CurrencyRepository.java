package pl.coderslab.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.coderslab.entity.Currency;
import pl.coderslab.entity.Transaction;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

	
	
	Currency findByName(String lastname);
	
	@Modifying
	@Query("update Currency u set u.amount = ?1 where u.name = ?2")
	void setCurrAmount(double amount, String name);
	
	@Modifying
	@Query("update Currency u set u.AvgRate = ?1 where u.name = ?2")
	void setCurrAvgRate(double amount, String name);
	
	@Modifying
	 @Query("Select c from Currency c where c.symbol not like ?1")
	 List<Currency> findCurrency(String place);
	
}
