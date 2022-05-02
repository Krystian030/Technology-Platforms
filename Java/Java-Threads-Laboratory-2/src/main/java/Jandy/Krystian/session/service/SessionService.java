package Jandy.Krystian.session.service;

import Jandy.Krystian.character.entity.Employee;
import Jandy.Krystian.character.service.EmployeeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Jandy.Krystian.session.quest.QuestBoard;
import Jandy.Krystian.session.quest.QuestGenerator;
import Jandy.Krystian.session.result.ResultWriter;
import Jandy.Krystian.session.view.SessionScanner;
import Jandy.Krystian.session.worker.Boss;
import Jandy.Krystian.session.worker.Worker;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import Jandy.Krystian.thread.Killer;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionService {
    private Integer counts;
    private ArrayList<Thread> threads;
    private QuestBoard questBoard;
    private QuestGenerator questGenerator;
    private List<Worker> workers;
    private ResultWriter resultWriter;
    private Thread scanner;
    Boss boss;

    public SessionService(Integer counts) {
        this.counts = counts;
        this.threads = new ArrayList<>();
        this.resultWriter = new ResultWriter();
    }

    public void startGenericSession() {
        this.questGenerator = new QuestGenerator();
        this.questBoard = new QuestBoard();
        this.boss = Boss.builder()
                .questGenerator(this.questGenerator)
                .questBoard(this.questBoard)
                .build();

        this.workers = new ArrayList<>(2*this.counts);
        for (int i=0; i<this.counts; i++) {
            this.workers.add(Worker.builder()
                    .questBoard(questBoard)
                    .workerName("Worker[" + String.valueOf(i)+"]")
                    .resultWriter(resultWriter)
                    .build());
        }

        this.threads.add(new Thread(this.boss));

        for (Worker w : this.workers) {
            this.threads.add(new Thread(w));
        }

//        this.scanner = new Thread(new SessionScanner(this));
//        scanner.start();
        for(Thread t : this.threads) t.start();

    }
}
