//Lister Fichiers Java

commande=$(find . -name \*.java)

for file in $commande;
do
	echo $file;
done



//Compter Fichiers Java

nb_lignes=$(find . -name \*.java | wc -l)

echo $nb_lignes "fichiers Java trouvés";



//Lister Fichiers et Nombre de Lignes

commande=$(find . -name \*.java)

for file in $commande;
do
	count=$(cat $file | wc -l)
	
	echo $file;
	echo " Nombre de Lignes : " $count;
done



//Lister les 10 Plus Gros Fichiers Java

commande=$(find . -name \*.java -exec du -s {} \; | sort -n | tail -n 10)

for file in $commande;
do	
	echo $file;
done



//Compter Nombre de Lignes Total des Fichiers Java

commande=$(find . -name \*.java)

for file in $commande;
do
	count=$(cat $file | wc -l)
	total=$(($total + $count))

	echo "Total : " $total;
done

