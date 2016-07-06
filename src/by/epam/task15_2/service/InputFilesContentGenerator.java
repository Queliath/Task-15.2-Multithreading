package by.epam.task15_2.service;

import by.epam.task15_2.dao.InputFileDAO;
import by.epam.task15_2.dao.exception.DAOException;
import by.epam.task15_2.service.exception.ServiceException;

/**
 * Created by Владислав on 05.07.2016.
 */
public class InputFilesContentGenerator {
    public void generateContentForFile(int fileNumber) throws ServiceException{
        InputFileDAO inputFileDAO = new InputFileDAO(fileNumber);

        inputFileDAO.setActionId(generateActionId());
        inputFileDAO.setNumberA(generateNumber());
        inputFileDAO.setNumberB(generateNumber());

        try {
            inputFileDAO.writeToFile();
        } catch (DAOException e) {
            throw new ServiceException("Exception while generating content for an input file", e);
        }
    }

    private int generateActionId(){
        return (int) Math.ceil(Math.random() * 3);
    }

    private double generateNumber(){
        return Math.random();
    }
}
