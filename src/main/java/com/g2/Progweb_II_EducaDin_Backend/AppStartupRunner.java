package com.g2.Progweb_II_EducaDin_Backend;

import br.ueg.progweb2.arquitetura.service.AbstractAppStartupRunner;
import ch.qos.logback.classic.Logger;
import com.g2.Progweb_II_EducaDin_Backend.enums.Repeatable;
import com.g2.Progweb_II_EducaDin_Backend.model.*;
import com.g2.Progweb_II_EducaDin_Backend.repository.CategoryRepository;
import com.g2.Progweb_II_EducaDin_Backend.repository.ExpenseRepository;
import com.g2.Progweb_II_EducaDin_Backend.repository.IncomeRepository;
import com.g2.Progweb_II_EducaDin_Backend.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

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
    private UserRepository userRepository;


    @Override
    public void runInitData() {

        persistUser(
                "admin",
                "admin@gmail.com",
                "admin");

        User user = new User();

        if (userRepository.findById(1L).isPresent()) {
            user = userRepository.findById(1L).get();

        }

        Category category1 = new Category(1L, "categoria 1 income", false, user);
        category1 = categoryRepository.save(category1);

        Category category2 = new Category(2L, "categoria 1 expense", true, user);
        category2 = categoryRepository.save(category2);

        persistIncome("Salario",
                category1,
                500.0,
                "Esta é descrição",
                LocalDate.now(),
                2,
                Repeatable.MONTHLY, user);
        persistIncome("Freelance pro IFOOD",
                category1,
                500.0,
                "Esta é a descrição tal",
                LocalDate.now(),
                1,
                Repeatable.YEARLY, user);
        persistIncome("Consultoria em TI",
                category2,
                1200.0,
                "Serviço de consultoria mensal", LocalDate.now(),
                5,
                Repeatable.WEEKLY, user);
        persistIncome("Maria",
                category1,
                2500.0,
                "Esta é descrição",
                LocalDate.now(),
                6,
                Repeatable.WEEKLY, user);
        persistIncome("Luciana",
                category1,
                500.0,
                "Esta é a descrição tal",
                LocalDate.now(),
                4,
                Repeatable.DONT_REPEATS, user);
        persistIncome("Consultoria em TI",
                category2, 1200.0,
                "Serviço de consultoria mensal",
                LocalDate.now().plusWeeks(2),
                0,
                Repeatable.DONT_REPEATS, user);

        persistExpense("Aluguel",
                category2,
                1200.0,
                "Pagamento mensal de aluguel",
                LocalDate.now(),
                1, Repeatable.DONT_REPEATS, user);
        persistExpense("Internet",
                category2,
                150.0,
                "Fatura de internet",
                LocalDate.now().plusDays(5),
                2, Repeatable.DONT_REPEATS, user);
        persistExpense("Supermercado",
                category2,
                350.0,
                "Compras mensais de supermercado",
                LocalDate.now().plusDays(3),
                3, Repeatable.DONT_REPEATS, user);
        persistExpense("Combustível",
                category2,
                250.0,
                "Abastecimento do carro",
                LocalDate.now().minusDays(1),
                4, Repeatable.DONT_REPEATS, user);
        persistExpense("Academia",
                category2,
                100.0,
                "Mensalidade da academia",
                LocalDate.now().plusWeeks(1),
                1, Repeatable.DONT_REPEATS, user);

    }


    private void persistUser(
            String login,
            String email,
            String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = User.builder()
                .login(login)
                .loginEnt(new Login())
                .email(email)
                .activeState(true)
                .roles(Arrays.asList(
                        "ROLE_INCOME_REMOVEALL",
                        "ROLE_INCOME_CREATE",
                        "ROLE_INCOME_READ",
                        "ROLE_INCOME_UPDATE",
                        "ROLE_INCOME_DELETE",
                        "ROLE_INCOME_READ_ALL",
                        "ROLE_EXPENSE_REMOVEALL",
                        "ROLE_EXPENSE_CREATE",
                        "ROLE_EXPENSE_READ",
                        "ROLE_EXPENSE_UPDATE",
                        "ROLE_EXPENSE_DELETE",
                        "ROLE_EXPENSE_READ_ALL",
                        "ROLE_GOAL_REMOVEALL",
                        "ROLE_GOAL_CREATE",
                        "ROLE_GOAL_READ",
                        "ROLE_GOAL_UPDATE",
                        "ROLE_GOAL_DELETE",
                        "ROLE_GOAL_READ_ALL",
                        "ROLE_CATEGORY_REMOVEALL",
                        "ROLE_CATEGORY_CREATE",
                        "ROLE_CATEGORY_READ",
                        "ROLE_CATEGORY_UPDATE",
                        "ROLE_CATEGORY_DELETE",
                        "ROLE_CATEGORY_READ_ALL",
                        "ROLE_NOTIFICATION_READ_ALL",
                        "ROLE_NOTIFICATION_CREATE",
                        "ROLE_NOTIFICATION_READ",
                        "ROLE_NOTIFICATION_UPDATE",
                        "ROLE_NOTIFICATION_DELETE",
                        "ROLE_NOTIFICATION_READ_ALL",
                        "ROLE_NOTIFICATIONPREFERENCE_READ_ALL",
                        "ROLE_NOTIFICATIONPREFERENCE_CREATE",
                        "ROLE_NOTIFICATIONPREFERENCE_READ",
                        "ROLE_NOTIFICATIONPREFERENCE_UPDATE",
                        "ROLE_NOTIFICATIONPREFERENCE_DELETE",
                        "ROLE_NOTIFICATIONPREFERENCE_READ_ALL"))
                .build();

        user.getLoginEnt().setUser(user);
        user.getLoginEnt().setPassword(bCryptPasswordEncoder.encode(password));

        LOG.info("New user: {}", this.userRepository.save(user).toString());

    }

    private void persistIncome(
            String name,
            Category category,
            double amount,
            String description,
            LocalDate incomeDate,
            int leadTime,
            Repeatable repeatable,
            User user) {
        Income income = Income.builder()
                .name(name)
                .category(category)
                .amount(amount)
                .description(description)
                .incomeDate(incomeDate)
                .leadTime(leadTime)
                .repeatable(repeatable)
                .user(user)
                .build();
        LOG.info("New income: {}", this.incomeRepository.save(income).toString());

    }

    private void persistExpense(String name,
                                Category category,
                                double amount,
                                String description,
                                LocalDate incomeDate,
                                int leadTime,
                                Repeatable repeatable,
                                User user) {
        Expense expense = Expense.builder()
                .name(name)
                .category(category)
                .amount(amount)
                .description(description)
                .expenseDate(incomeDate)
                .leadTime(leadTime)
                .repeatable(repeatable)
                .user(user)
                .build();
        LOG.info("New Expense: {}", this.expenseRepository.save(expense).toString());

    }

}
