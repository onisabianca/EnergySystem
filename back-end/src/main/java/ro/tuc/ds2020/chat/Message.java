package ro.tuc.ds2020.chat;

public class Message {
    private String senderName;
    private String receiverName;
    private String message;
    private Status status;

    public Message(){}

    public Message(String s, String r, String mess, Status stat)
    {
        this.senderName=s;
        this.receiverName=r;
        this.message=mess;
        this.status=stat;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
