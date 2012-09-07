/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.addbuttonprogramatically;


import java.util.Scanner;

/**
 *
 * @author Yoda
 */
public class Game 
{
    private Scanner input = new Scanner(System.in);
    int first_move=0;
    int row=0;
    int column = 0;
    int symbol;
    int turn;
    int inf = 99999999;
    int difficulty=0;
    int stepLength = 0;
    int sLength = 0;
    int[] Row = new int[100];
    int[] Column = new int[100];
    int[] flag = new int[100];
    int[][] mat = new int[20][20];
    int[] step = new int[100];
    int[] s = new int[100];
    private int[] ss = new int[100];
    ConnectFourApplication connectFourApplication;
    public Game(int row,int column,int difficulty,int first_move,ConnectFourApplication connectFourApplication)
    {
        this.first_move = first_move;
        this.row = row;
        this.column = column;
        this.difficulty = ++difficulty;
        this.connectFourApplication = connectFourApplication;
        initializeVariables();
        //initiateGame();
    }
    
    private void initiateGame()
    {
        int Human = 0,AI=0,col=0;
        int turn = row*column;
        do
        {
            if(first_move==1)
            {
                col = Human_Turn();
                System.out.println("Human = "+col);
                Show_Board();
                if(Win(flag[col]-1,col))
                {
                    Human = 1;
                    break;
                }

                turn--;
                if(turn==0)
                    break;

                col = AI_Turn();
                System.out.println("AI = "+col);
                if(col==-1)break;

                Show_Board();
                if(Win(flag[col]-1,col))
                {
                    AI = 1;
                    break;
                }
                turn--;
            }
            else
            {
                col = AI_Turn();
                Show_Board();
                if(Win(flag[col]-1,col))
                {
                    AI = 1;
                    break;
                }

                turn--;
                if(turn==0)break;

                Human_Turn();
                Show_Board();
                if(Win(flag[col]-1,col))
                {
                    Human = 1;
                    break;
                }
                turn--;
            }

        }while(turn!=0);
        if(Human==1)System.out.println("YOU WON\n"+"AI Says : I was going Eazy on you ;-)");
        else if(AI==1)System.out.println("AI WON\n"+"AI Says : Well I guess it's time for another Evoulution >:P ");
        else System.out.println("AI Says : Consider yourself lucky >:P ");
    }
    public int Human_Turn()
    {
        int col=0;
        while(true)
        {       
            System.out.println("Your Move : ");
            col = input.nextInt();
                    
            if((col<0 || col>=20)|| (flag[col]>=row))
            {
                System.out.println("Invalid Move");
                continue;
            }
            break;
        }
        symbol = 4;
        mat[flag[col]++][col]=4;
        return col;
    }

    private void initializeVariables() 
    {
        int i,j;
        turn = row*column;
        for(i=0;i<row;i++)
        {
            flag[i]=0;
            for(j=0;j<column;j++)
                mat[i][j]=connectFourApplication.mat[i][j];
        }
    }

    private void Show_Board() 
    {
        int i,j;
        for(i=0;i<column;i++)
            if(i==0)
                System.out.println("      "+i);
            else
                System.out.println("   "+i);

        System.out.println();
        System.out.println();
        System.out.println();

        for(i=row-1;i>=0;i--)
        {
            System.out.println("   "+i);
            for(j=0;j<column;j++)
                System.out.println("   "+(mat[i][j]));
            System.out.println();
            System.out.println();               
        }
        return;
    }

    boolean Win(int x, int y) 
    {
        int cnt,j,i,v=0;
        Boolean fl=false;
        sLength=stepLength=0;
        if(mat[x][y]==9)
            v=1;

        // Right
        cnt=0;
        for(i=y;i<column;i++)
        {
            if(mat[x][y]==mat[x][i])cnt++;
            else break;
        }

        //left

        for(i=y-1;i>=0;i--)
        {
            if(mat[x][y]==mat[x][i])cnt++;
            else break;
        }
        if(cnt>=4)
            fl=true;
        step[stepLength++]=cnt;
        s[sLength]=v;

        //down
        cnt=0;
        for(i=x;i>=0;i--)
        {
            if(mat[x][y]==mat[i][y])cnt++;
            else break;
        }

        //top

        for(i=x+1;i<row;i++)
        {
            if(mat[x][y]==mat[i][y])cnt++;
            else break;
        }
        if(cnt>=4)
            fl=true;
        step[stepLength++]=cnt;
        s[sLength++]=(v);

        //top right
        cnt=0;
        for(i=x,j=y;i<row && j<column;i++,j++)
        {
            if(mat[x][y]==mat[i][j])cnt++;
            else break;
        }

        //down left

        for(i=x-1,j=y-1;i>=0 && j>=0;i--,j--)
        {
            if(mat[x][y]==mat[i][j])cnt++;
            else break;
        }
        if(cnt>=4)
            fl=true;
        step[stepLength++]=cnt;
        s[sLength]=v;

        //top left
        cnt=0;
        for(i=x,j=y;i<row && j>=0;i++,j--)
        {
            if(mat[x][y]==mat[i][j])cnt++;
            else break;
        }

        //down right

        for(i=x-1,j=y+1;i>=0 && j<column;i--,j++)
        {
            if(mat[x][y]==mat[i][j])cnt++;
            else break;
        }
        if(cnt>=4)
            fl=true;
        step[stepLength++]=cnt;
        s[sLength++]=v;

        return fl;
    }

