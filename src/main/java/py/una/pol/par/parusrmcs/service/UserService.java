package py.una.pol.par.parusrmcs.service;

import java.util.Collection;
import py.una.pol.par.commons.entity.Entity;
import py.una.pol.par.parusrmcs.model.entity.User;

/**
 *
 * @author Sourabh Sharma
 */
public interface UserService {

    /**
     *
     * @param user
     * @throws Exception
     */
    public void add(User user) throws Exception;

    /**
     *
     * @param user
     * @throws Exception
     */
    public void update(User user) throws Exception;

    /**
     *
     * @param id
     * @throws Exception
     */
    public void delete(Integer id) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Entity findById(Integer id) throws Exception;

    /**
     *
     * @param name
     * @param lastname
     * @return
     * @throws Exception
     */
    public Collection<User> findByNameLastname(String name, String lastname) throws Exception;

    
    public boolean containsLoginPasswd(String loginName, String passwd);
}
