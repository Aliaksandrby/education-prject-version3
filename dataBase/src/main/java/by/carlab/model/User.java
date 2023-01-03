package by.carlab.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_user")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_order")
    private int isOrder;

    @OneToMany(mappedBy = "user")
    private List<Order> order;

    @Transient
    private String confirmPassword;

    @Transient
    private String message;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Card> cardList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roleSet;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoleSet();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
