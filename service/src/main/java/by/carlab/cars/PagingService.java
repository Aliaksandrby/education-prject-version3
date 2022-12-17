package by.carlab.cars;

import java.util.List;

public interface PagingService {
    List getCarPaging(int currentPage);
    int getTotalNumbersOfCars();
    int getNumberOfPages();
    int getStartPage(int currentPage);
    int getEndPage(int currentPage);
}
