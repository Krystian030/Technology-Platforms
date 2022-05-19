package Jandy.Krystian.session.worker;

import Jandy.Krystian.session.quest.QuestBoard;
import Jandy.Krystian.session.quest.QuestGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Boss implements Runnable {
    private QuestBoard questBoard;
    private QuestGenerator questGenerator;

    @Override
    public void run() {
        while(!Thread.interrupted()){
            try {
                System.out.println("Boss puts new tasks on the board");
                questBoard.put(questGenerator.generate(5));
                Thread.sleep(10*1000);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
