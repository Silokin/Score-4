//…Ÿ¡ÕÕ«” Õ… œÀ«” 3100127

import java.util.ArrayList;

public class State implements Comparable<State> {
	
	private int fill;
	private int term;
	private int lastcol;
	private int lastrow;
	private Double score;
	private int [][] board;
	
	//kataskevasths xwris orisma.Kataseskevazei ena adeio state kai arxikopoiei oles tis metavlhtes mas
	public State()
	{
		this.fill = 0;
		this.term = 0;
		this.lastcol = 0;
		this.lastrow = 0;
		this.score = (double) 0;
		this.board = new int[7][8];
		for(int i = 0 ; i<7 ; i++){
			for(int j = 0 ; j<8 ; j++){
				if(j==0 && i>0 ) this.board[i][j]=i;
				else if(i==0 &&j>0) this.board[i][j]=j;
				else this.board[i][j]=0;
			}
		}
	}
	
	//kataskevasths me orisma ena dusdiastato pinaka. To state einai auto pou apeikonizetai ston pinaka.
	public State(int board[][]){
		this.board = new int[7][8];
		for(int i = 0 ; i<7 ; i++){
			for(int j = 0 ; j<8 ; j++){
				if(board[i][j]!=0){
					this.fill++;
					this.board[i][j]=board[i][j];
				}
				else this.board[i][j]=0;	
			}
		}
		this.fill= this.fill-13;
		this.term=0;
		this.lastcol=0;
		this.lastrow = 0;
		this.score = (double) 0;
	}
	
	//sunarthsh p tupwnei to State 
	public void print(){
		for(int i = 0 ; i<7 ; i++){
			if(i>0)System.out.println();
			for(int j = 0 ; j<8 ; j++){
				if(this.board[i][j]==8){
					if(i==this.lastrow && j==this.lastcol)System.out.print("x!");
					else System.out.print("x ");
				}
				else if(this.board[i][j]==9){
					if(i==this.lastrow && j==this.lastcol)System.out.print("+!");
					else System.out.print("+ ");
				}
				else System.out.print(this.board[i][j]+" ");
			}
		}
		System.out.println();
		System.out.println("************************");
	}
	
	//sunarthsh kinhshs. analoga me to p kseroume poios paizei kai analoga me to col kseroume p thelei na paiksei 
	public boolean play(int p,int col){
		if(col<1 || col>7)return false;
		if(this.board[1][col]!=0){
			return false;
		}
		else{
			for(int i=6 ; i>0 ; i--){
				if(this.board[i][col]==0){
					if(p==0){
						this.board[i][col]=8;
						this.fill++;
						if(terminality(i,col)==true)this.term=1;
					}
					else{
						this.board[i][col]=9;
						this.fill++;
						if(terminality(i,col)==true)this.term=2;
					}
					this.lastrow=i;
					this.lastcol=col;
					break;
				}
			}
		return true;
		}
	}
	
	//sunarthsh p kathorizei thn timi term pou mas dixnei an kai poios exei kerdisei se auto to state h an exei gemisei o pinakas dixws nikith
	public boolean terminality(int i,int j){
		int count=0;
		int r=7-j;
		int l=j-1;
		int u=i-1;
		int d=6-i;
		
		//metrame posa idia exoume apo ta aristera
		for(int g=1;g<=l;g++){
			if(this.board[i][j-g]==this.board[i][j])count++;
			else break;
		}
		//metrame posa idia exoume apo ta de3ia
		for(int g=1;g<=r;g++){
			if(this.board[i][j+g]==this.board[i][j])count++;
			else break;
		}
		//koitame an kerdisame
		if(count>=3)return true;
		count=0;
		//metrame posa idia exoume apo ta panw
		for(int g=1;g<=u;g++){
			if(this.board[i-g][j]==this.board[i][j])count++;
			else break;
		}
		//metrame posa idia exoume apo ta katw
		for(int g=1;g<=d;g++){
			if(this.board[i+g][j]==this.board[i][j])count++;
			else break;
		}
		//koitame an kerdisame
		if(count>=3)return true;
		count=0;
		//metrame posa idia exoume diagwnia aristera panw
		for(int g=1;g<=u;g++){
			if(i-g>0 && j-g>0){
				if(this.board[i-g][j-g]==this.board[i][j])count++;
				else break;
			}
		}
		//metrame posa idia exoume diagwnia katw de3ia
		for(int g=1;g<=d;g++){
			if(i+g<7 && j+g<8){
				if(this.board[i+g][j+g]==this.board[i][j])count++;
				else break;
			}
		}
		//koitame an kerdisame
		if(count>=3)return true;
		count=0;
		//metrame posa idia exoume diagwnia panw de3ia
		for(int g=1;g<=u;g++){
			if(i-g>0 && j+g<8){
				if(this.board[i-g][j+g]==this.board[i][j])count++;
				else break;
			}
		}
		//metrame posa idia exoume diagwnia katw aristera
		for(int g=1;g<=d;g++){
			if(i+g<7 && j-g>0){
				if(this.board[i+g][j-g]==this.board[i][j])count++;
				else break;
			}
		}
		//koitame an kerdisame
		if(count>=3)return true;
		if(this.fill>=42)this.term=3;
		return false;
	}
	
	//getter gia thn term 
	public int isTerminal(){
		return term;
	}
	
	//getter gia na kseroume se poia kinhsh einai ousiasthka auth th stigmh to state
	public int getlastcol(){
		return lastcol;
	}
	
	//sunarthsh p paragei ta paidia t state. To p prosdiorizei poios paikths kanei kinhsh 
	public ArrayList<State> getChildren(int p)
	{	
		ArrayList<State> children = new ArrayList<State>();
		State child = new State(this.board);
		for(int i=1;i<8;i++){
			if(child.play(p,i))
			{
				children.add(child);
			}
			child = new State(this.board);
		}
		return children;
	}
	
	//setter gia to score
	public void setScore(Double s){
		this.score =this.score + s;
	}
	
	//getter gia to score
	public Double getScore(){
		return this.score;
	}
	
	//overide gia na boroume na sortaroume eukola thn arraylist me gnwmona ta scores
	@Override
	public int compareTo(State s)
	{
		return Double.compare(this.score, s.getScore());
	}
}
