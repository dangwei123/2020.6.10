给定一个非空字符串, 按照如下方式编码, 使得编码后长度最小, 返回编码后的长度: 
编码规则为: k[encoding_string], 表示重复k次encoding_strng, 
例如'abcdefabcdefabc'可表示为'2[abcdef]abc', 但是'aaa'仅能编码成'aaa', 
因为len('3[a]')>len('aaa').
补充:
1. k为正整数, []内的encoding_string不得含有空格不得为空;
2. []内的encoding_string 本身可以为编码过的字符串, 例如'abcdabcdeabcdabcde' 可以编码为 '2[abcdabcde]'(编码后长度从18减少到12), []内的'abcdabcde'又可以编码为 '2[abcd]e', 最终编码为 '2[2[abcd]e]', 编码后长度为11, 应返回11; 这个编码路径也能是: 'abcdabcdeabcdabcde' -> '2[abcd]e2[abcd]e' -> '2[2[abcd]e]';
2. 输入字符串为全小写英文字母, 长度<=160;
3. 如果编码后长度没有更小, 则保留原有字符串;

import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String str=sc.next();
            System.out.println(getMinLen(str).length());
        }
    }
    
    private static String getMinLen(String str){
        int len=str.length();
        if(len<=4) return str;
        String pre="";
        String cur="";
        String next="";
        
        int cutLen=len/2;
        int bestCount=0;
        int bestLen=len;
        while(cutLen>=1){
            for(int i=0;i<=len-cutLen;i++){
                String s1=str.substring(i,i+cutLen);
                int count=1;
                for(int j=1;i+j*cutLen+cutLen<=len;j++){
                    String s2=str.substring(i+j*cutLen,i+j*cutLen+cutLen);
                    if(s2.equals(s1)){
                        count++;
                    }else{
                        break;
                    }
                }
                
                if(count>1){
                    int newLen=len-(count-1)*cutLen+String.valueOf(count).length()+2;
                    if(newLen<bestLen){
                        bestLen=newLen;
                        bestCount=count;
                        pre=str.substring(0,i);
                        cur=str.substring(i,i+cutLen);
                        next=str.substring(i+count*cutLen);
                    }
                }
            }
            cutLen--;
        }
        if(bestCount==0){
            return str;
        }
        return getMinLen(pre)+String.valueOf(bestCount)+"["+getMinLen(cur)+"]"+getMinLen(next);
    }
}