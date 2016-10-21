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
	echo
	cd src/
	javac -Xlint main.java
	if [ $? -eq 0 ]
	then
		#case for choicearg
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
	fi
	namefile="${choicearg}_${n}_1.csv"
	echo "${choicearg}_1" > ../$namefile
	for i in {0..9}
	do
		java main 0 1 $choice Files/testset_${n}_$i.txt >> ../$namefile
		seuilerror $?
	done
	namefile="${choicearg}_${n}_2.csv"
	echo "${choicearg}_2" > ../$namefile
	for i in {10..19}
	do
		java main 0 1 $choice Files/testset_${n}_$i.txt >> ../$namefile
		seuilerror $?
	done
	namefile="${choicearg}_${n}_3.csv"
	echo "${choicearg}_3" > ../$namefile
	for i in {20..29}
	do
		java main 0 1 $choice Files/testset_${n}_$i.txt >> ../$namefile
		seuilerror $?
	done
	cd ../
	echo -e "${n}, ," > concat_${choicearg}_${n}.csv
	paste ${choicearg}_${n}*.csv >> concat_${choicearg}_${n}.csv
	rm -rf ${choicearg}*.csv
}

# Help Function
function help()
{
  echo -e  "${cyan}-------------------------------\n      MENU AIDE POUR TP.SH\n-------------------------------\n${nocolor}"
  echo "Le script doit etre appeller ce cette maniere :"
  echo 
  echo -e "          tp.sh ${orange}[OPTIONS]${nocolor} -a [bucket | bucketSeuil | merge | mergeSeuil] -e path_vers_fichier_txt"
  echo
  echo -e "${orange}[OPTIONS]${nocolor} : "
  echo
  echo -e "\t-p : Affiche les nombres tries\n\t-t : Affiche le temps d\'execution"
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
	shift 3
	;;
	-all)
	callall $2 $3
	shift 2
	;;	
    #default
    *)
    echo "NEED ARGUMENTS"
    ;;    
  esac
  shift
done

