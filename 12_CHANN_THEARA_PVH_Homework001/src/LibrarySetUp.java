import java.lang.String;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.System.*;

public class LibrarySetUp {
    private String name;
    private String address;
    private String date;

    public LibrarySetUp(String name, String address ){
        this.name=name;
        this.address=address;

        LocalDateTime setupDate = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE MMMM dd yyyy HH:mm:ss");
        this.date = setupDate.format(format);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.matches("^[a-zA-Z\\s]+$"))
            out.println();
        else
            out.println(Main.Red+"Please input only letters!"+Main.Reset);
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.matches("^[a-zA-Z\\s]+$"))
            out.println();
        else
            out.println(Main.Red+"Please input only letters!"+Main.Reset);
        this.address = address;
    }

    public String displayLibraryInfo(){
        return String.format("\n"+Main.LightYellow+name+" "+Main.Reset+" Library is already created in "+Main.LightYellow+" "+address+" "+Main.Reset+" address successfully on "+Main.LightYellow+date+Main.Reset);
    }
}
