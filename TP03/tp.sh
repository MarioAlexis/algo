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
function seatcorps()
{
	pathtxt="$1"
	echo
	cd src/
	javac -Xlint Main.java
	if [ $? -eq 0 ]
	then
		echo -e "${green}***************\n${green}Lancement du l'algorithme -- Compilation REUSSI\n${green}***************${nocolor}"
	fi
	echo
	echo -e "${orange}Demarrge de l'algorithme avec le fichier ${cyan}$(basename "$pathtxt")${nocolor}"	
	echo
	java Main $printtower $printtime $pathtxt
	seuilerror $?
	echo
	cd ../
}

#Fonction qui va lire une serie au complete
function callall()
{
	cd src/
	javac -Xlint Main.java
	namefile="test.txt"
	for i in Files/*
	do
		echo -e "$(basename "$i")"
		echo -e "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*" >> ../$namefile
		echo -e "Fichier : $(basename "$i")" >> ../$namefile
		echo >> ../$namefile
		java Main 0 1 $i >> ../$namefile
		seuilerror $?
		echo >> ../$namefile
	done
	cd ../
}

# Help Function
function help()
{
  echo -e  "${cyan}-------------------------------\n      MENU AIDE POUR TP.SH\n-------------------------------\n${nocolor}"
  echo "Le script doit etre appeller ce cette maniere :"
  echo 
  echo -e "          tp.sh ${orange}[OPTIONS]${nocolor} -e path_vers_fichier_des_entreprises"
  echo
  echo -e "${orange}[OPTIONS]${nocolor} : "
  echo
  echo -e "\t-p : Affiche la formation des tables a la console\n\t-t : Affiche le temps de execution"
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
    -e)
	seatcorps $2
	shift
	;;
	-all)
	callall
	;;	
    #default
    *)
	echo
    echo -e "${red}NEED ARGUMENTS"
	echo -e "${red}Taper la commande ${nocolor}--help ${red}pour connaitre les options disponibles pour le programme.${nocolor}"
    exit
	;;    
  esac
  shift
done

