package Jandy.Krystian;

import Jandy.Krystian.session.quest.Quest;
import Jandy.Krystian.session.service.SessionService;
import Jandy.Krystian.session.view.SessionScanner;
import Jandy.Krystian.session.view.SessionView;

import java.util.*;
import java.util.logging.Level;

import Jandy.Krystian.session.worker.Worker;
import Jandy.Krystian.thread.Killer;
import lombok.extern.java.Log;

@Log
public class SessionLaboratory {
    public void createQuest(){

    }
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of threads: ");
        SessionService service = new SessionService(scanner.nextInt());
        boolean isRunning = true;

//        SessionScanner sessionScanner = new SessionScanner(service);


        service.startGenericSession();
        SessionView sessionView = new SessionView(service);
        sessionView.menu();

        scanner.nextLine();
        String option;
        while(isRunning) {
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
                    service.getQuestBoard().put(List.of(Quest.builder()
                            .number(scanner.nextInt())
                            .time(random.nextInt((10) * 1000))
                            .build()));
                    System.out.println("Quest is added!\n");
                    break;
                case "T":
                    Worker newWorker = Worker.builder()
                            .workerName("Worker[" + String.valueOf(service.getThreads().size()-1)+"]")
                            .questBoard(service.getQuestBoard())
                            .resultWriter(service.getResultWriter())
                            .build();
                    service.getWorkers().add(newWorker);
                    service.getThreads().add(new Thread(newWorker));
                    service.getThreads().get(service.getThreads().size()-1).start();

                    System.out.println("Worker is added");
                    break;
                case "O":
                    for(Worker w : service.getWorkers()) {
                        System.out.println(w.getWorkerName());
                    }
                    break;
                case "Q":
                    try {
                        System.out.println("Session ends");
                        Thread killer = new Thread(Killer.builder().threads(service.getThreads()).build());
                        killer.start();
                        killer.join();
                        isRunning = false;
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

