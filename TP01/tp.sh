#!/bin/bash
# tp.sh
nocolor='\033[0m'
red='\033[0;31m'
green='\033[0;32m'
orange='\033[0;33m'
cyan='\033[0;36m'

printsortedlist=0
printtime=0

function checklettre()
{
	if [ "$1" != "-e" ]
	then
			echo
			echo -e "${red}-----ERROR-----${nocolor}"
			echo -e "${red}Vous devez entrer l'option -e apres d'avoir choisi l'algo de tri${nocolor}"
			echo
			exit
	fi
}

#Recoit en argurment l'erreur durant la compilation du code pour le calcul du seuil
function seuilerror()
{	
	case $1 in
		1)
			echo -e "${red}-----ERROR-----"
			echo -e "${red} Fichier non existant. Verifiez le Path.${nocolor}"
		;;
		*)
		;;
	esac
}

#Fonction pour etablir le seuil dans le merge sort et dans le bucket sort
function callsorting()
{
	choicearg="$1"
	choice=0
	lettre="$2"
	pathtxt="$3"
	pathtxt=${pathtxt:4}
	echo
	cd src/
	javac -Xlint main.java
	if [ $? -eq 0 ]
	then
		echo -e "${green}***************\n${green}Calcul du seuil -- Compilation REUSSI\n${green}***************${nocolor}"
	fi
	echo
	#merge
	case $choicearg in
		merge)
			choice=1
		;;
		mergeSeuil)
			choice=2
		;;
		bucket)
			choice=3
		;;
		bucketSeuil)
			choice=4
		;;
		*)
			echo -e "${red}-----ERROR-----${nocolor}"
			echo -e "${red}Taper la commande ${nocolor}--help ${red}pour connaitre les choix des tris${nocolor}"
			echo
			exit
		;;
	esac
	checklettre $lettre
	echo -e "${orange}Demarrge du tri avec ${cyan}${choicearg} ${orange}de la liste ${cyan}${pathtxt}${nocolor}"	
	echo
	java main $printsortedlist $printtime $choice $pathtxt
	seuilerror $?
	echo
	cd ../
}

#Fonction qui va lire une serie au complete
function callall()
{

	choicearg="$1"
	choice=0
	n="$2"
	ne="$3"
	echo
	cd src/
	javac -Xlint main.java
	if [ $? -eq 0 ]
	then
		echo -e "${green}***************\n${green}Calcul du seuil -- Compilation REUSSI\n${green}***************${nocolor}"
	fi
	echo
	#merge
	case $choicearg in
		merge)
			choice=1
		;;
		mergeSeuil)
			choice=2
		;;
		bucket)
			choice=3
		;;
		bucketSeuil)
			choice=4
		;;
		*)
			echo -e "${red}-----ERROR-----${nocolor}"
			echo -e "${red}Taper la commande ${nocolor}--help ${red}pour connaitre les choix des tris${nocolor}"
			echo
			exit
		;;
	esac
	namefile="${choicearg}_${n}_${ne}.csv"
	if [ ${ne} -eq 1 ]
	then
		echo -e "${orange} Demarrage du tri avec ${cyan}${choicearg}  ${orange}avec $n elements provenants des exemplaire {0,1,2,3,4,5,6,7,8,9}${nocolor}"
		echo -e "Un fichier $namefile sera creer"
		echo "Nombre element : ${n}" > ../$namefile
		for i in {0..9}
		do
			pathtxt="TextFiles/testset_${n}_${i}.txt"
			echo -n "$i," >> ../$namefile
			java main 0 1 ${choice} ${pathtxt} >> ../$namefile
			seuilerror $?
		done
	fi
	if [ ${ne} -eq 2 ]
	then
		echo -e "${orange} Demarrage du tri avec ${cyan}${choicearg}${orange} avec $n elements provenants des exemplaire {10,11,12,13,14,15,16,17,18,19}${nocolor}"
		echo -e "Un fichier $namefile sera creer"
		echo "Nombre element : ${n}" > ../$namefile
		for i in {10..19}
		do
			pathtxt="TextFiles/testset_${n}_${i}.txt"
			echo -n "$i," >> ../$namefile
			java java 0 1 ${choicearg} ${pathtxt} >> ../$namefile
			seuilerror $?
		done
	fi
	if [ ${ne} -eq 3 ]
	then
		echo -e "${orange} Demarrage du tri avec ${cyan}${choicearg}${orange} avec $n elements provenant des exemplaire {20,21,22,23,24,25,26,27,28,29}${nocolor}"
		echo -e "Un fichier $namefile sera creer"
		echo "Nombre element : ${n}" > ../$namefile
		for i in {19..29}
		do
			pathtxt="TextFiles/testset_${n}_${i}.txt"
			echo -n "$i," >> ../$namefile
			java MainSeuil 0 1 ${choicearg} ${pathtxt} >> ../$namefile
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
	-p|-P)
	printsortedlist=1
	;;
	-t|-T)
	printtime=1
	;;
    -a)
	callsorting $2 $3 $4
	;;
	-all)
	callall $2 $3 $4
	;;	
    #default
    *)
    echo "NEED ARGUMENTS"
    ;;
    
  esac
  shift
done

