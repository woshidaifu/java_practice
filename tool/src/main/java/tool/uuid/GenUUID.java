package tool.uuid;
import java.util.UUID;

public class GenUUID {
    public String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
