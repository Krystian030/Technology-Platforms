package Jandy.Krystian.session.quest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestGenerator {

    public Quest generate() {
        Random random = new Random();
        return Quest.builder()
                .number(random.nextInt(1000))
                .time(random.nextInt(10)*1000)
                .build();
    }

    public List<Quest> generate(int n) {
        Random random = new Random();
        List<Quest> quests = new ArrayList<>();
        for(int i=0; i<n; i++) {
            quests.add(Quest.builder()
                    .number(random.nextInt(1000))
                    .time(random.nextInt(10) * 1000)
                    .build());
        }
        return quests;
    }
}
