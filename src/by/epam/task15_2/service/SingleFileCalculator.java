package by.epam.task15_2.service;

import by.epam.task15_2.dao.InputFileDAO;
import by.epam.task15_2.dao.exception.DAOException;

/**
 * Created by Владислав on 06.07.2016.
 */
public class SingleFileCalculator implements Runnable {
    private Calculator.CalculationResult calculationResult;
    private int fileNumber;

    public SingleFileCalculator(Calculator.CalculationResult calculationResult, int fileNumber) {
        this.calculationResult = calculationResult;
        this.fileNumber = fileNumber;
    }

    @Override
    public void run() {
        try {
            InputFileDAO inputFileDAO = new InputFileDAO(fileNumber);
            inputFileDAO.readFromFile();

            int actionId = inputFileDAO.getActionId();
            double numberA = inputFileDAO.getNumberA();
            double numberB = inputFileDAO.getNumberB();
            double actionCalculationResult = 0.0;

            switch (actionId) {
                case 1:
                    actionCalculationResult = actionCalculationResult + (numberA + numberB);
                    break;
                case 2:
                    actionCalculationResult = actionCalculationResult + (numberA * numberB);
                    break;
                case 3:
                    actionCalculationResult = actionCalculationResult + (Math.pow(numberA, 2.0) + Math.pow(numberB, 2.0));
                    break;
            }

            calculationResult.addToCalculationResult(actionCalculationResult);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
