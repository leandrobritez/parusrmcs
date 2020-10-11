package py.una.pol.par.parusrmcs.repository;

import java.util.Collection;
import py.una.pol.par.commons.repository.Repository;

/**
 *
 * @author Mauricio Machuca
 * @param <User>
 * @param <Integer>
 */
public interface UserRepository<User, Integer> extends Repository<User, Integer> {

    /**
     *
     * @param name
     * @param lastname
     * @return
     */
    boolean containsNameLastname(String name, String lastname);

    /**
     *
     * @param name
     * @param lastname
     * @return
     * @throws Exception
     */
    public Collection<User> findByNameLastname(String name, String lastname);
    
    public boolean containsLoginPasswd(String loginName, String passwd);
    
}
