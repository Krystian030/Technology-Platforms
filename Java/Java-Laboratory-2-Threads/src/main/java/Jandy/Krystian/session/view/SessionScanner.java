package Jandy.Krystian.session.view;

import Jandy.Krystian.session.quest.Quest;
import Jandy.Krystian.session.service.SessionService;
import Jandy.Krystian.session.worker.Worker;
import Jandy.Krystian.thread.Killer;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;

@Log
@NoArgsConstructor
@Getter
@Setter
public class SessionScanner implements Runnable {
    private SessionService sessionService;
    private SessionView sessionView;
    private Boolean isRun;
    private Scanner scanner;

    public SessionScanner(SessionService sessionService) {
        this.sessionService = sessionService;
        this.sessionView = new SessionView(sessionService);
        this.sessionView.menu();
        this.scanner = new Scanner(System.in);
        this.isRun = true;
    }

    @Override
    public void run() {
        String option;
        while (isRun) {
            option = scanner.nextLine();
            switch (option.toUpperCase()) {
                case "M":
                    sessionView.menu();
                    break;
                case "P":
                    sessionView.printResult();
                    break;
                case "N":
                    System.out.println("Enter number to check: ");
                    Random random = new Random();
                    sessionService.getQuestBoard().put(List.of(Quest.builder()
                            .number(scanner.nextInt())
                            .time(random.nextInt((10) * 1000))
                            .build()));
                    System.out.println("Quest is added!\n");
                    break;
                case "T":
                    sessionService.getThreads().add(new Thread(Worker.builder()
                            .workerName("Worker[" + String.valueOf(sessionService.getThreads().size() - 1) + "]")
                            .questBoard(sessionService.getQuestBoard())
                            .resultWriter(sessionService.getResultWriter())
                            .build()
                    ));
                    sessionService.getThreads().get(sessionService.getThreads().size() - 1).start();

                    System.out.println("Worker is added");
                    break;
                case "Q":
                    try {

                        System.out.println("Session ends");
                        Thread killer = new Thread(Killer.builder().threads(sessionService.getThreads()).build());
                        killer.start();
                        killer.join();
                        this.isRun = false;
                        sessionService.getScanner().interrupt();
                    } catch (InterruptedException ex) {
                        log.log(Level.WARNING, ex.getMessage(), ex);
                    }
                    break;
                default:
                    option = scanner.nextLine();
                    break;
            }
        }
    }
}
