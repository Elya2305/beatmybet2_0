package org.example.beatmybet.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Category> subCategories;

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void addSubCategory(Category category) {
        this.subCategories.add(category);
        category.setCategory(this);
    }

    public void removeSubCategory(Category category) {
        this.subCategories.remove(category);
        category.setCategory(null);
    }
}
