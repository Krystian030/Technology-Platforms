package jandy.krystian.characters.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@EqualsAndHashCode
@Entity
@Table(name = "schools")
public class School {
    @Id
    private String name;

    private int influence;

    @EqualsAndHashCode.Exclude
    @OneToMany(cascade =CascadeType.ALL, mappedBy = "school", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Wizard> wizards;

    public School(String name, int influence) {
        this.name = name;
        this.influence = influence;
//        this.mages = new ArrayList<>();
    }

    public void addMageToTower(Wizard wizard) {
        if (wizards == null) {
            wizards = new ArrayList<>();
        }
        this.wizards.add(wizard);
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", influence=" + influence +
                '}';
    }
}
