package character.dto;

public class MageDTO {
    private final String deleteFound = "done";
    private final String deleteNotFound = "not found";
    private final String getNotExist = "not found";

    public String getExist;
    public String messages;

    private final String saveCorrect = "done";
    private final String saveInCorrect = "bad request";


    public void MageDTO(){}

    public void setGetExist(String getExist) {
        this.getExist = getExist;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getDeleteFound() {
        return deleteFound;
    }

    public String getDeleteNotFound() {
        return deleteNotFound;
    }

    public String getGetNotExist() {
        return getNotExist;
    }

    public String getGetExist() {
        return getExist;
    }

    public String getMessages() {
        return messages;
    }

    public String getSaveCorrect() {
        return saveCorrect;
    }

    public String getSaveInCorrect() {
        return saveInCorrect;
    }
}
