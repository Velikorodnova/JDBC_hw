import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CityDAOImpl implements CityDAO {


    @Override
    public void createCity(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            session.save(city);
            transaction.commit();
        }
    }

    @Override
    public City getCityById(int cityId) {
        return HibernateSessionFactoryUtil.getSessionFactory().
                openSession().get(City.class, cityId);
    }

    @Override
    public List<City> getAllCity() {
        List<City> cityList = (List<City>) HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().createQuery
                        ("From City").list();
        return cityList;
    }

    @Override
    public void correctiveCityById(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(city);
            transaction.commit();
        }
    }

    @Override
    public void deleteCityById(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(city);
            transaction.commit();
        }
    }
}
