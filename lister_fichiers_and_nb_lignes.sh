commande=$(find . -name \*.java)

for file in $commande;
do
	count=$(cat $file | wc -l)
	
	echo $file;
	echo " Nombre de Lignes : " $count;
done
