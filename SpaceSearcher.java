//…Ÿ¡ÕÕ«” Õ… œÀ«” 3100127

import java.util.ArrayList;
import java.util.Collections;


public class SpaceSearcher {
	
	private ArrayList<State> moves;
	private ArrayList<State> states;
	private ArrayList<State> statesChilds;

	//kenos kataskevasths apla arxikopoiei tis metavlhtes ws null
	SpaceSearcher()
	{
		this.moves = null;
		this.states = null;
		this.statesChilds=null;
	}
	
	// kataskevasths me orisma ena state to opoio kathorizei apo pou ksekiname na psaxnoume
	SpaceSearcher(State initialState){
		this.moves = new ArrayList<State>();
		this.moves.addAll(initialState.getChildren(1));
	}
	
	/*ulopoihsh t minimax. koitame ta paidia t arxikhs katastashs kai an kapoio kerdizei to epistrefoume.
	 * an dn kerdizei upologizoume ta scores gia kathe paidi sunarthsh tou vathous sto opoio sunantame teliki katastash
	 */
	public int MinMax(){
		//h metavliti depth mas voithaei na kseroume to vathos t dentrou wste na valoume ta katallhla varh kata ton upologismo tou score
		Double depth;
		//koitame gia kathe pithani amesh kinisi an einai teliki kai an dn einai upologizoume to score ths
		for(int i = 0; i < moves.size(); i++){
			if(moves.get(i).isTerminal()==2){
				return moves.get(i).getlastcol();
			}
			this.states = new ArrayList<State>();
			this.states.add(moves.get(i));
			depth = (double) 1;
			//edw ksekinaei o upologismos t score
			while(this.states.size() > 0){
				this.statesChilds = new ArrayList<State>();
				//mesw ths for kseroume oti allazoume epipedo sto dentro prwtou proxwrhsoume thn anazhthsh mas
				for(int j = 0; j < states.size(); j++){
					State currentState = this.states.get(j);
					if(currentState.isTerminal()==1){
						moves.get(i).setScore(-1/depth);
						break;
					}
					else if(currentState.isTerminal()==2){
						moves.get(i).setScore(1/depth);
						break;
					}
					else if(currentState.isTerminal()==0){
						if((depth+1)%2==0)this.statesChilds.addAll(currentState.getChildren(0));
						//h statesChilds mas vohthaei sto na boresoume na uplogisoume to depth
						else this.statesChilds.addAll(currentState.getChildren(1));
					}
				}
				this.states.clear();
				this.states.addAll(statesChilds);
				this.statesChilds.clear();
				depth++;
			}	
		}
		Collections.sort(this.moves);
		//epistrefoume thn teleutaia kinisi kathws exei to upsilotero score 
		return moves.get(moves.size()-1).getlastcol();
	}

}
