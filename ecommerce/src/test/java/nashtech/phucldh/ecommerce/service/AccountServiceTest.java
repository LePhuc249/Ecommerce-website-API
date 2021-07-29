package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.entity.ERole;
import nashtech.phucldh.ecommerce.entity.Role;

import nashtech.phucldh.ecommerce.repository.AccountReponsitory;
import nashtech.phucldh.ecommerce.repository.RoleRepository;

import nashtech.phucldh.ecommerce.service.impl.AccountServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;

import javax.security.auth.login.AccountNotFoundException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@SpringBootTest
public class AccountServiceTest {

    @Mock
    private AccountReponsitory accountReponsitory;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private AccountServiceImpl service;

    @BeforeEach
    public void setUp() {

        Role userRole = new Role(1L, ERole.ROLE_CUSTOMER);
        Role adminRole = new Role(2L, ERole.ROLE_ADMIN);
        Role managerRole = new Role(3L, ERole.ROLE_MANAGER);

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);

        Set<Role> roles2 = new HashSet<>();
        roles2.add(userRole);

        Set<Role> roles3 = new HashSet<>();
        roles3.add(userRole);
        roles3.add(adminRole);

        Mockito.when(roleRepository.findByName(ERole.ROLE_ADMIN)).thenReturn(Optional.of(adminRole));

        Account account = new Account();
        long generatedLong = new Random().nextLong();
        account.setId(generatedLong);
        account.setUserName("MokitoTest");
        account.setFullName("abc");
        account.setEmail("abc@gmail.com");
        account.setRoles(roles);

        Account account2 = new Account();
        long generatedLong2 = new Random().nextLong();
        account2.setId(generatedLong2);
        account2.setFullName("test");
        account2.setEmail("test@gmail.com");
        account2.setRoles(roles);

        Account account3 = new Account();
        account3.setId(Long.valueOf("1000"));
        account3.setFullName("test2");
        account3.setEmail("test2@gmail.com");
        account3.setRoles(roles2);

        when(accountReponsitory.findByUserName(account.getUserName())).thenReturn(Optional.of(account));

        when(accountReponsitory.findByEmail(account2.getEmail())).thenReturn(Optional.of(account2));

        when(accountReponsitory.findById(Long.valueOf("1000"))).thenReturn(Optional.of(account3));

        when(accountReponsitory.save(any(Account.class))).thenAnswer(i -> i.getArguments()[0]);

    }

    @Test
    public void testGetAccountByUsername() throws AccountNotFoundException {
        assertNotNull(service.getAccountByUsername("MokitoTest"));
    }



}
