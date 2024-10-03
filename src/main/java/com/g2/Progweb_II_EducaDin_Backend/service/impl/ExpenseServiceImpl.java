package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import com.g2.Progweb_II_EducaDin_Backend.model.Expense;
import com.g2.Progweb_II_EducaDin_Backend.repository.ExpenseRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl extends GenericCrudService<Expense, Long, ExpenseRepository> implements ExpenseService
    {
        @Override
        protected void validateBusinessToList(List<Expense> expenses) {

        }

        @Override
        protected void prepareToCreate(Expense newModel) {

        }

        @Override
        protected void validateBusinessLogicToCreate(Expense newModel) {

        }

        @Override
        protected void prepareToUpdate(Expense newModel, Expense model) {

        }

        @Override
        protected void validateBusinessLogicToUpdate(Expense model) {

        }

        @Override
        protected void validateBusinessLogicToDelete(Expense model) {

        }

        @Override
        protected void validateBusinessLogic(Expense data) {

        }

        @Override
        public List<Expense> listAll() {
            return List.of();
        }

        @Override
        public Expense deleteById(Long id) {
            return null;
        }
    }
