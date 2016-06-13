#include <stdio.h>

typedef struct o{
int id,type,direction;
}objet;


objet To[50];


void affiche_dejaEcrit(int max){
int i;

for(i=0;i<max;i++){
   printf("%d : type:%d direction:%d\n",To[i].id,To[i].type,To[i].direction);
}

}

char  deb[]="<condL>";
char  end[]="</condL>";

char  objetB[]="<cond>";
char  objetE[]="</cond>";

char  typeB[]="<type>";
char  typeE[]="</type>";

char  idB[]="<id>";
char  idE[]="</id>";

char  useB[]="<d>";
char  useE[]="</d>";


void Ecrit(int id,FILE* f){

fprintf(f,"%s\n",objetB);
fprintf(f,"%s%d%s\n",idB,To[id].id,idE);
fprintf(f,"%s%d%s\n",useB,To[id].direction,useE);
fprintf(f,"%s%d%s\n",typeB,To[id].type,typeE);
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
        printf("\nPour quitter : '-1' lors de type\n\n");
        printf("Recap : type(1:arme;2:consommable;3:antidote?;4:terrain;5:mur)\n");
        printf("        use=degat si arme ; use= heal si consommable\n\n");
        printf("direction=");
        scanf(" %d",&rep);
        To[current].direction=rep;
        printf("type=");
        scanf(" %d",&rep);
        To[current].type=rep;

        if(rep!=-1) Ecrit(current,f);
        current++;
    }
fprintf(f,"%s",end);
}
