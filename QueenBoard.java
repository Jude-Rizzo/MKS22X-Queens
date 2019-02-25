public class QueenBoard{
  private int[][] board;
  public QueenBoard(int size){
    board = new int[size][size];
    QueenBoard.clear(board);
  }

  public static void clear(int[][] l){
    for(int i = 0; i < l.length; i++){
      for(int j = 0; j < l[0].length;j++){
        l[i][j] = 0;
      }
    }
  }

  private boolean addQueen(int r, int c){
    if(board.length == 0) return false;
    if(r >= board.length || c >= board.length)return false;
    if(board[r][c] != 0) return false;
    for(int i = 0; i < board[0].length; i++){
      board[r][i] ++;
      board[i][c] ++;

      //going diagonally up
      if (r-i >= 0 && c+i <board.length)board[r-i][i+c]++;
      //going diagonally down
      if (r+i <board.length && c+i <board.length)board[r+i][c+i]++;
        }

    board[r][c] = -1;
    return true;
  }

  private boolean removeQueen(int r, int c){
   if (r >= board.length || c >= board.length) return false;
   //check to make sure the loc is a queen
   if (board[r][c] == -1){
     //remove the places that have been marked
     for (int i = 0; i < board.length; i++){
       //vertically
       board[i][c]--;
       //horizontally
       board[r][i]--;
       //diagonally y = x
       if (r-i >= 0 && c+i < board.length) board[r-i][i+c]--;
       //diagonally y = -x
       if (r+i < board.length && c+i < board.length) board[r+i][c+i]--;
     }
     board[r][c] = 0;
     return true;
   }
   return false;
 }

  public boolean solve(){
    clear(board);
    return solveH(0);
  }


  public boolean solveH(int c){
    //will only happen if all queens have been placed
    if (c >= board.length){
      return true;
    }
    for (int i = 0; i < board.length; i++){
      //recursive call if queen can be placed
      if (addQueen(i,c)){
        //then move to next column
        if (solveH(c+1)){
          return true;
        }
        //if cannot add queen to the next column
        else{
          //backtrack
          //remove the queen that was placed
          removeQueen(i,c);
        }
      }
    }
    return false;
  }

  public int countSolutions(){
    return countSolutionsH(0);
  }

  public int countSolutionsH(int c){
    int count = 0 ;
    if(c > board.length-1) return 1;
    for(int i = 0; i < board.length; i++){
      if(addQueen(i, c)){
        count +=countSolutionsH(c+1);
        removeQueen(i,c);
      }
    } return count;
  }

  public String toString(){
  String ans = "";
    for(int i = 0; i < board.length; i++){
      ans +="\n";
      for(int j = 0; j < board.length; j++){
        if (board[i][j] == -1) ans += "Q ";else ans+="_ ";
      }
    }
    return ans;
  }

  public static void runTest(int i){
    QueenBoard b;
    int[]tests =   {1,2,3,4,5,8};
    int[]answers = {1,0,0,2,10,92};
    if(i >= 0 && i < tests.length ){
      int size = tests[i];
      int correct = answers[i];
      b = new QueenBoard(size);
      int ans  = b.countSolutions();

      if(correct==ans){
        System.out.println("PASS board size: "+tests[i]+" "+ans);
      }else{
        System.out.println("FAIL board size: "+tests[i]+" "+ans+" vs "+correct);
      }
    }
  }


  public static void main(String[] args){
    runTest(0);
  }
}
