package Jandy.Krystian.session.worker;

import Jandy.Krystian.session.quest.Quest;
import Jandy.Krystian.session.quest.QuestBoard;
import Jandy.Krystian.session.result.ResultWriter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.Getter;

@Log
@Builder
@AllArgsConstructor
@Getter
public class Worker implements Runnable{
    private QuestBoard questBoard;
    private String workerName;
    private ResultWriter resultWriter;
    @Override
    public void run() {
        while(!Thread.interrupted()){
            try{
                boolean isPrimeNumber = true;
                Quest quest = questBoard.take();
//                System.out.println("Worker " + workerName + " get " + String.valueOf(quest.getNumber()) + " to calculate!");

                for(int i=2; i<quest.getNumber();i++) {
                    if(quest.getNumber()%i == 0) {
                        isPrimeNumber = false;
                        break;
                    }
                }
                Thread.sleep(quest.getTime());
//                System.out.println("Worker " + workerName + " has finished calculating, next step is save");
                resultWriter.save(quest.getNumber(),isPrimeNumber);
//                System.out.println("Worker " + workerName + " saved result and finished his work. Now he waiting for new work...");
            } catch(InterruptedException ex){
                break;
            }
        }
    }
}
