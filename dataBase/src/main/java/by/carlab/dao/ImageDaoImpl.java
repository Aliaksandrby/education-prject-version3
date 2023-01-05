package by.carlab.dao;

import by.carlab.model.ImageCar;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDaoImpl implements ImageDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void create(ImageCar imageCar) {
        sessionFactory.getCurrentSession().save(imageCar);
    }

    @Override
    public void update(ImageCar imageCar) {
        sessionFactory.getCurrentSession().update(imageCar);
    }

    @Override
    public void delete(ImageCar imageCar) {
        ImageCar loadedImageCar = sessionFactory.getCurrentSession().load(ImageCar.class, imageCar.getId());
        sessionFactory.getCurrentSession().delete(loadedImageCar);
    }
}
