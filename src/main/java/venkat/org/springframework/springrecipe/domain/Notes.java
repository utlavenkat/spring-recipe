package venkat.org.springframework.springrecipe.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@RequiredArgsConstructor
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @NonNull
    private String notes;

    @OneToOne
    @NonNull
    private Recipe recipe;

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
