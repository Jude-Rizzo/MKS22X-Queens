public class QueenBoard{
  private int[][] data;
  public QueenBoard(int size){
    clear(data);
  }

  public static void clear(int[][] l){
    for(int i = 0; i < l.length; i++){
      for(int j = 0; j < l[0].length;j++){
        l[i][j] = 0;
      }
    }
  }

  private boolean addQueen(int r, int c){
    if(data.length == 0) return false;
    if(data[r][c] != 0) return false;
    for(int i = c; i < data[0].length; i++){
      data[r][i] ++;
      //going diagonally up
      if(r+(i - c)< data.length) data[r + (i - c)][c]+= 1;
      //going diagonally down
      if(r-(i-c) >=0) data[r-(i-c)][c] += 1;
        }

    data[r][c] = -1;
    return true;
  }

  private boolean removeQueen(int r, int c){
    if(data.length == 0) return false;
    if(data[r][c] != -1) return false;
    for(int i = c; i < data[0].length; i++){
      data[r][i] --;
      //going diagonally up
      if(r+(i - c)< data.length) data[r + (i - c)][c]-= 1;
      //going diagonally down
      if(r-(i-c) >=0) data[r-(i-c)][c] -= 1;
        }
        //fix!
    data[r][c] = 0;
    return true;
  }

  public boolean solve(){
    return solveH(0,0,data.length);
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
    for(int i = 0; i < data.length; i++){
      ans +="/n";
      for(int j = 0; j < data.length; j++){
        ans += data[i][j];
      }
    }
    return ans;
  }



}
