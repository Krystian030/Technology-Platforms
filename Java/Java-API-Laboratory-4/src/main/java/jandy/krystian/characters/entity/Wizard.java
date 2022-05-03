package jandy.krystian.characters.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "wizards")
public class Wizard implements Comparable<Wizard> {
    @Id
    private String name;

    private int level;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_name")
    private School school;

    public Wizard(String name, int level, School school) {
        this.name = name;
        this.level = level;
        school.addMageToTower(this);
        this.school = school;
    }

    @Override
    public String toString() {
        return "Wizard {" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", school=" + school.getName() +
                '}';
    }

    @Override
    public int compareTo(Wizard w) {
        int ret = name == null
                ? (w.name == null ? 0 : 1)
                : name.compareTo(w.name);
        return ret;
    }
}
