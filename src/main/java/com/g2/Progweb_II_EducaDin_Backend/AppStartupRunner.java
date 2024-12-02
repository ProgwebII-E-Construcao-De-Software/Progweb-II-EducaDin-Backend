package com.g2.Progweb_II_EducaDin_Backend;

import br.ueg.progweb2.arquitetura.service.AbstractAppStartupRunner;
import ch.qos.logback.classic.Logger;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import com.g2.Progweb_II_EducaDin_Backend.model.Expense;
import com.g2.Progweb_II_EducaDin_Backend.model.Income;
import com.g2.Progweb_II_EducaDin_Backend.model.Tip;
import com.g2.Progweb_II_EducaDin_Backend.repository.CategoryRepository;
import com.g2.Progweb_II_EducaDin_Backend.repository.ExpenseRepository;
import com.g2.Progweb_II_EducaDin_Backend.repository.IncomeRepository;
import com.g2.Progweb_II_EducaDin_Backend.repository.TipRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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

	@Autowired
	private TipRepository tipRepository;

	@Override
	public void runInitData() {

		Category category1 = new Category(1L, "categoria 1 income", false);
		category1 = categoryRepository.save(category1);

		Category category2 = new Category(2L, "categoria 1 expense", true);
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

		persistExpense("Aluguel",
				category2,
				1200.0,
				"Pagamento mensal de aluguel",
				LocalDate.now(),
				1);
		persistExpense("Internet",
				category2,
				150.0,
				"Fatura de internet",
				LocalDate.now().plusDays(5),
				2);
		persistExpense("Supermercado",
				category2,
				350.0,
				"Compras mensais de supermercado",
				LocalDate.now().plusDays(3),
				3);
		persistExpense("Combustível",
				category2,
				250.0,
				"Abastecimento do carro",
				LocalDate.now().minusDays(1),
				4);
		persistExpense("Academia",
				category2,
				100.0,
				"Mensalidade da academia",
				LocalDate.now().plusWeeks(1),
				1);

		loadTips(); // Adicionado para carregar as Tips
	}

	private void loadTips() {
		loadIncomeTips();
		loadExpenseTips();
		loadGoalTips();
	}

	private void loadIncomeTips() {
		List<String> incomeTips = Arrays.asList(
				"Invista parte do seu salário todo mês.",
				"Priorize aumentar sua renda antes de aumentar seus gastos.",
				"Considere fazer freelances para aumentar sua receita.",
				"Crie uma reserva de emergência com seu salário.",
				"Evite depender de uma única fonte de renda.",
				"Guarde ao menos 20% do que ganha mensalmente.",
				"Rastreie sua receita para identificar oportunidades de crescimento.",
				"Invista em educação financeira com parte de sua renda.",
				"Automatize investimentos mensais para poupar tempo.",
				"Use bônus ou aumentos para pagar dívidas ou investir.",
				"Monitore consistentemente suas fontes de renda.",
				"Faça planejamento financeiro para grandes receitas.",
				"Tenha metas financeiras claras para sua renda.",
				"Use aplicativos para gerenciar sua receita e investimentos.",
				"Evite gastar imediatamente após receber sua renda."
		);

		saveTipsByType("income", incomeTips);
	}

	private void loadExpenseTips() {
		List<String> expenseTips = Arrays.asList(
				"Evite gastos desnecessários com cartões de crédito.",
				"Tenha um orçamento mensal detalhado para suas despesas.",
				"Economize energia elétrica para reduzir custos.",
				"Corte assinaturas que você não utiliza.",
				"Evite comprar por impulso em promoções.",
				"Monitore pequenos gastos que acumulam ao longo do mês.",
				"Planeje compras maiores com antecedência.",
				"Use cupons e cashback para economizar em compras.",
				"Cozinhe em casa em vez de comer fora frequentemente.",
				"Estabeleça um limite para despesas variáveis.",
				"Tenha um fundo de reserva para emergências.",
				"Evite empréstimos para financiar gastos não essenciais.",
				"Avalie sua fatura mensalmente em busca de ajustes.",
				"Priorize pagar dívidas antes de adquirir novas despesas.",
				"Negocie tarifas e contratos para reduzir custos fixos."
		);

		saveTipsByType("expense", expenseTips);
	}

	private void loadGoalTips() {
		List<String> goalTips = Arrays.asList(
				"Defina metas financeiras específicas e mensuráveis.",
				"Estabeleça prazos realistas para atingir suas metas.",
				"Priorize metas de curto prazo antes das de longo prazo.",
				"Crie um plano de ação detalhado para alcançar seus objetivos.",
				"Revise suas metas financeiras regularmente.",
				"Compartilhe suas metas com alguém para aumentar a responsabilidade.",
				"Divida metas grandes em pequenos passos alcançáveis.",
				"Monitore seu progresso em direção às metas.",
				"Use ferramentas digitais para gerenciar seus objetivos.",
				"Associe suas metas a recompensas para se motivar.",
				"Adapte suas metas às mudanças na sua situação financeira.",
				"Invista em conhecimento para alcançar metas maiores.",
				"Tenha sempre uma meta de emergência no planejamento.",
				"Estabeleça metas para economizar em grandes compras futuras.",
				"Lembre-se: metas claras geram melhores resultados financeiros."
		);

		saveTipsByType("goal", goalTips);
	}

	private void saveTipsByType(String type, List<String> tips) {
		for (String message : tips) {
			Tip tip = Tip.builder()
					.type(type)
					.message(message)
					.build();
			tipRepository.save(tip);
			LOG.info("Saved Tip [{}]: {}", type, tip.getMessage());
		}
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

	private void persistExpense(String name, Category category, double amount, String description, LocalDate incomeDate, int leadTime) {
		Expense expense = Expense.builder()
				.name(name)
				.category(category)
				.amount(amount)
				.description(description)
				.expenseDate(incomeDate)
				.leadTime(leadTime)
				.build();
		LOG.info("New Expense: {}", this.expenseRepository.save(expense).toString());

	}

}