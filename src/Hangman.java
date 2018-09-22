import java.util.Scanner;
import java.util.Random;
import java.lang.String;
import java.io.*; //text file
public class Hangman {
   public static  void main (String []args)throws IOException {
	   System.out.println("Welcome to Game of HANGMAN ! \n");
	   boolean weArePlaying =true;
	   File file = new File("words.txt");
	   while(weArePlaying) {
	       System.out.println("Please choose a category:\r\n" + 
  		            "_Football teams\r\n"  +
  		            "_Books \r\n" + 
  		            "_Programming principles  \r\n" );
	       int rand_int1 =SelectRandomWord();
	       if (rand_int1 == -1) {
	    	   System.out.println("Error  !!! \n ");
	    	   break;
	       }
		   String  randomWord =WordThatYouMustGuesses ( rand_int1 ); 
		   char [] randomWordToGuess= randomWord.toCharArray();
		   int amountOfGuesses =randomWordToGuess.length;
		   char[] playerGuess =new char [amountOfGuesses]; //
		   int k;
		   for(k=0; k < randomWordToGuess.length ;k++) {
			   if(randomWordToGuess[k] == ' ') {
				   playerGuess[k]=' ';
			   }
			   else {
			   playerGuess[k]='-';
			   }
		   }
		   printArray(playerGuess);
		   int tries = 0 ;
		   boolean wordIsGuessed =false;
		   while(!wordIsGuessed && tries != amountOfGuesses) {
			   System.out.println("Current guesses: ");
			   System.out.printf("Attempts left: %d  \n",amountOfGuesses - tries);
			   System.out.printf("Enter a single character : \n >");
			   Scanner scanner =new Scanner ( System.in);
			   char input =scanner.next().charAt(0);
			   tries++;
			   if (input == '-') {
				   weArePlaying= false;
				   wordIsGuessed = true;   
			   }
			   else {
				   char upperCase = Character.toUpperCase(input);
				   for(k=0; k < randomWordToGuess.length ;k++){
					   if(randomWordToGuess[k] ==input || randomWordToGuess[k] ==upperCase  ) {
						 playerGuess[k] = randomWordToGuess[k];
				       }
			       }
				   printArray(playerGuess);
				   if( isTheWordGuessed(playerGuess)) {
						   wordIsGuessed = true;
						   System.out.println("Congratulation you won!");
			       }
			   }
		    }
			if(!wordIsGuessed) {
				System.out.println("You ran out of guesses");
			}
			System.out.println("Do you want to play another game?:  \n ");
			Scanner scanner =new Scanner ( System.in);
			String anotherGame = scanner.nextLine();
			if(anotherGame.equals("no")) {
			         weArePlaying= false;
	        } 
		    else {
				   System.out.printf("Word that you didn'n guess: %s \n " ,randomWord);
			}                                    
			System.out.flush();
       } 
       System.out.println("Game over!!!");
   }
public static String WordThatYouMustGuesses (int rand_int1 ) {
	String strWord="0";
	int i=0;
	File file = new File("words.txt");
	try {
        Scanner sc = new Scanner(file);//za faila
        sc.useDelimiter("\\s");
		while( sc.hasNextLine()) 
		 {
			++i;
			strWord =(String) sc.nextLine();
			if(i == rand_int1  ) {
        		break;
             }
	    }
		sc.close();
	} 
	catch (FileNotFoundException e){
	        e.printStackTrace();
	}
	return String.format("%s", strWord);
}

public static void printArray( char[] array){
   System.out.print("Current word/phrase:   ");
   for(int i =0;i< array.length;i++) {
	   System.out.print(array[i]+" " ); //array[i]+
    }
   System.out.println();     
 }
 public static boolean isTheWordGuessed(char[] array) {
   for(int i =0;i< array.length;i++) {
	   if(array[i]== '-') {
		   return false;
	   }
   }
   return true;
 }
 public static int SelectRandomWord() {
	 File file = new File("words.txt");
	 int rand_int1 =0;
	 int i=0, j=0;
	 try {	
	    	Scanner sc = new Scanner(file);
	        sc.useDelimiter("\\s");   
            Scanner in= new Scanner (System.in);
     	    String str=in.nextLine();
	        while (sc.hasNext()) {
	        	++i;
	        	if (sc.nextLine().equals(str)){
     			     j=i;
	        	     while( sc.hasNextLine()) 
	        		 {
	        			 ++j;
	        			 if (sc.nextLine().charAt(0)== '_') break;
	        	     }
	        	     j-=2;
	        	     if(!sc.hasNextLine()) j++;
		 			 Random rand= new Random();
		        	 rand_int1 = rand.nextInt( (j-i) + 1 )+ i; //ot i do i+j ((max - min) + 1) + min;
		             //System.out.printf("index rand_int1=%d i= %d   j=%d \n ",rand_int1,i,j);
		             break;
	        	}
	        }
	        if(i>j)  rand_int1 = -1;
	        sc.close();
	 }
	 catch (FileNotFoundException e) {
	        e.printStackTrace();
    }
    return rand_int1;
 }
}