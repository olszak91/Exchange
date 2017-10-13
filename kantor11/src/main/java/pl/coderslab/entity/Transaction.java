package pl.coderslab.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "transactions")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@DecimalMin("0.0")
	private double rate;
	@NotNull
	@DecimalMin("0.0")
	private double amountOfCur;
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
	private Currency curr;
	@NotNull
	@DecimalMin("0.0")
	private double amountOfPln;
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
	private TransactionType type;
	private Date date;
	private double income;
	
	public double getIncome() {
		return income;
	}
	public void setIncome() {
		if(this.type.getName().equals("Sell")) {
			this.income = (this.getRate()-this.getCurr().getAvgRate())*this.getAmountOfCur();

		}else {
			this.income = 0;
		}
		
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate() {
		this.date = new Date();
	}
	public TransactionType getType() {
		return type;
	}
	public void setType(TransactionType type) {
		this.type = type;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getAmountOfCur() {
		return amountOfCur;
	}
	public void setAmountOfCur(double amountOfCur) {
		this.amountOfCur = amountOfCur;
	}
	public Currency getCurr() {
		return curr;
	}
	public void setCurr(Currency curr) {
		this.curr = curr;
	}
	
	public double getAmountOfPln() {
		return amountOfPln;
	}
	public void setAmountOfPln(double amountOfPln) {
		this.amountOfPln = amountOfPln;
	}
	
	public double change() {
		if(this.type.getName().equals("Sell")) {
			return this.amountOfPln-this.amountOfCur*this.rate;
		} 
		else return 0;
	}
	
	
	
}
