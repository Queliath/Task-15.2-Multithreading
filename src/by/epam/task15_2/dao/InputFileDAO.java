package by.epam.task15_2.dao;

import by.epam.task15_2.dao.exception.DAOException;
import by.epam.task15_2.start.Main;

import java.io.*;

/**
 * Created by Владислав on 05.07.2016.
 */
public class InputFileDAO {
    private static final String filePrefix = "in";
    private static final String fileExtension = ".txt";
    private static final char directorySeparator = '/';
    private static final char newRowSymbol = '\n';
    private static final char spaceSymbol = ' ';

    private int fileNumber;

    private int actionId;
    private double numberA;
    private double numberB;

    public InputFileDAO(int fileNumber) {
        this.fileNumber = fileNumber;
    }

    public void readFromFile() throws DAOException{
        try (FileReader fileReader = new FileReader(Main.workingDirectoryURI +
                directorySeparator + filePrefix + fileNumber + fileExtension)){
            StringBuilder fileContent = new StringBuilder();

            int symbol = 0;
            while ((symbol = fileReader.read()) != -1){
                fileContent.append((char) symbol);
            }

            String fileContentStr = fileContent.toString();
            String actionIdStr = fileContentStr.substring(0, fileContentStr.indexOf(newRowSymbol));
            String numberAStr = fileContentStr.substring(fileContentStr.indexOf(newRowSymbol) + 1,
                    fileContentStr.indexOf(spaceSymbol));
            String numberBStr = fileContentStr.substring(fileContentStr.indexOf(spaceSymbol));

            actionId = Integer.parseInt(actionIdStr);
            numberA = Double.parseDouble(numberAStr);
            numberB = Double.parseDouble(numberBStr);
        } catch (FileNotFoundException e) {
            throw new DAOException("A file was not found", e);
        } catch (IOException e) {
            throw new DAOException("Exception while reading a file", e);
        }
    }

    public void writeToFile() throws DAOException{
        try (FileWriter fileWriter = new FileWriter(Main.workingDirectoryURI +
                directorySeparator + filePrefix + fileNumber + fileExtension)){
            fileWriter.write(Integer.toString(actionId));
            fileWriter.append(newRowSymbol);
            fileWriter.append(Double.toString(numberA));
            fileWriter.append(spaceSymbol);
            fileWriter.append(Double.toString(numberB));
        } catch (FileNotFoundException e) {
            throw new DAOException("A file was not found", e);
        } catch (IOException e) {
            throw new DAOException("Exception while writing into a file", e);
        }
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public double getNumberA() {
        return numberA;
    }

    public void setNumberA(double numberA) {
        this.numberA = numberA;
    }

    public double getNumberB() {
        return numberB;
    }

    public void setNumberB(double numberB) {
        this.numberB = numberB;
    }
}
