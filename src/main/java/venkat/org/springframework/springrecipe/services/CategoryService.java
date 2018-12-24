package venkat.org.springframework.springrecipe.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import venkat.org.springframework.springrecipe.domain.Category;
import venkat.org.springframework.springrecipe.repositories.CategoryRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public Category getByCategoryName(String categoryName) {
        Category category = null;
        Optional<Category>  savedCategory = categoryRepository.findByCategoryName(categoryName);
        if(savedCategory.isPresent()) {
            category =savedCategory.get();
        }
        return category;
    }
}
