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
    if(r >= board.length || c >= board[0].length || board[r][c] != 0) return false;
    for(int i = c; i < board[0].length; i++){
      board[r][i] ++;
      if(r + i < board.length){
      board[r+i][c] ++;
    }
    if(r - i >= 0){
      board[r-i][c] ++;
    }
      //going diagonally up
      if (r-i >= 0 && c+i <board.length)board[r-i][i+c]++;
      //going diagonally down
      if (r+i <board.length && c+i <board.length)board[r+i][c+i]++;
        }

    board[r][c] = -1;
    return true;
  }

  private boolean removeQueen(int r, int c){
    if(board.length == 0) return false;
    if(r >= board.length || c >= board[0].length) return false;
    if(board[r][c] != -1) return false;
    for(int i = c; i < board[0].length; i++){
      board[r][i] --;
      //going diagonally up
      if(r+(i - c)< board.length) board[r + (i - c)][c]-= 1;
      //going diagonally down
      if(r-(i-c) >=0) board[r-(i-c)][c] -= 1;
        }
        //fix!
    board[r][c] = 0;
    return true;
  }

  public boolean solve(){
    return solveH(0,0,board.length);
  }


  public boolean solveH(int row,int col, int size){
    if(row >= size) return true;
    //start off by seeing if u can add a queen to the 0,0
    if(addQueen(row, col)){
      //then goes to the next row and adds
     return solveH(row + 1, col, size);
   }else{
      //remove the queen that you just added
      removeQueen(row, col);
      //if every column has been tested go back a row and try to solve it
      if(col >= size) return solveH(row -1, 0, size);
      //otherwise just go down a column and see if that works
      return solveH(row, col + 1, size);
    }
  }

  public String toString(){
  String ans = "";
    for(int i = 0; i < board.length; i++){
      ans +="\n";
      for(int j = 0; j < board.length; j++){
        ans += board[i][j];
      }
    }
    return ans;
  }

  public static void main(String[] args){
    QueenBoard q = new QueenBoard(8);
    System.out.println(q.solve());
    //q.addQueen(0,0);
    //System.out.println(q.toString());
    int[][] b = q.board();
  }
