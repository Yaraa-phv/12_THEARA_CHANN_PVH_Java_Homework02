class ErrorMsg {
    String msg[]= {"Output Error", "Input Error", "Dusk Full", "Index out of bound"};

    String getErrorMsg(int i){
        if (i >= 0 & i < msg.length)
            return "Code index ["+i+"] is "+msg[i];
        else
            return "Invalid Error Code";
    }
}

public class ErrMsgDemo{
    public static void main(String[] args) {
        ErrorMsg err = new ErrorMsg();

        System.out.println(err.getErrorMsg(2));
        System.out.println(err.getErrorMsg(10));
    }
}