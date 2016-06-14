#include <stdio.h>

typedef struct o{
int id,type,use,passage;
char name[50];
}objet;


objet To[50];


void affiche_dejaEcrit(int max){
int i;

for(i=0;i<max;i++){
   printf("%d,%s : type:%d use:%d passage:%d\n",To[i].id,To[i].name,To[i].type,To[i].use,To[i].passage);
}

}

char  deb[]="<objetL>";
char  end[]="</objetL>";

char  objetB[]="<objet>";
char  objetE[]="</objet>";

char  typeB[]="<type>";
char  typeE[]="</type>";

char  idB[]="<id>";
char  idE[]="</id>";

char  useB[]="<use>";
char  useE[]="</use>";

char  nameB[]="<name>";
char  nameE[]="</name>";

char  passageB[]="<passage>";
char  passageE[]="</passage>";

void Ecrit(int id,FILE* f){

fprintf(f,"%s\n",objetB);
fprintf(f,"%s%d%s\n",idB,To[id].id,idE);
fprintf(f,"%s%d%s\n",typeB,To[id].type,typeE);
fprintf(f,"%s%d%s\n",useB,To[id].use,useE);
fprintf(f,"%s%s%s\n",nameB,To[id].name,nameE);
fprintf(f,"%s%d%s\n",passageB,To[id].passage,passageE);
fprintf(f,"%s\n",objetE);
}


int main(){
int i;
char name[50];

printf("entrez le fichier ou ecrire le xml :");
scanf(" %s",&name);
FILE *f=fopen(name,"w+");
int rep;
char nameO[50];
int current=0;
fprintf(f,"%s\n",deb);
    while(rep!=-1){
        affiche_dejaEcrit(current);
        To[current].id=current+1;
        printf("\nPour quitter : '-1' lors de passage\n\n");
        printf("Recap : type(1:arme;2:consommable;3:antidote?;4:terrain;5:mur)\n");
        printf("        use=degat si arme ; use= heal si consommable\n\n");
        printf("Type=");
        scanf(" %d",&rep);
        To[current].type=rep;
        printf("use=");
        scanf(" %d",&rep);
        To[current].use=rep;
        printf("passage=");
        scanf(" %d",&rep);
        To[current].passage=rep;
        printf("name=");
        scanf("%s",&nameO);
        strcpy(To[current].name,nameO);

        if(rep!=-1) Ecrit(current,f);
        current++;
    }
fprintf(f,"%s",end);
}
