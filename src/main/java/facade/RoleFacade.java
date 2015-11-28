package facade;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import model.Role;

@ApplicationScoped
public class RoleFacade {
    @Inject
    private EntityManager em;

    private List<Role> allRoles;

    @PostConstruct
    public void initializeAvailableRoles() {
        allRoles = em.createNamedQuery(Role.LIST_AVAILABLE, Role.class).getResultList();
    }
    
    /**
     * Get the Role for USER
     * @return USER Role
     */
    public Role getUserRole() {
        for ( Role role: allRoles ) {
            if ( role.getRole().equals("USER")) return role;
        }
        throw new RuntimeException("Role USER not found"); 
    }

    /**
     * Get the Role for ADMIN
     * @return ADMIN Role
     */
    public Role getAdminRole() {
        for ( Role role: allRoles ) {
            if ( role.getRole().equals("ADMIN")) return role;
        }
        throw new RuntimeException("Role ADMIN not found"); 
    }
}
