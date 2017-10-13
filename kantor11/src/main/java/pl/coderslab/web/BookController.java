package pl.coderslab.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.BookDao;
import pl.coderslab.PublisherConverter;
import pl.coderslab.PublisherDao;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Currency;
import pl.coderslab.entity.Publisher;
import pl.coderslab.entity.Transaction;
import pl.coderslab.entity.TransactionType;
import pl.coderslab.repository.BookRepository;
import pl.coderslab.repository.CurrencyRepository;
import pl.coderslab.repository.TransactionRepository;
import pl.coderslab.repository.TypeRepository;

@Controller
public class BookController {
	
	@Autowired
	private PublisherDao publisherDao;
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CurrencyRepository currencyRepository;
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	
	

	
	
	
	@Autowired
	public BookController(PublisherDao publisherDao, BookRepository bookRepository, CurrencyRepository currencyRepository) {
		this.publisherDao = publisherDao;
		this.bookRepository = bookRepository;
		this.currencyRepository = currencyRepository;
}
	




	@GetMapping("/addCurr")
	public String addCurr(Model model){
		model.addAttribute("currency", new Currency());
		model.addAttribute("currs",currencyRepository.findAll() );
		return "/addCurr";
	}
	
	@RequestMapping(value = "/addCurr", method = RequestMethod.POST)
	public String processForm(@Valid @ModelAttribute Currency currency, BindingResult result,Model model) {
		if (result.hasErrors()) {
			return "/addCurr";
		}
		currencyRepository.save(currency);
		return "/home";
	}
	
	@GetMapping("/addType")
	public String addType(Model model){
		model.addAttribute("type", new TransactionType());
		return "/addType";
	}
	
	@RequestMapping(value = "/addType", method = RequestMethod.POST)
	public String processForm(@Valid @ModelAttribute TransactionType type, BindingResult result) {
		if (result.hasErrors()) {
			return "/addType";
		}
		typeRepository.save(type);
		return "/home";
	}
	
	@GetMapping("/transaction")
	public String addTrans(Model model){
		model.addAttribute("transaction", new Transaction());
		model.addAttribute("currs",currencyRepository.findCurrency("PLN"));
		model.addAttribute("types",typeRepository.findAll());
		TransactionType type1 = new TransactionType();
		TransactionType type2 = new TransactionType();
		if(typeRepository.findByName("Buy")==null) {
			type1.setName("Buy");
			typeRepository.save(type1);
		}
		if(typeRepository.findByName("Sell")==null) {
			type2.setName("Sell");
			typeRepository.save(type2);
		}
		System.out.println(typeRepository.findByName("Sell"));
		return "/transaction";
	}
	
	@Transactional
	@RequestMapping(value = "/transaction", method = RequestMethod.POST)
	
