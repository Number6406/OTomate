#include <stdio.h>

typedef struct o{
int id;
char ms[500];
char me[500];
}objet;


objet To[50];


void affiche_dejaEcrit(int max){
int i;

for(i=0;i<max;i++){
   printf("%d? %s : %s\n",To[i].id,To[i].ms,To[i].me);
}

}

char  deb[]="<ActionL>";
char  end[]="</ActionL>";

char  objetB[]="<action>";
char  objetE[]="</action>";

char  typeB[]="<type>";
char  typeE[]="</type>";

char  idB[]="<id>";
char  idE[]="</id>";

char  useB[]="<use>";
char  useE[]="</use>";

char  nameB[]="<succes>";
char  nameE[]="</succes>";

char  passageB[]="<echec>";
char  passageE[]="</echec>";

void recup_string(char * s){
int i=0;
char c='a';
printf("(Caractere de fin de String : '$') ");
while (c!='$'){
scanf("\n%c",&c);
s[i]=c;
i++;
}
s[i-1]='\0';
}

void Ecrit(int id,FILE* f){

fprintf(f,"%s\n",objetB);
fprintf(f,"%s%d%s\n",idB,To[id].id,idE);
fprintf(f,"%s%s%s\n",nameB,To[id].ms,nameE);
fprintf(f,"%s%s%s\n",passageB,To[id].me,passageE);
fprintf(f,"%s\n",objetE);
}


int main(){
int i;
char name[50];

printf("entrez le fichier ou ecrire le xml :");
scanf(" %s",&name);
FILE *f=fopen(name,"w+");
int rep;
char nameO[500];
int current=0;
fprintf(f,"%s\n",deb);
    while(rep!=-1){
        affiche_dejaEcrit(current);
        printf("\nPour quitter : '-1' lors de id\n\n");
        printf("Recap : id(1:GoNord 2:GoSud 3:GoEst 4:GoOuest 5:AttNord 6:AttSud 7:AttEst 8:AttOuest 9:Ramasser\n");
        printf("          (10:Pieger 11:Manger 12:Soigner 13:Fuir 14:Detruire 15:Fouiller 16:Rien 0:Rien)\n\n");
        printf("id=");
        scanf(" %d",&rep);
        To[current].id=rep;
        printf("message succes=");
        //recup_string(nameO);
        scanf("\n%s",&nameO);
        strcpy(To[current].ms,nameO);
        printf("message echec=");
        scanf("\n%s",&nameO);
        strcpy(To[current].me,nameO);

        if(rep!=-1) Ecrit(current,f);
        current++;
    }
fprintf(f,"%s",end);
}
