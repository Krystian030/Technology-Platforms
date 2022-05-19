package Jandy.Krystian.session.result;


import java.util.TreeMap;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Setter
public class ResultWriter {
    private TreeMap<Integer,Boolean> results = new TreeMap<>();

    public synchronized void save(Integer number, Boolean result) {
        results.put(number,result);
    }
}
