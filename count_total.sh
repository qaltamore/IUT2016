commande=$(find . -name \*.java)

for file in $commande;
do
	count=$(cat $file | wc -l)
	total=$(($total + $count))

	echo "Total : " $total;
done
