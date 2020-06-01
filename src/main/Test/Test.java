import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


        System.out.println(simpleDateFormat.format(date));
    }

    @org.junit.Test
    public void ran(){
        Random random = new Random();
        int one = random.nextInt(5);
        int two = random.nextInt(5);
        while(one == two ){
            one = random.nextInt(5);
            two = random.nextInt(5);

        }
        System.out.println(one+"\n"+two+"\n");
    }
}
