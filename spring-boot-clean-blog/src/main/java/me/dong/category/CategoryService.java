package me.dong.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dong.exception.NotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j  //자동으로 logging을 위한 필드인 ‘private static final Logger log’가 추가, Slf4j를 사용하여 출력
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category create(Category category) {
//        category.setRegDate(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.delete(id);
    }

    public void update(Category category) {
        Category oldCategory = categoryRepository.findOne(category.getId());
        if (oldCategory == null) {
            throw new NotFoundException(category.getId() + " not found");
        }
        oldCategory.setName(category.getName());
    }

    @Transactional(readOnly = true)
    @Cacheable("blog.category")  // 캐시에 사용할 key 지정
    public Page<Category> findAll(Pageable pageable) {
        log.info("blog.category cache");
        return categoryRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findOne(Long id) {
        return categoryRepository.findOne(id);
    }
}
