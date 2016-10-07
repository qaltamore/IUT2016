commande=$(find . -name \*.java -exec du -s {} \; | sort -n | tail -n 10)

for file in $commande;
do	
	echo $file;
done
