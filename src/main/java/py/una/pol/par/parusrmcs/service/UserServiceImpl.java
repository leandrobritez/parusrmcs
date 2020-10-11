package py.una.pol.par.parusrmcs.service;

import java.util.Collection;
import py.una.pol.par.commons.entity.Entity;
import py.una.pol.par.commons.service.BaseService;
import py.una.pol.par.parusrmcs.model.entity.User;
import py.una.pol.par.parusrmcs.repository.UserRepository;


/**
 *
 * @author Mauricio Machuca
 */
public class UserServiceImpl extends BaseService<User, Integer> implements UserService {

    private final UserRepository<User, Integer> userRepository;

    /**
     *
     * @param userRepository
     */
    //@Autowired
    public UserServiceImpl(UserRepository<User, Integer> userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) throws Exception {
        if (userRepository.containsNameLastname(user.getName(), user.getLastname())) {
            throw new Exception(String.format("Ya existe un usuario con el nombre %s y el apellido %s", user.getName(), user.getLastname()));
        }
        
        if (user.getName() == null || "".equals(user.getName())) {
            throw new Exception("El nombre del usuario no puede ser nulo o cadena vacia.");
        }
        
        if (user.getLastname() == null || "".equals(user.getLastname())) {
            throw new Exception("El apellido del usuario no puede ser nulo o cadena vacia.");
        }
        
        super.add(user);
    }

    /**
     *
     * @param name
     * @param lastname
     * @return
     * @throws Exception
     */
    @Override
    public Collection<User> findByNameLastname(String name, String lastname) throws Exception {
        return userRepository.findByNameLastname(name, lastname);
    }

    /**
     *
     * @param user
     * @throws Exception
     */
    @Override
    public void update(User user) throws Exception {
        userRepository.update(user);
    }

    /**
     *
     * @param id
     * @throws Exception
     */
    @Override
    public void delete(Integer id) throws Exception {
        userRepository.remove(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Entity findById(Integer id) throws Exception {
        return userRepository.get(id);
    }

    @Override
    public boolean containsLoginPasswd(String loginName, String passwd) {
        return userRepository.containsLoginPasswd(loginName, passwd);
    }

}
