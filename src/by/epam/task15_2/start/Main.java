package by.epam.task15_2.start;

import by.epam.task15_2.service.Calculator;
import by.epam.task15_2.service.InputFilesContentGenerator;
import by.epam.task15_2.service.exception.ServiceException;

/**
 * Created by Владислав on 05.07.2016.
 */
public class Main {
    public static String workingDirectoryURI;

    public static void main(String[] args){
        workingDirectoryURI = args[0];

        try {
            InputFilesContentGenerator inputFilesContentGenerator = new InputFilesContentGenerator();
            for(int i = 1; i <= 5; i++){
                inputFilesContentGenerator.generateContentForFile(i);
            }

            Calculator calculator = new Calculator();
            double calculationResult = calculator.getCalculationResult();
            System.out.println("Резульат калькуляции: " + calculationResult);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
