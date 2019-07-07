package pri.ryan.bns;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public class UUIDTest {
    public static void main(String[] args) {
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(LocalDateTime.now());
    }
}