	public String processForm(@Valid @ModelAttribute Transaction transaction, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "/transaction";
		}
		model.addAttribute("change", transaction.change());
		transaction.setDate();
		transaction.setIncome();
		//System.out.println("----------------------------"+transactionRepository.totalIncome(dat1, dat2) + "------------");
		if(transaction.getType().getName().equals("Sell")) {
			if(transaction.getCurr().getAmount()-transaction.getAmountOfCur()>0) {
				currencyRepository.setCurrAmount(transaction.getCurr().getAmount()-transaction.getAmountOfCur(), transaction.getCurr().getName());
				currencyRepository.setCurrAmount(currencyRepository.findByName("Zlotowki").getAmount()+transaction.getAmountOfCur()*transaction.getRate(), "Zlotowki");

			}else return "/notEnough";
		}else {
			if(currencyRepository.findByName("Zlotowki").getAmount()-transaction.getAmountOfCur()*transaction.getRate()>0) {
				currencyRepository.setCurrAmount(transaction.getCurr().getAmount()+transaction.getAmountOfCur(), transaction.getCurr().getName());
				currencyRepository.setCurrAmount(currencyRepository.findByName("Zlotowki").getAmount()-transaction.getAmountOfCur()*transaction.getRate(), "Zlotowki");

			}else {
				return "/notEnough";
			}
		}
		if(transaction.getType().getName().equals("Buy")) {
			currencyRepository.setCurrAvgRate((transaction.getCurr().getAmount()*transaction.getCurr().getAvgRate()+transaction.getAmountOfCur()*transaction.getRate())/(transaction.getCurr().getAmount()+transaction.getAmountOfCur()),transaction.getCurr().getName());
		}
		transactionRepository.save(transaction);
		return "change";
	}
	
	
	
	@GetMapping("/history")
	public String hist(Model model){
		return "/history";
	}
	
	
	
	@RequestMapping(value = "/history", method = RequestMethod.POST)
	public String history(@RequestParam String date1, @RequestParam String date2, Model model) throws ParseException{
		List<Transaction> list;
		DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
		Date dat1 = (Date) parser.parse(date1);
		Date dat2 = (Date) parser.parse(date2);
		list = transactionRepository.findByDateBetween(dat1, dat2);
		model.addAttribute("list",list);
		model.addAttribute("total",transactionRepository.totalIncome(dat1, dat2) );
		System.out.println(list);
		return "/history2";
}
	

	@GetMapping("/addPLN")
	public String hista(Model model){
		return "/addPLN";
	}
	
	@Transactional
	@RequestMapping(value = "/addPLN", method = RequestMethod.POST)
	public String addPLN(@RequestParam String amm, Model model) throws ParseException{
		double add = Double.parseDouble(amm);
		currencyRepository.setCurrAmount(currencyRepository.findByName("Zlotowki").getAmount()+add, "Zlotowki");

		return "/home";
	}
	
	
	@GetMapping("/")
	public String home(){
		return "/home";
	}
	
	@GetMapping("/dailySummary")
	public String daily(Model model) throws ParseException{
		List<Transaction> list;
		Date dat2 = new Date();
		 Calendar cal = Calendar.getInstance();
		    cal.setTime(dat2);
		    cal.set(Calendar.HOUR_OF_DAY, 0);
		    cal.set(Calendar.MINUTE, 0);
		    cal.set(Calendar.SECOND, 0);
		    cal.set(Calendar.MILLISECOND, 0);
		Date dat1 = cal.getTime();
		list = transactionRepository.findByDateBetween(dat1, dat2);
		model.addAttribute("list",list);
		model.addAttribute("total",transactionRepository.totalIncome(dat1, dat2) );
		model.addAttribute("curr", currencyRepository.findCurrency("PLN"));
		System.out.println(list);
		return "/dailySummary";
	}
	
	@RequestMapping(value = "/dailySummary", method = RequestMethod.POST)
	public String history(@RequestParam String name,Model model) throws ParseException{
		List<Transaction> list;
		Date dat2 = new Date();
		 Calendar cal = Calendar.getInstance();
		    cal.setTime(dat2);
		    cal.set(Calendar.HOUR_OF_DAY, 0);
		    cal.set(Calendar.MINUTE, 0);
		    cal.set(Calendar.SECOND, 0);
		    cal.set(Calendar.MILLISECOND, 0);
		Date dat1 = cal.getTime();
		list = transactionRepository.findByDateBetweenIn(dat1, dat2,name);
		model.addAttribute("list",list);
		model.addAttribute("total",transactionRepository.totalIncomeIn(dat1, dat2, name) );
		model.addAttribute("curr", currencyRepository.findCurrency("PLN"));
		System.out.println(list);
		return "/dailySummary2";
	}
	
	@RequestMapping(value = "/dailySummary2", method = RequestMethod.POST)
	public String history2(@RequestParam String name,Model model) throws ParseException{
		List<Transaction> list;
		Date dat2 = new Date();
		 Calendar cal = Calendar.getInstance();
		    cal.setTime(dat2);
		    cal.set(Calendar.HOUR_OF_DAY, 0);
		    cal.set(Calendar.MINUTE, 0);
		    cal.set(Calendar.SECOND, 0);
		    cal.set(Calendar.MILLISECOND, 0);
		Date dat1 = cal.getTime();
		list = transactionRepository.findByDateBetweenIn(dat1, dat2,name);
		model.addAttribute("list",list);
		model.addAttribute("total",transactionRepository.totalIncomeIn(dat1, dat2, name) );
		model.addAttribute("curr", currencyRepository.findAll());
		System.out.println(list);
		return "/dailySummary2";
	}
	
	

	@GetMapping("/delete")
	public String hist2(Model model){
		return "/delete";
	}
	
	@Transactional
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String history22(@RequestParam String date1, Model model) throws ParseException{
		Transaction trans =transactionRepository.findOne(Long.parseLong(date1));  
		currencyRepository.setCurrAvgRate((trans.getCurr().getAmount()*trans.getCurr().getAvgRate()-trans.getAmountOfCur()*trans.getRate())/(trans.getCurr().getAmount()-trans.getAmountOfCur()),trans.getCurr().getName());
		transactionRepository.delete(trans);
		return "/home";
	}
	
}














