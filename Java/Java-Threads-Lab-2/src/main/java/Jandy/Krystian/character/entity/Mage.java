package Jandy.Krystian.character.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.Builder;

@Builder
public class Mage implements Comparable<Mage> {

    private String name;
    private int level;
    private double power;
    private Set<Mage> apprentices;

    public Mage(String name, int level, double power, Set<Mage> apprentices) {
        this.name = name;
        this.level = level;
        this.power = power;
        this.apprentices = apprentices;
    }

    public void addApprentices(Mage mage) {
        if(this != mage) apprentices.add(mage);
        else System.out.printf("Nie mozesz sam byc swoim praktykantem!");
    }

    public void removeApprentices(Mage mage) {
        apprentices.remove(mage);
    }

// compareTo
    @Override
    public int compareTo(Mage o) {
        int ret = name == null
                ? (o.name == null ? 0 : 1)
                : name.compareTo(o.name);

        if(ret == 0) ret = level - o.level;

        if(ret == 0) ret = Double.compare(power,o.power);

        return ret;
    }

// HashCode method
    @Override
    public int hashCode() {
        int result = name == null ? 0 : name.hashCode();
        result = 31*result+level;

        // Wrapper na Double do wywołania metody hashCode na klasie Dobule
        result = 67*result+Double.hashCode(power);
        return result;
    }

// equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Mage tmp = (Mage)o;
        return Objects.equals(name,tmp.name) && level == tmp.level && Double.compare(power,tmp.power) == 0 && Objects.equals(apprentices,tmp.apprentices);
    }

// toString method

    @Override
    public String toString(){
        return "Mage{name='" + name + "', level=" + level + ", power=" + power + "}";
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public double getPower() {
        return power;
    }

    public Set<Mage> getApprentices() {
        return apprentices;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public void setApprentices(Set<Mage> apprentices) {
        this.apprentices = apprentices;
    }
}
