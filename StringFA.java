/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hppc
 */

import java.io.*;
import java.lang.*;
public class StringFA {
 
    
    public static int computestate(char[] pattern, int pl,int state,int a)
    {
        
        if (state < pl && a == pattern[state])
            return state+1;
        int nextstate, i;
        for (nextstate = state; nextstate > 0; nextstate--)
        {
            if (pattern[nextstate - 1] == a)
            {
                for (i = 0; i < nextstate - 1; i++)
                {
                    if (pattern[i] != pattern[state - nextstate + 1 + i])
                        break;
                }
                if (i == nextstate - 1)
                    return nextstate;
            }
        }
        return 0;
    }
    
     public static void computesarray(char[] pattern, int pl, int[][] stt)
    {
        int state, a;
        for (state = 0; state <= pl; ++state)
            for (a = 0; a < 256; ++a)
            {
                stt[state][a] = computestate(pattern, pl, state, a);
            }
    }
   public static void find( char testee[],char pattern[])
            
    {
        int tl=testee.length;
        int pl=pattern.length;
        
        int stt[][]=new int[pl+1][256];
        computesarray(pattern,pl,stt);
        
        
        int i,state=0;
        for(i=0;i<tl;i++)
        {
            state=stt[state][testee[i]];
            if(state==pl)
            {
                System.out.println(pattern);
                System.out.println("The pattern was found at " +(i-pl+2));
                break;
            }
            else
            {
                System.out.println("Did not find pattern");
            }
        }
    }
     
              
            
    public static void main(String args[])throws IOException
    {
        String testee,pattern;
        
     BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
     System.out.println("Enter the string");
     testee=br.readLine();
     System.out.println("Enter the pattern");
     pattern=br.readLine();
     find(testee.toCharArray(),pattern.toCharArray());
    }
}