package uz.pdp.g9restfulservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;
import uz.pdp.g9restfulservice.entity.Category;
import uz.pdp.g9restfulservice.repository.CategoryRepository;



@Component
public class DataLoader implements CommandLineRunner {


    @Value("${spring.sql.init.mode}")
    private String initMode;

    final CategoryRepository categoryRepository;

    public DataLoader(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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
        }

    }
}
