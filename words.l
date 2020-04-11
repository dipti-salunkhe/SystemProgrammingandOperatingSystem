%option noyywrap
%{
#include <stdio.h>
int wcount=0,lcount=0,ccount=0;
%}
%%
[\n] {lcount++; wcount++;}
[" "] {wcount++;}
[a-zA-Z] {ccount++;}
%%
main()
{
FILE *fp;
fp=fopen("data.txt","r");
if (fp==NULL)
printf("\nError in Opening file\n");
else
yyin=fp;
yylex();
printf("word count :%d \n",wcount);
printf("line count :%d \n",lcount);
printf("character count :%d \n",ccount);
}
