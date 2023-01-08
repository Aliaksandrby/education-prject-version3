package by.carlab.paging;

import by.carlab.dao.CarDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PagingServiceImpl implements PagingService{

    private final int numberOfCarsOnPage = 1;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CarDao carDao;


    @Override
    public List getCarPaging(int currentPage) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Car")
                .setFirstResult(numberOfCarsOnPage*(currentPage-1))
                .setMaxResults(numberOfCarsOnPage)
                .list();
    }

    @Override
    public int getTotalNumbersOfCars() {
        return carDao.readAll().size();
    }

    @Override
    public int getNumberOfPages() {
        return (int)Math.ceil(getTotalNumbersOfCars()*1.0/numberOfCarsOnPage);
    }

    @Override
    public int getStartPage(int currentPage) {
        int startPage;
        if(currentPage < 5) {
            startPage = 1;
        } else {
            if(getNumberOfPages() == currentPage) {
                startPage = currentPage - 4;
            } else {
                startPage = currentPage - 3;
            }
        }
        return startPage;
    }

    @Override
    public int getEndPage(int currentPage) {
        int endPage;
        if(currentPage < 5) {
            endPage = Math.min(getNumberOfPages(), 5);
            if(getNumberOfPages() == 0) {
                endPage = 1;
            }
        } else {
            if(getNumberOfPages() == currentPage) {
                endPage = currentPage;
            } else {
                endPage = currentPage + 1;
            }
        }
        return endPage;
    }
}
