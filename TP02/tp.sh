#!/bin/bash
# tp.sh
nocolor='\033[0m'
red='\033[0;31m'
green='\033[0;32m'
orange='\033[0;33m'
cyan='\033[0;36m'

printtower=0
printtime=0

function checklettre()
{
	if [ "$1" != "-e" ]
	then
			echo
			echo -e "${red}-----ERROR-----${nocolor}"
			echo -e "${red}Vous devez entrer l'option -e apres d'avoir choisi l'algo${nocolor}"
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
function buildtower()
{
	choicearg="$1"
	choice=0
	lettre="$2"
	pathtxt="$3"
	pathtxt=${pathtxt:4}
	echo
	cd src/
	javac -Xlint Main.java
	if [ $? -eq 0 ]
	then
		echo -e "${green}***************\n${green}Lancement du l'algorithme -- Compilation REUSSI\n${green}***************${nocolor}"
	fi
	echo
	#merge
	case $choicearg in
		vorace)
			choice=1
		;;
		progdyn)
			choice=2
		;;
		tabou)
			choice=3
		;;
		*)
			echo -e "${red}-----ERROR-----${nocolor}"
			echo -e "${red}Taper la commande ${nocolor}--help ${red}pour connaitre les choix des tris${nocolor}"
			echo
			exit
		;;
	esac
	checklettre $lettre
	echo -e "${orange}Demarrge de l'algorithme ${cyan}${choicearg} ${orange}de la liste de bloques ${cyan}${pathtxt}${nocolor}"	
	echo
	java Main $printtower $printtime $choice $pathtxt
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
	javac -Xlint Main.java
	if [ $? -eq 0 ]
	then
		#case for choicearg
		case $choicearg in
			vorace)
			choice=1
			;;
			progdyn)
			choice=2
			;;
			tabou)
			choice=3
			;;
			*)
			echo -e "${red}-----ERROR-----${nocolor}"
			echo -e "${red}Taper la commande ${nocolor}--help ${red}pour connaitre les choix des algorithmes disponibles${nocolor}"
			echo
			exit
			;;
		esac
	fi
	namefile="${choicearg}_${n}.csv"
	echo "${choicearg}" > ../$namefile
	for i in {0..9}
	do
		java Main 0 1 $choice Files/b${n}_$i >> ../$namefile
		seuilerror $?
	done
	cd ../
}

# Help Function
function help()
{
  echo -e  "${cyan}-------------------------------\n      MENU AIDE POUR TP.SH\n-------------------------------\n${nocolor}"
  echo "Le script doit etre appeller ce cette maniere :"
  echo 
  echo -e "          tp.sh ${orange}[OPTIONS]${nocolor} -a [vorace | progdyn | tabou] -e path_vers_fichier_de_bloques"
  echo
  echo -e "${orange}[OPTIONS]${nocolor} : "
  echo
  echo -e "\t-p : Affiche les informations des bloques de la solution (hauteur, largeur, pronfondeur)\n\t-t : Affiche le temps de execution"
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
	printtower=1
	;;
	-t|-T)
	printtime=1
	;;
    -a)
	buildtower $2 $3 $4
	shift 3
	;;
	-all)
	callall $2 $3
	shift 2
	;;	
    #default
    *)
	echo
    echo -e "${red}NEED ARGUMENTS"
	echo -e "${red}Taper la commande ${nocolor}--help ${red}pour connaitre les choix des algorithmes disponibles${nocolor}"
    exit
	;;    
  esac
  shift
done

