package pe.edu.upeu.apipersonasuniversitarias.services.serviceConfig;

import pe.edu.upeu.apipersonasuniversitarias.services.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface GenericeService<T> {

    List<T> getAll(T t) throws ServiceException;
    Optional<T> findByCode(String codigo) throws ServiceException;
    T save(T t) throws ServiceException;
    T update(int id, T t) throws ServiceException;
    void delete(int id) throws ServiceException;

}
