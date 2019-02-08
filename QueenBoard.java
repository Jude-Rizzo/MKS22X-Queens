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

  private void removeQueen(int r, int c){
    if(data.length == 0) return false;
    if(data[r][c] != -1) return false;
    for(int i = c; i < data[0].length; i++){
      data[r][i] --;
      //going diagonally up
      if(r+(i - c)< data.length) data[r + (i - c)][c]-= 1;
      //going diagonally down
      if(r-(i-c) >=0) data[r-(i-c)][c] -= 1;
        }

    data[r][c] = -1;
    return true;
  }



}
