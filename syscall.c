#include<stdio.h>
#include<unistd.h>
#include<stdlib.h>
int main()
{

int pid,status,pid1;
// instead of int we can use
char *argv[] = {NULL};
//array of character
int choice = 0;
int i = 1;
while(1)
{

	printf("-------MENU------");
	printf("\n 1.Execute ps command");
	printf("\n 2.Execute join command");
	printf("\n 3.Execute fork,wait and exec syscall");
	printf("\nEnter your choice : \n");
	scanf("%d",&choice);
switch(choice)
{
case 1 :
while(i<2)
{
printf("\nexecuting ps command\n");
sleep(3);
system("ps");
printf("\nexecuting ps -a command\n");
sleep(3);
system("ps -a");
printf("\nexecuting ps -ef command\n");
sleep(3);
system("ps -ef");
printf("\nexecuting ps r command\n");
sleep(3);system("ps r");
i++;
}
break;
case 2:
	printf("executing join operation\n");
	sleep(3);
	system("join myfile1.txt myfile2.txt ");
	printf("executing join operation and store in another file\n");
	sleep(3);
	system("join myfile1.txt myfile2.txt > newfile1.txt");
	printf("executing join operation and store in another file which is exists.\n");
	sleep(3);
	system("join myfile1.txt myfile2.txt >> newfile.txt");
break;
case 3:
	pid=fork();
	if(pid<0)
	{
	printf("unsuccessful execution...\n");
	exit(1);
	}
if(pid>0)
{
	printf("i am parent and new child with pid=%d\n",pid);
	pid1=wait(&status);
	printf("child of pid = %d is terminated\n",pid1);
}
else if(pid == 0)
{
	printf("i am child\n");
	sleep(3);
	execv("/usr/bin/firefox",argv);
//first provides path and then argument vector
}
break;
}
}
return 0;
}
