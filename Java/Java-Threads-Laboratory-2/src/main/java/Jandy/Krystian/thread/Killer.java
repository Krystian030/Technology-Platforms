package Jandy.Krystian.thread;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import lombok.extern.java.Log;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.logging.Level;

@Getter
@Setter
@Log
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Killer implements Runnable {
    @Singular
    private List<Thread> threads;

    @Override
    public void run() {
        for(Thread t: threads) {
            t.interrupt();
        }

        for(Thread t: threads){
            try{
                t.join();
            } catch (InterruptedException ex) {
                log.log(Level.WARNING,ex.getMessage(),ex);
            }
        }
    }
}
