package uz.pdp.g9restfulservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;
import uz.pdp.g9restfulservice.entity.Category;
import uz.pdp.g9restfulservice.entity.Role;
import uz.pdp.g9restfulservice.enums.RoleEnum;
import uz.pdp.g9restfulservice.repository.CategoryRepository;



import uz.pdp.g9restfulservice.repository.RoleRepository;



@Component
public class DataLoader implements CommandLineRunner {


    @Value("${spring.sql.init.mode}")
    private String initMode;

    final CategoryRepository categoryRepository;

    final RoleRepository roleRepository;

    public DataLoader(CategoryRepository categoryRepository, RoleRepository roleRepository) {
        this.categoryRepository = categoryRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Data loader is working....");
        if (initMode.equals("always")) {

            Category category1 = Category.builder()
                    .name("Maishiy texnika")
                    .build();
            Category category2 = Category.builder()
                    .name("Telefonlar va gadjetlar")
                    .build();
            Category savedCategory1 = categoryRepository.save(category1);
            Category savedCategory2 = categoryRepository.save(category2);

            Role admin = roleRepository.save(Role.builder()
                    .roleEnum(RoleEnum.ROLE_ADMIN)
                    .build());
            Role user = roleRepository.save(Role.builder()
                    .roleEnum(RoleEnum.ROLE_USER)
                    .build());
            Role superAdmin = roleRepository.save(Role.builder()
                    .roleEnum(RoleEnum.ROLE_SUPER_ADMIN)
                    .build());
            Role roleOther = roleRepository.save(Role.builder()
                    .roleEnum(RoleEnum.ROLE_OTHERS)
                    .build());

        }

    }
}
