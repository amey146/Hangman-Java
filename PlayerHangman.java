import java.io.*;
import java.net.*;

//hey this is hangman, and iam omkar.

public class PlayerHangman{
    public static DatagramSocket ds;
    public static int attempt = 7; 
    public static String gcs, teststr;
    public static int clientport = 789, serverport = 790;

	    public static void main(String args[]) throws Exception {
        PlayerHangman u = new PlayerHangman();
        byte buffer[] = new byte[10000];
        byte bufferS[] = new byte[5000];
        ds = new DatagramSocket(serverport);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        InetAddress ia = InetAddress.getByName("localhost");
        while (true) {
            DatagramPacket p = new DatagramPacket(buffer, buffer.length);
            ds.receive(p);
			System.out.println("Server started game");
            String combi = new String(p.getData(), 0, p.getLength());
            String[] arr = combi.split(":");
            String secretWord = arr[0].toString();
            String hint = arr[1].toString();
            System.out.println("Secret Word Length: " + secretWord.length());  
			System.out.println("Your Hint is : "+hint);
            StringBuilder b = new StringBuilder(secretWord.length()); 
            for (int i = 0; i < secretWord.length(); i++) 
                b.append("*"); 
            char[] secrectStrCharArr = secretWord.toCharArray(); 
            int charCnt = secretWord.length(); 
            while (attempt > 0 && charCnt >= 0) 
			{ 
                System.out.println("Secrect Word :" + b.toString()); 
                System.out.println("Attempts left :" + attempt); 
                System.out.println("Guess a letter :"); 
                char guessChar = br.readLine().toCharArray()[0]; 
                for (int i = 0; i < secrectStrCharArr.length; i++) 
				{ 
                    if (guessChar == secrectStrCharArr[i]) 
					{ 
                        b.setCharAt(i, guessChar); 
                        teststr = b.toString(); 
                        if(teststr.equals(secretWord))
						{ 
							String game = "You Win!!! Wrong guessed letters: ";
                            System.out.println(game+ (7-attempt)); 
							game = "Player Won";
							bufferS = game.getBytes();
							ds.send(new DatagramPacket(bufferS, game.length(), ia, clientport));
							System.exit(0);
                        } 
                    } 
                }  
                gcs = String.valueOf(guessChar); 
                if(!secretWord.contains(gcs))
				{ 
                    attempt--;
					System.out.println("Wrong guess, try again");
                    u.hangmanImage();
                    if(attempt==0) {
						String game = "You Lose! The secretWord was ";
                        System.out.println(game+ secretWord);
						game = "Player Lost";
						bufferS = game.getBytes();
						ds.send(new DatagramPacket(bufferS, game.length(), ia, clientport));
						System.exit(0);
                    }
                } 
            }
        }
    }
    public void hangmanImage() {
		if (attempt == 6) {
			
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("  _|_");
			System.out.println();
		}
		if (attempt == 5) {
			
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("  _|_");
		}
		if (attempt == 4) {
			
			System.out.println("   ____");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   | ");
			System.out.println("  _|_");
		}
		if (attempt == 3) {
			
			System.out.println("   ___________");
			System.out.println("   |        _|_");
			System.out.println("   |       /   \\");
			System.out.println("   |      |     |");
			System.out.println("   |       \\_ _/");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("  _|_");
		}
		if (attempt == 2) {
			
			System.out.println("   ___________");
			System.out.println("   |        _|_");
			System.out.println("   |       /   \\");
			System.out.println("   |      |     |");
			System.out.println("   |       \\_ _/");
			System.out.println("   |         |");
			System.out.println("   |         |");
			System.out.println("   |");
			System.out.println("  _|_");
		}
		if (attempt == 1) {
			
			System.out.println("   ___________");
			System.out.println("   |        _|_");
			System.out.println("   |       /   \\");
			System.out.println("   |      |     |");
			System.out.println("   |       \\_ _/");
			System.out.println("   |         |");
			System.out.println("   |         |");
			System.out.println("   |        / \\ ");
			System.out.println("  _|_      /   \\");
		}
		if (attempt == 0) {
			System.out.println("GAME OVER!");
			System.out.println("   ___________");
			System.out.println("   |        _|_");
			System.out.println("   |       /   \\");
			System.out.println("   |      |     |");
			System.out.println("   |       \\_ _/");
			System.out.println("   |        /|\\");
			System.out.println("   |       / | \\");
			System.out.println("   |        / \\ ");
			System.out.println("  _|_      /   \\");
		}
}
}