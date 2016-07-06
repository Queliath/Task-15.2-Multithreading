package by.epam.task15_2.dao;

import by.epam.task15_2.dao.exception.DAOException;
import by.epam.task15_2.start.Main;

import java.io.*;

/**
 * Created by Владислав on 05.07.2016.
 */
public class OutputFileDAO {
    private static final String fileName = "out.txt";
    private static final char directorySeparator = '/';

    public void writeToFile(double result) throws DAOException{
        try (FileWriter fileWriter = new FileWriter(Main.workingDirectoryURI +
                directorySeparator + fileName)) {
            fileWriter.write(Double.toString(result));
        } catch (FileNotFoundException e) {
            throw new DAOException("A file was not found", e);
        } catch (IOException e) {
            throw new DAOException("Exception while writing into a file", e);
        }
    }
}
