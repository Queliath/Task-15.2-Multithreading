package by.epam.task15_2.service;

import by.epam.task15_2.dao.OutputFileDAO;
import by.epam.task15_2.dao.exception.DAOException;
import by.epam.task15_2.service.exception.ServiceException;

import java.util.ArrayList;

/**
 * Created by Владислав on 05.07.2016.
 */
public class Calculator {

    public double getCalculationResult() throws ServiceException{
        CalculationResult calculationResult = new CalculationResult();

        ArrayList<Thread> actionCalculatorThreads = new ArrayList<>(5);
        for(int i = 1; i <= 5; i++){
            Thread actionCalculatorThread = new Thread(new SingleFileCalculator(calculationResult, i));
            actionCalculatorThreads.add(actionCalculatorThread);
        }

        for(Thread thread : actionCalculatorThreads){
            thread.start();
        }

        for(Thread thread: actionCalculatorThreads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new ServiceException("Exception while getting calculation result", e);
            }
        }

        try {
            OutputFileDAO outputFileDAO = new OutputFileDAO();
            outputFileDAO.writeToFile(calculationResult.getCalculationResult());
            return calculationResult.getCalculationResult();
        } catch (DAOException e) {
            throw new ServiceException("Exception while getting calculation result", e);
        }
    }

    public static class CalculationResult{
        private double calculationResult = 0.0;

        public synchronized double getCalculationResult() {
            return calculationResult;
        }

        public synchronized void setCalculationResult(double calculationResult) {
            this.calculationResult = calculationResult;
        }

        public synchronized void addToCalculationResult(double addingNumber) {
            calculationResult = calculationResult + addingNumber;
        }
    }
}
