/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bl;

/**
 *
 * @author Denis Imeri
 */
public class SumUpBL {
    private int startNo;
    private int endNo;
    private int sum;
    
    public SumUpBL(int startNo, int endNo){
        this.startNo = startNo;
        this.endNo = endNo;
    }
    
    public void calculate(){
        sum = 0;
        for (int i = startNo; i <=endNo; i++) {
            sum+=i;
        }
    }

    public int getSum() {
        return sum;
    }
    
    
}
