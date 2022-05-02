package Jandy.Krystian.session.quest;

import java.util.ArrayList;
import java.util.List;

public class QuestBoard {
    private List<Quest> quests = new ArrayList<>();

    public synchronized  Quest take() throws InterruptedException {
        while(quests.isEmpty()){
            wait();
        }
        return quests.remove(0);
    }

    public synchronized void put(List<Quest> quests) {
        this.quests.addAll(quests);
        notifyAll();
    }

}
