#!/bin/bash
# tp.sh
nocolor='\033[0m'
red='\033[0;31m'
green='\033[0;32m'
orange='\033[0;33m'

#Recoit en argurment l'erreur durant la compilation du code pour le calcul du seuil
function seuilerror()
{	
	case $1 in
		1)
			echo -e "${red}Aucun argument fournir. SVP faite la commande ./tp.sh --help pour connaitre les arguments de l'option -s"
			echo -e "${red}Error - Exit"
			exit
		;;
		2)
			echo -e "${red}Trop de argument fournir. SVP faite la commande ./tp.sh --help"
			echo -e "${red}Error - Exit"
			exit
			;;
		3)
			echo -e "${red}Le premier argument doit etre 1 ou 2. Faire la commande ./tp.sh --help pour plus d'information"
			echo -e "${red}Error - Exit"
			exit
		;;
		4)
			echo -e "${red} Fichier non existant. Verifiez le Path."
		;;
		*)
		;;
	esac
}

#Fonction pour etablir le seuil dans le merge sort et dans le bucket sort
function calculseuil()
{
	choice="$1"
	pathtxt="$2"
	pathtxt=${pathtxt:4}
	echo
	cd src/
	javac -Xlint MainSeuil.java
	if [ $? -eq 0 ]
	then
		echo -e "${green}***************\n${green}Calcul du seuil -- Compilation REUSSI\n${green}***************"
	fi
	echo
	echo -e "${orange}Demarrge du calcul du seuil${nocolor}"	
	echo
	java MainSeuil ${choice} ${pathtxt}
	seuilerror $?
	echo
	cd ../
}

#Fonction qui va lire une serie au complete
function calculseuilrecur()
{
	choice="$1"
	n="$2"
	ne="$3"
	namefile="calculseuil_${n}_${ne}.csv"
	echo
	cd src/
	javac -Xlint MainSeuil.java
	if [ $? -eq 0 ]
	then
		echo -e "${green}***************\n${green}Calcul du seuil -- Compilation REUSSI\n${green}***************"
	fi
	echo
	if [ ${ne} -eq 1 ]
	then
		echo -e "${orange} Demarrage du calcul du seuil avec $n elements avec les exemplaire {0,1,2,3,4,5,6,7,8,9}"
		echo -e "Un fichier $namefile sera creer"
		echo "Nombre element : ${n}" > ../$namefile
		for i in {0..9}
		do
			pathtxt="TextFiles/testset_${n}_${i}.txt"
			echo -n "$i," >> ../$namefile
			java MainSeuil ${choice} ${pathtxt} >> ../$namefile
			seuilerror $?
		done
	fi
	if [ ${ne} -eq 2 ]
	then
		echo -e "${orange} Demarrage du calcul du seuil avec $n elements avec les exemplaire {10,11,12,13,14,15,16,17,18,19}"
		echo -e "Un fichier $namefile sera creer"
		echo "Nombre element : ${n}" > ../$namefile
		for i in {10..19}
		do
			pathtxt="TextFiles/testset_${n}_${i}.txt"
			echo -n "$i," >> ../$namefile
			java MainSeuil ${choice} ${pathtxt} >> ../$namefile
			seuilerror $?
		done
	fi
	if [ ${ne} -eq 3 ]
	then
		echo -e "${orange} Demarrage du calcul du seuil avec $n elements avec les exemplaire {20,21,22,23,24,25,26,27,28,29}"
		echo -e "Un fichier $namefile sera creer"
		echo "Nombre element : ${n}" > ../$namefile
		for i in {19..29}
		do
			pathtxt="TextFiles/testset_${n}_${i}.txt"
			echo -n "$i," >> ../$namefile
			java MainSeuil ${choice} ${pathtxt} >> ../$namefile
			seuilerror $?
		done
	fi	
}

# Help Function
function help()
{
  echo
  echo -e  "-------------------------------\n      MENU AIDE POUR TP.SH\n-------------------------------\n"
  echo
  echo "Le script doit etre appeller ce cette maniere :"
  echo 
  echo "          tp.sh [OPTIONS] -a [bucket | bucketSeuil | merge | mergeSeuil] -e path_vers_fichier_txt"
  echo
  echo "[OPTIONS] : "
  echo
  echo -e "\t-p : Affiche les nombres tries\n\t-t : Affiche le temps d\'execution\n\t-s : Execute le code pour le calcul du seuil"
  echo -e "\t     Ce dernier code va creer un fichier CSV qui explique notre choix du seuil\n\n"
}
	
# Parse Arguments
while [[ $# -gt 0 ]]
do
  arg="$1"

  case $arg in
    --help|-help)
	help
    	;;
    -s)
	calculseuil $2 $3
	;;
	-sr)
	calculseuilrecur $2 $3 $4
	;;	
    #default
    *)
    echo "NEED ARGUMENTS"
    ;;
    
  esac
  shift
done

