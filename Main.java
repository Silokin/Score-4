//…Ÿ¡ÕÕ«” Õ… œÀ«” 3100127

import java.util.Scanner;


public class Main {
	
	public static void main(String Args[]){
		int run=1;
		int player=0;
		int cpu=0;
		//oso to epilegei o paikths sunexise na 3ekinas nea paixnidia
		while(run!=2){
			boolean round = false;
			boolean correct;
			State gameState=new State();
			gameState.print();
			int AI;
			//edw o vrogxos einai atermwn kai teleiwnei mono otan vrethei teliki katastasi kai kanei break
			while(round==false){
				correct=false;
				//anagkazoume ton paikth na epilegei mexri na mas dwsei swsth epilogh
				while(correct==false){
					System.out.println("Please give a column number from 1-7. Don't choose a maxed out column.");
					Scanner in = new Scanner(System.in);
					int num = in.nextInt();
					correct=gameState.play(0,num);
				}
				gameState.print();
				//afou paiksei o paikths elegxoume na doume an kerdise h an hrthe isopalia
				if(gameState.isTerminal()==1){
					System.out.println("Congratulations you won!");
					player++;
					break;
				}
				if(gameState.isTerminal()==3){
					System.out.println("DRAW");
					break;
				}
				//seira gia thn AI na paiksei 
				SpaceSearcher SpS = new SpaceSearcher(gameState);
				AI = SpS.MinMax();
				gameState.play(1,AI);
				gameState.print();
				//afou paiksei h AI elegxoume na doume an kerdise h an to match hrthe isopalia
				if(gameState.isTerminal()==2){
					System.out.println("Too bad you lost...");
					cpu++;
					break;
				}
				if(gameState.isTerminal()==3){
					System.out.println("DRAW");
					break;
				}
			}
			//afou vgoume apo ton vrogxo dioti pesame se telikh katastash tupwnoume to score
			System.out.println("Score: Player["+player+"] CPU["+cpu+"]");
			System.out.println();
			correct=false;
			//rwtame ton xrhsth an thelei na ksanapaiksei alliws termatizoume
			while(correct==false){
				System.out.println("Want to play again?");
				System.out.println("1.Yes");
				System.out.println("2.No");
				Scanner in = new Scanner(System.in);
				int num = in.nextInt();
				if(num==1 || num==2){
					correct=true;
					run=num;
				}
			}
		}	
	}
}