    int AI_Turn() 
    {
        symbol = 9;
        int col = AI_Move();
        //System.out.println("AI MOVED TO :");
        //mat[flag[col]++][col]=9;
        return col;
    }

    private int AI_Move() 
    {        
        int n,col=-1;
        int counter=0;
        int mx = -inf;
        for(int i=0;i<column;i++)
        {
            if(flag[i]>=row)
                continue;

            Row[0]=flag[i];
            Column[0]=i;
            mat[flag[i]][i]=9;
            flag[i]++;

            n = Tree(i,4,1,mx);
        //  cout<<"mx = "<<mx<<" n = "<<n<<endl;
            if(mx<=n)
            {
                if(mx<n)
                   counter = 0; 
                mx=n;
                ss[counter++] = i;
            }

            mat[--flag[i]][i]=0;

        }
        if(counter==1)
            return ss[0];
        return Another_Hurstic(counter);
    }

    private int Tree(int pre,int symbol,int depth,int res) 
    {
        int n,mx;
        if(Win(flag[pre]-1,pre))
        {
            if(symbol==9)
                return -inf;
            return inf;
        }
        if(depth==difficulty)
            return Huristic_Result(symbol==4);

        if(symbol==9)
            mx = -inf;
        else mx = inf;


        for(int i=0;i<column;i++)
        {
            if(flag[i] >= row)continue;

            Row[depth]=flag[i];
            Column[depth]=i;
            mat[flag[i]][i]=symbol;
            flag[i]++;

            if(symbol==9)
            {
                mx = Math.max(Tree(i,4,depth+1,mx),mx);
                if(mx>res)
                {
                    mat[--flag[i]][i]=0;
                    return mx;
                }
            }
            else
            {
                mx = Math.min(mx,Tree(i,9,depth+1,mx));
                if(mx<res)
                {
                    mat[--flag[i]][i]=0;
                    return mx;
                }
            }
            mat[--flag[i]][i]=0;
        }

        return mx;
    }
    // Another_Heruristic method is ambiguous.
    private int Another_Hurstic(int counter) 
    {
        int siz,n,mx,avg,nn,col=0;
        mx = -inf;
        nn= difficulty;
        avg = column/2;
        for(int i=0;i<counter;i++)
        {
            difficulty=1;
            Row[0]=flag[ss[i]];
            Column[0]=ss[i];
            mat[Row[0]][Column[0]]=9;
            flag[ss[i]]++;
            n = Huristic_Result(true);
            //cout<<ss[i]<<" huristic = "<<n<<" col = "<<col<<" mx = "<<mx<<endl;
            if(mx <= n)
            {
                if(mx<n)
                    col = ss[i];
                else if(Math.abs(ss[i]-avg)<Math.abs(avg-col))col=ss[i];
                mx = n;

            }
            mat[Row[0]][Column[0]]=0;
            flag[ss[i]]--;
        }

        difficulty=nn;
        return col;
    }

    private int Huristic_Result(boolean Alpha) 
    {
        int i,siz,mx,mxx,x,y;
        sLength=0;
        stepLength=0;
        for(i=0;i<difficulty;i++)
        {
            x = Row[i];
            y = Column[i];
            if(Win(x,y))
            {
                if(mat[x][y]==9)
                    return inf;
                return -inf;
            }
        }

        siz = stepLength;
        mx=-inf;
        mxx=-inf;

        for(i=0;i<siz;i++)
        {
            if(s[i]==0)
                mx = Math.max(mx,step[i]);

            else  mxx = Math.max(mxx,step[i]);
        }
        if(mx==mxx && Alpha) return -mx;
        else if(mx==mxx)return mx;
        if(mx>mxx)return -mx;
        return mxx;
    }
}
