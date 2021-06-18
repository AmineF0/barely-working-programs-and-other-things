/*
THIS PROGRAM IS A CONVERTER FROM DECIMAL TO BCD() AND FROM BCD TO DEC
IN THE BEGINNING THE USER IS ASKED TO CHOOSE THE CONVERSION HE WANTS
HE WILL ASKED AFTER TO TYPE IS INPUT
THEN THE PROGRAM WILL CHECK THE VALIDITY OF THE INPUT
FINALLY IT WILL PROCESS IT AND PRINT THE RESULT
*/

#include <stdio.h>
#include <string.h>

char DB[10][5];//storing the BCD equivalent for each decimal digit
char* ans;

void initDB(){//fill DB with the appropriate data (decimal -> BCD)
    for(int n=0; n<10; n++){
        int num = n; char str[4];
        for(int i=0; i<4; i++){
            char dig = num%2 + '0';
            num = num/2;
            str[3-i] = dig;
        }
        strcpy(DB[n],str);
        DB[n][5] = '\0';
    }
}

char* BCDtoDEC(char* BCD){//make the conversion from BCD to decimal and return the result as a string
    char dec[strlen(BCD)/4+1];
    for(int i=0;i<strlen(BCD);i++){
        if(i%4==3){
            int num =(int)(BCD[i-3]-'0')*8+(int)(BCD[i-2]-'0')*4+(int)(BCD[i-1]-'0')*2+(int)(BCD[i]-'0')*1;
            dec[i/4]=num+'0';
        }
    }
    dec[strlen(BCD)/4]='\0';
    ans = dec;
    return ans;
}

char* DECtoBCD(char* DEC){//make the conversion from decimal to BCD and return the result as a string
    char bcd[strlen(DEC)][4];
    for(int i=0;i<strlen(DEC);i++){
        strcpy(bcd[i],DB[(int)(DEC[i]-'0')]);
    }
    ans = bcd[0];
    return ans;
}

//check if the input is a valid BCD by checking : 
//  1-all digits are binary  
//  2-each 4 digits are not equivalent to a number bigger than 9
int isNotBCD(char* str){
    for(int i=0; i<strlen(str);i++){
        if(str[i]!='0' && str[i]!='1') return 1;//invalid (true)
        if(i%4==3 && str[i-3]=='1' && (str[i-2]=='1' || str[i-1]=='1') ) return 1;//not a BCD (true)
    }
    return 0;//valid (false)
}

//check if the input is a valid BCD by checking : 
//  1-all digits are  decimal 
int isNotDEC(char* str){
    for(int i=0; i<strlen(str);i++){
        if(str[i]<'0' || str[i]>'9') return 1;//invalid (true)
    }
    return 0;//valid (false)
}
 
void main(){
    
    printf("THIS PROGRAM CODE AND DECODE INFORMATION\n\n");//INTRO
    
    initDB();//initialize DB
    
    int trans=-1;
    while(trans==-1){//keep repeating until choosing a valid option
        printf("YOUR TRANSCODING (enter the number): 0- DEC->BCD \t 1- BCD->DEC\nTYPE :");
        scanf("%d",&trans);
        switch(trans){
            case 0: trans=0; break;
            case 1: trans=1; break;
            default: printf("INVALID INPUT\nYOUR INPUT TYPE (enter the number): 0-DEC\t1-BIN\t2-BCD\nTYPE:"); break;
        }
    }

    char* input; int first=0;
    printf("YOUR INPUT : ");
    do{//keep repeating until giving a valid input
        if(first) printf("INVALID INPUT\n\nYOUR NEW INPUT : ");
        first=1;
        scanf("%s",input);
        //N.B THE PROGRAM CONSIDER THE BCD WITH NUMBER OF DIGITS UNDIVISIBLE BY 4 NVALID
    }while (((strlen(input)%4!=0 || isNotBCD(input) ) && trans==1) || (trans==0 && isNotDEC(input)));

    if(trans){//make proper conversion and print the result
        printf("DEC : %s\n", BCDtoDEC(input));
    }
    else{
        printf("BCD : %s\n", DECtoBCD(input));
    }

}
