package com.g2.Progweb_II_EducaDin_Backend;

import br.ueg.progweb2.arquitetura.service.AbstractAppStartupRunner;
import ch.qos.logback.classic.Logger;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import com.g2.Progweb_II_EducaDin_Backend.model.Expense;
import com.g2.Progweb_II_EducaDin_Backend.model.Income;
import com.g2.Progweb_II_EducaDin_Backend.repository.CategoryRepository;
import com.g2.Progweb_II_EducaDin_Backend.repository.ExpenseRepository;
import com.g2.Progweb_II_EducaDin_Backend.repository.IncomeRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AppStartupRunner extends AbstractAppStartupRunner {
	private static final Logger LOG =
			(Logger) LoggerFactory.getLogger(AppStartupRunner.class);
	@Autowired
	private IncomeRepository incomeRepository;

	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private CategoryRepository categoryRepository;


	@Override
	public void runInitData() {

	Category category1 = new Category(1L, "categoria 1 income", false);
	category1 = categoryRepository.save(category1);

	Category category2 = new Category(2L,"categoria 1 expense", true);
	category2 = categoryRepository.save(category2);

	persistIncome("Salario",
			category1,
			500.0,
			"Esta é descrição",
			LocalDate.now(),
			2);
	persistIncome("Freelance pro IFOOD",
			category1,
			500.0,
			"Esta é a descrição tal",
			LocalDate.now(),
			1);
	persistIncome("Consultoria em TI",
			category2,
			1200.0,
			"Serviço de consultoria mensal", LocalDate.now(),
			5);
	persistIncome("Maria",
			category1,
			2500.0,
			"Esta é descrição",
			LocalDate.now(),
			6);
	persistIncome("Luciana",
			category1,
			500.0,
			"Esta é a descrição tal",
			LocalDate.now(),
			4);
	persistIncome("Consultoria em TI",
			category2, 1200.0,
			"Serviço de consultoria mensal",
			LocalDate.now().plusWeeks(2),
			0);

}

private void persistIncome(String name, Category category, double amount, String description, LocalDate incomeDate, int leadTime) {
	Income income = Income.builder()
			.name(name)
			.category(category)
			.amount(amount)
			.description(description)
			.incomeDate(incomeDate)
			.leadTime(leadTime)
			.build();
	LOG.info("New income: {}", this.incomeRepository.save(income).toString());

}
}
