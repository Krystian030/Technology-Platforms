package Jandy.Krystian.session.view;

import Jandy.Krystian.session.result.ResultWriter;
import Jandy.Krystian.session.service.SessionService;
import Jandy.Krystian.view.View;

import java.util.HashMap;
import java.util.Map;

public class SessionView implements View {

    private SessionService sessionService;

    public SessionView(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public void menu() {
        System.out.println(" ================ MENU ================ ");
        System.out.println("==> Q - Quit");
        System.out.println("==> N - New task");
        System.out.println("==> T - Add thread");
        System.out.println("==> M - Menu option");
        System.out.println("==> P - Print results");
        System.out.println("==> O - Print all threads");
        System.out.println("================================================");
    }

    public void printResult(){
        System.out.println("| Number | Prime number |");
        for(Map.Entry<Integer,Boolean> entry : sessionService.getResultWriter().getResults().entrySet()) {
            System.out.println("\t" + entry.getKey() + "\t\t   " + entry.getValue());
        }
    }

    @Override
    public void display() {

    }

}
