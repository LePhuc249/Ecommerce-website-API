package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.entity.Role;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void getRole() {
        Optional<Role> result = roleRepository.findById(Long.valueOf("1"));
        Assert.notNull(result);
    }

    @Test
    public void getListRole() {
        List<Role> list = roleRepository.findAll();
        Assert.notNull(list);
    }

    @Test
    public void testAddRole() {
        int num = 0;
        num = roleRepository.addNewAccountRole(Long.valueOf("1"), Long.valueOf("3"));
        boolean result = false;
        if (num > 0) {
            result = true;
        }
        Assert.isTrue(result);
    }

    @Test
    public void testDeleteRole() {
        int num = 0;
        num = roleRepository.deleteAccountRole(Long.valueOf("1"), Long.valueOf("3"));
        boolean result = false;
        if (num > 0) {
            result = true;
        }
        Assert.isTrue(result);
    }
}
